package com.fit2cloud.plugin.ibm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.AbstractMetalProvider;
import com.fit2cloud.metal.sdk.F2CMetalPlugin;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.constants.BareMetalConstants;
import com.fit2cloud.metal.sdk.model.*;
import com.fit2cloud.metal.sdk.model.request.IPMIRequest;
import com.fit2cloud.metal.sdk.util.*;
import com.fit2cloud.plugin.ibm.model.*;
import com.fit2cloud.plugin.ibm.utils.IBMOkHttpUtils;
import com.fit2cloud.plugin.ibm.utils.IBMRestUtil;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@F2CMetalPlugin
public class IBMMetalProvider extends AbstractMetalProvider {

    public IBMMetalProvider() {
        super.name = "fit2cloud-ibm-metal-plugin";
    }

    // cookie过期时间
    public static final long cookieTimeOut = 1200L;
    // 登陆接口
    public static final String login_url = "https://%s/data/login";
    public static final String logout_url = "https://%s/data/logout";
    public static final String memory_url = "https://%s/designs/imm/dataproviders/imm_memory.php";
    public static final String disk_url = "https://%s/designs/imm/dataproviders/imm_disks.php";
    public static final String cpu_url = "https://%s/designs/imm/dataproviders/imm_processors.php";
    public static final String info_url = "https://%s/designs/imm/dataproviders/imm_info.php";
    public static final String system_url = "https://%s/designs/imm/dataproviders/imm_system.php";

    public static List<String> httpIpList = new ArrayList() {{
        add("10.153.5.10");
        add("10.153.5.11");
        add("10.153.5.12");
        add("10.153.5.13");
        add("10.153.5.1");
        add("10.153.5.2");
        add("10.153.5.4");
        add("10.153.5.58");
        add("10.153.5.6");
        add("10.153.5.7");
        add("10.153.5.8");
        add("10.153.5.9");
    }};

    // redis缓存cookie
    private String KEY_PREFIX = "FIT2CLOUD_IBM_";
    private static ConcurrentHashMap<String, Map<String, String>> headerMap = new ConcurrentHashMap();

    public boolean login(String ip, String userName, String password) {
        Response response = null;
        try {
            String cookie = getCookie(ip, userName, password);
            if (StringUtils.isNotBlank(cookie)) {
                return true;
            }
            response = IBMOkHttpUtils.postResponse(String.format(login_url, ip), String.format("user=%s&password=%s&SessionTimeout=%s", IBMRestUtil.escapeStr(userName), IBMRestUtil.escapeStr(password), cookieTimeOut));
            if (response.isSuccessful()) {
                JSONObject resultJson = JSONObject.parseObject(response.body().string());
                if (resultJson != null && StringUtils.equalsIgnoreCase(resultJson.getString("status"), "ok") && StringUtils.equalsIgnoreCase(resultJson.getString("authResult"), "0")) {
                    KEY_PREFIX = KEY_PREFIX + ip + userName;
                    if (StringUtils.isNotBlank(response.header("Set-Cookie"))) {
                        Map<String, String> cookieMap = new HashMap<>();
                        cookieMap.put("Cookie", response.header("Set-Cookie"));
                        headerMap.put(ip, cookieMap);
                        return true;
                    }
                } else {
                    removeCookie(ip);
                    LogUtil.error(String.format("爬取IBM ip：{%s}时，获取sessionKey失败！：e:{%s}", ip, "authResult:" + resultJson.getString("authResult")));
                }
            }
        } catch (Exception e) {
            removeCookie(ip);
            LogUtil.error(String.format("爬取IBM ip：{%s}时，获取sessionKey失败！：e:{%s}", ip, ExceptionUtils.getExceptionDetail(e)));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return false;
    }

    public MachineEntity getMachineEntity(String ip, String userName, String password) {
        MachineEntity machineEntity = new MachineEntity();
        if (login(ip, userName, password)) {
            Map<String, String> headers = new HashMap<>();
            String cookie = getCookie(ip, userName, password);
            headers.put("Cookie", cookie);
            IBMInfoDTO ibmInfoDTO = getInfoDetails(headers, ip);
            machineEntity.setName("IBM " + ibmInfoDTO.getMachineName());
            // 序列号
            machineEntity.setSerialNo(ibmInfoDTO.getSerialNumber());
            // 内存
            List<F2CPmMemory> pmMemories = IBMRestUtil.covertToF2CMemory(getMemoryDetails(headers, ip));
            machineEntity.setPmMemories(pmMemories);
            List<JSONObject> memoryDetails = new ArrayList<>();
            for (F2CPmMemory F2CPmMemory : pmMemories) {
                JSONObject memoryDetail = new JSONObject();
                memoryDetail.put("memorySize", new BigDecimal(F2CPmMemory.getMemModSize()).divide(new BigDecimal("1024"), 0, BigDecimal.ROUND_HALF_UP).intValue());
                memoryDetail.put("memoryType", F2CPmMemory.getMemModType());
                machineEntity.setMemoryType(F2CPmMemory.getMemModType());
                memoryDetails.add(memoryDetail);
            }
            HashMap<String, String> extendInfo = new HashMap<>();
            extendInfo.put("memoryDetails", memoryDetails.toString());
            machineEntity.setExtendInfo(extendInfo);
            machineEntity.setMemory(pmMemories.stream().mapToInt(m -> Integer.valueOf(m.getMemModSize())).sum());
            machineEntity.setPmMemories(pmMemories);
            machineEntity.setBmcIp(ip);

            //通过IPMI补充CPU信息
            String bmcResult;
            List<F2CPmCpu> cpus = new LinkedList<>();
            if (IpUtil.ping(ip)) {
                IBMCpuDTO ibmCpuDTO = getCpuDetails(headers, ip);
                cpus = IBMRestUtil.covertToF2CProcessor(ibmCpuDTO);
            } else {
                try {
                    bmcResult = IPMIUtils.exeCommand(new IPMIRequest(ip, userName, password), "fru");
                    String cpuStr = IPMIUtils.grep(bmcResult, "CPU");
                    Long now = System.currentTimeMillis();
                    if (StringUtils.isNotBlank(cpuStr)) {
                        String s[] = cpuStr.split("\n");
                        for (int i = 0; i < cpuStr.split("\n").length / 2; i++) {
                            F2CPmCpu cpu = new F2CPmCpu();
                            cpu.setProcSocket(s[2 * i].split(":")[1].trim());
                            cpu.setProcName(s[2 * i + 1].split(":")[1].trim());
                            cpu.setProcSpeed((int) (Double.parseDouble(s[2 * i + 1].substring(s[2 * i + 1].lastIndexOf(".") - 1).replace("GHz", "")) * 1000) + "");
                            cpu.setSyncTime(now);
                            cpus.add(cpu);
                        }
                    }
                } catch (Exception e) {
                    removeCookie(ip);
                    LogUtil.error("爬取IBM:" + ip + "时，通过IPMI获取CPU信息失败!" + ExceptionUtils.getExceptionDetail(e));
                }
            }
            machineEntity.setCpu(cpus.size());
            machineEntity.setPmCpus(cpus);

            // IBM的磁盘只能获取到数量，没有磁盘大小信息
//            machineEntity.setDisk();
//            machineEntity.setDisks();
            logout(gson.toJson(new IPMIRequest(ip, userName, password)));
        }
        return machineEntity;
    }

    /**
     * 获取内存信息
     *
     * @param headers
     * @param ip
     */
    private IBMMemoryDTO getMemoryDetails(Map<String, String> headers, String ip) {
        try {
            if (IBMMetalProvider.httpIpList.stream().filter(u -> ip.equalsIgnoreCase(u)).count() > 0) {
                String url = memory_url.replace("https", "http");
                String itemsStr = IBMRestUtil.getItemsByEntity(HttpFutureUtils.getHttp(String.format(url, ip), headers));
                if (StringUtils.isNotBlank(itemsStr)) {
                    String resultStr = itemsStr.replace("memory.", "").replace("memory_events.", "");
                    List<IBMMemoryDTO> resultList = JSONObject.parseArray(resultStr).toJavaList(IBMMemoryDTO.class);
                    if (!CollectionUtils.isEmpty(resultList)) {
                        return resultList.get(0);
                    }
                }
            }

            String itemsStr = IBMRestUtil.getItemsByEntity(HttpFutureUtils.getHttps(String.format(memory_url, ip), headers));
            if (StringUtils.isNotBlank(itemsStr)) {
                String resultStr = itemsStr.replace("memory.", "").replace("memory_events.", "");
                List<IBMMemoryDTO> resultList = JSONObject.parseArray(resultStr).toJavaList(IBMMemoryDTO.class);
                if (!CollectionUtils.isEmpty(resultList)) {
                    return resultList.get(0);
                }
            }
        } catch (Exception e) {
            LogUtil.error(String.format("爬取IBM ip：{%s}时，获取内存信息失败！：e:{%s}", ip, ExceptionUtils.getExceptionDetail(e)));
            removeCookie(ip);
        }
        return null;
    }

    /**
     * 获取磁盘信息
     *
     * @param headers
     * @param ip
     */
    private IBMDiskDTO getDiskDetails(Map<String, String> headers, String ip) {
        try {
            String itemsStr = IBMRestUtil.getItemsByEntity(HttpFutureUtils.getHttps(String.format(disk_url, ip), headers));
            if (StringUtils.isNotBlank(itemsStr)) {
                String resultStr = itemsStr.replace("disk.", "").replace("disk_events.", "");
                List<IBMDiskDTO> resultList = JSONObject.parseArray(resultStr).toJavaList(IBMDiskDTO.class);
                if (!CollectionUtils.isEmpty(resultList)) {
                    return resultList.get(0);
                }
            }
        } catch (Exception e) {
            LogUtil.error(String.format("爬取IBM ip：{%s}时，获取磁盘信息失败！：e:{%s}", ip, ExceptionUtils.getExceptionDetail(e)));
            removeCookie(ip);
        }
        return null;
    }

    /**
     * 获取CPU信息
     *
     * @param headers
     * @param ip
     */
    public IBMCpuDTO getCpuDetails(Map<String, String> headers, String ip) {
        try {
            if (IBMMetalProvider.httpIpList.stream().filter(u -> ip.equalsIgnoreCase(u)).count() > 0) {
                String url = cpu_url.replace("https", "http");
                String itemsStr = IBMRestUtil.getItemsByEntity(HttpFutureUtils.getHttp(String.format(url, ip), headers));
                if (StringUtils.isNotBlank(itemsStr)) {
                    String resultStr = itemsStr.replace("processors.", "").replace("processor_events.", "");
                    List<IBMCpuDTO> resultList = JSONObject.parseArray(resultStr).toJavaList(IBMCpuDTO.class);
                    if (!CollectionUtils.isEmpty(resultList)) {
                        return resultList.get(0);
                    }
                }
            }
            String itemsStr = IBMRestUtil.getItemsByEntity(HttpFutureUtils.getHttps(String.format(cpu_url, ip), headers));
            if (StringUtils.isNotBlank(itemsStr)) {
                String resultStr = itemsStr.replace("processors.", "").replace("processor_events.", "");
                List<IBMCpuDTO> resultList = JSONObject.parseArray(resultStr).toJavaList(IBMCpuDTO.class);
                if (!CollectionUtils.isEmpty(resultList)) {
                    return resultList.get(0);
                }
            }
        } catch (Exception e) {
            LogUtil.error(String.format("爬取IBM ip：{%s}时，获取CPU信息失败！：e:{%s}", ip, ExceptionUtils.getExceptionDetail(e)));
            removeCookie(ip);
        }
        return null;
    }

    /**
     * 获取设备信息
     *
     * @param headers
     * @param ip
     */
    private IBMInfoDTO getInfoDetails(Map<String, String> headers, String ip) {
        try {
            if (IBMMetalProvider.httpIpList.stream().filter(u -> ip.equalsIgnoreCase(u)).count() > 0) {
                String url = info_url.replace("https", "http");
                String itemsStr = IBMRestUtil.getItemsByEntity(HttpFutureUtils.getHttp(String.format(url, ip), headers));
                List<IBMInfoDTO> ibmInfoDTOS = JSONArray.parseArray(itemsStr).toJavaList(IBMInfoDTO.class);
                if (!CollectionUtils.isEmpty(ibmInfoDTOS)) {
                    return ibmInfoDTOS.get(0);
                }
            }
            String itemsStr = IBMRestUtil.getItemsByEntity(HttpFutureUtils.getHttps(String.format(info_url, ip), headers));
            List<IBMInfoDTO> ibmInfoDTOS = JSONArray.parseArray(itemsStr).toJavaList(IBMInfoDTO.class);
            if (!CollectionUtils.isEmpty(ibmInfoDTOS)) {
                return ibmInfoDTOS.get(0);
            }
        } catch (Exception e) {
            LogUtil.error(String.format("爬取IBM ip：{%s}时，获取设备信息！：e:{%s}", ip, ExceptionUtils.getExceptionDetail(e)));
            removeCookie(ip);
        }
        return null;
    }

    /**
     * 获取系统信息和日志
     *
     * @param headers
     * @param ip
     */
    private IBMSystemDTO getSystemDetails(Map<String, String> headers, String ip) {
        try {
            String itemsStr = IBMRestUtil.getItemsByEntity(HttpFutureUtils.getHttps(String.format(system_url, ip), headers));
            return gson.fromJson(itemsStr, IBMSystemDTO.class);
        } catch (Exception e) {
            LogUtil.error(String.format("爬取IBM ip：{%s}时，获取系统信息和日志失败！：e:{%s}", ip, ExceptionUtils.getExceptionDetail(e)));
            removeCookie(ip);
        }
        return null;
    }

    /**
     * 得到最新cookie
     *
     * @param ip
     * @param userName
     * @param password
     * @return
     */
    private String getCookie(String ip, String userName, String password) {
        return headerMap.get(ip) == null ? null : headerMap.get(ip).get("Cookie");
    }

    private void removeCookie(String ip) {
        headerMap.remove(ip);
    }

    @Override
    public Map<String, String> getHeader(String ip) {
        return headerMap.get(ip);
    }

    @Override
    public PluginResult login(String ipmiRequestStr) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequestStr, IPMIRequest.class);
        checkIPMIParameter(request);
        if (login(request.getHost(), request.getUserName(), request.getPwd())) {
            return PluginResult.success();
        }
        return PluginResult.error("");
    }

    @Override
    public PluginResult logout(String ipmiRequestStr) throws MetalPluginException {
        try {
            IPMIRequest request = gson.fromJson(ipmiRequestStr, IPMIRequest.class);
            String cookie = getCookie(request.getHost(), request.getUserName(), request.getPwd());
            if (StringUtils.isBlank(cookie)) {
                return PluginResult.error("logout时不存在cookie信息");
            } else {
                Map<String, String> headers = new HashMap<>();
                headers.put("Cookie", cookie);
                if (IBMMetalProvider.httpIpList.stream().filter(u -> request.getHost().contains(u)).count() > 0) {
                    String url = logout_url.replace("https", "http");
                    IBMOkHttpUtils.getHttp(String.format(logout_url, request.getHost()), headers);
                } else {
                    IBMOkHttpUtils.getHttps(String.format(logout_url, request.getHost()), headers);
                }
                removeCookie(request.getHost());
            }
        } catch (Exception e) {
            throw new MetalPluginException(e);
        }
        return PluginResult.success();
    }

    @Override
    public MachineEntity getMachineEntity(String ipmiRequestStr) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequestStr, IPMIRequest.class);
        checkIPMIParameter(request);
        if (login(ipmiRequestStr).isSuccess()) {
            return getMachineEntity(request.getHost(), request.getUserName(), request.getPwd());
        }
        return null;
    }

    @Override
    public JSONObject getRaidPayLoad(String raidConfigRequestStr) throws MetalPluginException {
        MetalPluginException.throwException("该插件暂时不支持IBM组建Raid");
        return null;
    }

    @Override
    public JSONObject getDeleteRaidPayload() {
        MetalPluginException.throwException("该插件暂时不支持IBM组建Raid");
        return null;
    }

    @Override
    public String getRaidWorkFlow() {
        MetalPluginException.throwException("该插件暂时不支持IBM组建Raid");
        return null;
    }

    @Override
    public String getDeleteRaidWorkFlow() {
        MetalPluginException.throwException("该插件暂时不支持IBM组建Raid");
        return null;
    }

    @Override
    public String getCatalogRaidWorkFlow() {
        MetalPluginException.throwException("该插件暂时不支持IBM组建Raid");
        return null;
    }

    @Override
    public String getValidRaidType(String raidType) throws MetalPluginException {
        MetalPluginException.throwException("该插件暂时不支持IBM组建Raid");
        return null;
    }

    @Override
    public F2CPmMetric getMetric(String ipmiRequestStr) throws MetalPluginException {
        try {
            F2CPmMetric metric = new F2CPmMetric();
            IPMIRequest request = gson.fromJson(ipmiRequestStr, IPMIRequest.class);
            checkIPMIParameter(request);
            String ipmiSdr = IPMIUtils.exeCommand(request, "sdr");

            // 日志信息
            String selStr = IPMIUtils.exeCommand(request, "sel");
            if (StringUtils.isNotBlank(selStr)) {
                for (String s : selStr.split("\n")) {
                    if (StringUtils.isNotBlank(s) && s.contains("Percent Used")) {
                        metric.setSelPercentUsed(Long.valueOf(s.replace("%", "")
                                .replace("\"", "")
                                .replace(" ", "")
                                .split(":")[1]));
                    }
                }
            }

            //cpu温度
            String cpuTempStr = IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "CPU\\d VR Temp"), "\\|", 1, "degreesC");
            for (String s : cpuTempStr.split("\n")) {
                metric.getCpuTemp().add(Integer.valueOf(s));
            }

            //主板温度
            String mainBoardTempStr = IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "PCH Temp"), "\\|", 1, "degreesC");
            for (String s : mainBoardTempStr.split("\n")) {
                if (!"disabled".equalsIgnoreCase(s))
                    metric.setMainBoardTemp(Integer.valueOf(s));
            }

            //内存状态
            setMetricList(ipmiSdr, "DIMM \\d", metric.getMemoryStatus(), "");

            //电源状态
            setMetricList(ipmiSdr, "Power Supply \\d", metric.getPowerStatus(), "");

            //电源瓦特
            String powerWattStr = IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "Avg Power"), "\\|", 1, "Watts");
            for (String s : powerWattStr.split("\n")) {
                metric.getPowerWatt().add(Integer.valueOf(s));
            }

            //风扇状态
            setMetricList(ipmiSdr, "Fan \\d[AB] Tach", metric.getFanStatus(), "");

            //磁盘状态
            setMetricList(ipmiSdr, "Drive \\d+", metric.getDisktatus(), "");
            return metric;
        } catch (Exception e) {
            MetalPluginException.throwException("查询监控数据出现异常！" + ExceptionUtils.getExceptionDetail(e));
        }
        return null;
    }

    private void setMetricList(String ipmiSdr, String s2, List<Integer> disktatus, String replaceVal) {
        String diskStatusStr = IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, s2), "\\|", 2, replaceVal);
        for (String s : diskStatusStr.split("\n")) {
            disktatus.add("ok".equalsIgnoreCase(s) ? BareMetalConstants.HEALTHY : "nc".equalsIgnoreCase(s) ? BareMetalConstants.NOTDETECTED : BareMetalConstants.ERROR);
        }
    }

}
