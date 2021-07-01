package com.fit2cloud.plugin.huawei;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.AbstractMetalProvider;
import com.fit2cloud.metal.sdk.F2CMetalPlugin;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.constants.BareMetalConstants;
import com.fit2cloud.metal.sdk.model.*;
import com.fit2cloud.metal.sdk.model.request.IPMIRequest;
import com.fit2cloud.metal.sdk.util.DiskUtils;
import com.fit2cloud.metal.sdk.util.HttpFutureUtils;
import com.fit2cloud.metal.sdk.util.IPMIUtils;
import com.fit2cloud.metal.sdk.util.LogUtil;
import com.fit2cloud.plugin.huawei.model.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@F2CMetalPlugin
public class HuaweiMetalProvider extends AbstractMetalProvider {

    public HuaweiMetalProvider() {
        super.name = "fit2cloud-huawei-metal-plugin";
    }

    private static final String getCookieUrl = "https://%s/bmc/php/processparameter.php";
    private static final String getTokenUrl = "https://%s/bmc/php/gettoken.php";

    private static final String getMultiUrl = "https://%s/bmc/php/getmultiproperty.php";

    private static Map<String, String> inpuMap;
    private static ConcurrentHashMap<String, Map<String, String>> headerMap;
    private static Gson gson;

    static {
        try {
            headerMap = new ConcurrentHashMap<>();
            gson = new Gson();
            inpuMap = new HashMap<String, String>() {{
                put("bmc", "[{\"class_name\":\"BMC\",\"obj_name\":\"BMC\",\"property_list\":[\"CpldVersion\",\"PMEVer\",\"FlashUnitNum\",\"CpldUnitNum\",\"UbootVersion\",\"UbootBackupVer\",\"SystemName\",\"DeviceId\",\"DeviceSerialNumber\"]},{\"class_name\":\"Fru\",\"obj_name\":\"MotherBoard\",\"property_list\":[\"Name\",\"PcbVersion\",\"BoardId\"]},{\"class_name\":\"Elabel\",\"obj_name\":\"Fru0Elabel\",\"property_list\":[\"ProductAssetTag\",\"BoardManufacturer\",\"BoardSerialNumber\", \"BoardProductName\",\"ProductPartNumber\"]},{\"class_name\":\"Bios\",\"obj_name\":\"Bios\",\"property_list\":[\"Version\",\"UnitNum\"]},{\"class_name\":\"BOARD\",\"obj_name\":\"\",\"property_list\":[\"Name\",\"SlotId\"]},{\"class_name\":\"PRODUCT\",\"obj_name\":\"\",\"property_list\":[\"ChassisNum\", \"ProductName\"]},{\"class_name\":\"MainBoard\",\"obj_name\":\"\",\"property_list\":[\"PCHModel\"]}]");
                put("cpu", "[{ \"class_name\":\"Cpu\", \"obj_name\":\"\", \"property_list\":[\"Name\",\"Presence\",\"Manufacturer\",\"Version\",\"ProcessorID\",\"CurrentSpeed\",[\"CoreCount\",\"/\",\"ThreadCount\"],\"MemoryTec\",[\"L1Cache\",\"/\",\"L2Cache\",\"/\",\"L3Cache\"],\"DisableCpuHw\",\"CpuHealth\",\"PartNum\",\"SN\"]}]");
                put("memory", "[{ \"class_name\":\"Memory\", \"obj_name\":\"\", \"property_list\":[\"DimmName\",\"Presence\",\"Location\",\"Manufacturer\",\"Capacity\",\"ClockSpeed\",\"SN\",\"Type\",\"MinimumVoltage\",\"Rank\",\"BitWidth\",\"Technology\",\"PartNum\",\"MemHealth\",\"RemainLife\",\"MediaTemp\",\"ControllerTemp\",\"VolatileCapacity\",\"PersistentCapacity\",\"HealthStatus\"]}]");
                put("logdrive", "[{\"class_name\":\"LogicalDrive\",\"obj_name\":\"\",\"property_list\":[\"DedicatedSparedPDEnclosures\",\"ParticipatedPDEnclosure\",\"OSDriveName\", \"AccessPolicy\",\"DriveName\",\"DedicatedSparedPDSlots\",\"InitState\",\"SpanDepth\",\"BGIEnabled\",\"IsSSCD\",\"CacheCadeLD\",\"DiskCachePolicy\", \"ParticipatedPDSlot\", \"TargetID\", \"DriveName\",\"IsBootable\", \"Size\", \"RaidLevel\", \"StripSize\", \"DriveStatus\", \"CurrentReadPolicy\", \"DefaultReadPolicy\", \"CurrentWritePolicy\", \"DefaultWritePolicy\", \"CurrentCachePolicy\", \"DefaultCachePolicy\", \"ConsistencyCheck\", \"RefRAIDController\",\"RefArray\"]}]");
                put("hdd", "[{\"class_name\":\"Hdd\",\"obj_name\":\"\",\"property_list\":[\"EnclosureDeviceId\",\"ResId\",\"CapacityMB\",\"HoursOfPoweredUp\", \"Location\",\"Fault\",\"FirmwareStatus\", \"SASAddress2\", \"SlotNumber\", \"RefRAIDController\", \"RebuildProgress\", \"PatrolState\", \"SASAddress1\", \"RemnantMediaWearout\", \"Presence\", \"SerialNumber\",\"ModelNumber\", \"Id\", \"DeviceName\", \"Manufacturer\", \"HddHealth\", \"FirmwareVersion\", \"PowerState\", \"MediaType\", \"InterfaceType\", \"InterfaceSpeed\", \"LinkSpeed\", \"Capacity\", \"Temperature\", \"HotSpare\", \"RebuildState\", \"PartNum\"]}]");
                put("nic", "[{\"class_name\":\"BusinessPort\",\"obj_name\":\"\",\"property_list\":[\"RefNetCard\",\"NetDevFuncType\",\"MacAddr\",\"CardType\",\"OSEthName\",\"IPv6Info\",\"IPv4Info\"]},{\"class_name\":\"NetCard\",\"obj_name\":\"\",\"property_list\":[\"ProductName\",\"VirtualNetCardFlag\"]}]");
            }};
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String decodeResponse(String response) {
        if (StringUtils.isBlank(response)) {
            return null;
        }
        return UriEncoder.decode(response);
    }

    public static HttpResponse postHttpsResponse(String url, String payload, String
            contenttype, Map<String, String> headers) {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpFutureUtils.getOneHttpClient();
        try {
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if (StringUtils.isNotBlank(contenttype)) {
                httpPost.addHeader("Content-Type", contenttype);
            }
            if (StringUtils.isNotBlank(payload)) {
                HttpEntity httpEntity = new StringEntity(payload, "utf-8");
                httpPost.setEntity(httpEntity);
            }
            return httpClient.execute(httpPost);
        } catch (Exception e) {
            throw new RuntimeException("url请求失败：url" + url);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean login(String ip, String userName, String password) {

        if (headerMap.get(ip) != null) {
            return true;
        }

        HttpPost httpPost = new HttpPost(String.format(getCookieUrl, ip));
        CloseableHttpClient httpClient = HttpFutureUtils.getOneHttpClient();
        try {
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpEntity httpEntity = new StringEntity(String.format("check_pwd=%s&user_name=%s&func=AddSession", UriEncoder.encode(password), UriEncoder.encode(userName)), "utf-8");
            httpPost.setEntity(httpEntity);
            HttpResponse response = httpClient.execute(httpPost);
            String sessionId4 = response.getHeaders("Set-cookie")[0].getValue();
            HttpEntity sessionId3Entity = response.getEntity();
            JSONArray result = JSONArray.parseArray(UriEncoder.decode(EntityUtils.toString(sessionId3Entity)));
            String sessionId3 = JSONArray.parseArray(result.getJSONObject(0).getString("AddSession")).get(1).toString();
            Map<String, String> header = new HashMap();
            header.put("SessionId3", sessionId3);
            header.put("SessionId4", sessionId4);

            httpPost = new HttpPost(String.format(getTokenUrl, ip));
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
            String token = EntityUtils.toString(httpClient.execute(httpPost).getEntity());
            header.put("token", token);
            headerMap.put(ip, header);
            return true;
        } catch (Exception e) {
            LogUtil.error(String.format("爬取华为ip：{%s}时，获取sessionKey失败！：e:{%}", ip, e));
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public MachineEntity getMachineEntity(String ip, String userName, String password) {
        MachineEntity machineEntity = new MachineEntity();
        List<F2CPmDisk> diskList = new LinkedList<>();
        List<HuaweiCpuDTO.CpuBean> cpuDTOS = new LinkedList<>();
        List<HuaweiMemoryDTO.MemoryBean> memoryDTOS = new LinkedList<>();
        List<F2CPmNetworkCard> networkCards = new LinkedList<>();

        if (login(ip, userName, password)) {
            try {
                Map<String, String> header = headerMap.get(ip);

                List<BasicNameValuePair> valuePairs = new LinkedList<>();
                valuePairs.add(new BasicNameValuePair("token", header.get("token")));
                valuePairs.add(new BasicNameValuePair("str_input", inpuMap.get("bmc")));
                UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(valuePairs);

                String result = HttpFutureUtils.postHttps(String.format(getMultiUrl, ip), encodedFormEntity, "application/x-www-form-urlencoded; charset=UTF-8", header);

                HuaweiBMCDTO huaweiBMCDTO = gson.fromJson(decodeResponse(result), HuaweiBMCDTO.class);
                List<HuaweiBMCDTO.BMCBean> bmcBeans = huaweiBMCDTO.getBMC();

                machineEntity.setSerialNo(bmcBeans.get(0).getDeviceSerialNumber());
                machineEntity.setName("Huawei " + bmcBeans.get(0).getSystemName());
                machineEntity.setModel(bmcBeans.get(0).getSystemName());
                machineEntity.setBmcIp(ip);
                machineEntity.setBrand("Huawei");

                //cpu
                valuePairs = new LinkedList<>();
                valuePairs.add(new BasicNameValuePair("token", header.get("token")));
                valuePairs.add(new BasicNameValuePair("str_input", inpuMap.get("cpu")));
                encodedFormEntity = new UrlEncodedFormEntity(valuePairs);

                result = HttpFutureUtils.postHttps(String.format(getMultiUrl, ip), encodedFormEntity, "application/x-www-form-urlencoded; charset=UTF-8", header);
                HuaweiCpuDTO cpuDTO = gson.fromJson(decodeResponse(result), HuaweiCpuDTO.class);
                cpuDTOS = cpuDTO.getCpu();
                HuaweiCpuDTO.CpuBean bean = cpuDTOS.get(0);

                machineEntity.setCpuFre(bean.getCurrentSpeed().replace("MHz", "").replace(" ", ""));

                machineEntity.setCpuType(bean.getVersion());
                machineEntity.setCore(Integer.parseInt(bean.getCoreCountThreadCount().split("/")[0]));
                machineEntity.setThread(Integer.parseInt(bean.getCoreCountThreadCount().split("/")[1]));

                //内存
                valuePairs = new LinkedList<>();
                valuePairs.add(new BasicNameValuePair("token", header.get("token")));
                valuePairs.add(new BasicNameValuePair("str_input", inpuMap.get("memory")));
                encodedFormEntity = new UrlEncodedFormEntity(valuePairs);

                result = HttpFutureUtils.postHttps(String.format(getMultiUrl, ip), encodedFormEntity, "application/x-www-form-urlencoded; charset=UTF-8", header);
                HuaweiMemoryDTO memoryDTO = gson.fromJson(decodeResponse(result), HuaweiMemoryDTO.class);
                memoryDTOS = memoryDTO.getMemory().stream().filter(m -> StringUtils.isNotBlank("m.getCapacity()") && !"Unknown".equalsIgnoreCase(m.getCapacity())).collect(Collectors.toList());

                //内存存两份一份存extendInfo作为直接展示 另一份存另一个表
                List<JSONObject> memoryDetails = new ArrayList<>();
                memoryDTOS.forEach(memory -> {
                    JSONObject memoryDetail = new JSONObject();
                    memoryDetail.put("memorySize", Integer.parseInt(memory.getCapacity().replace("MB", "").replace(" ", "")) / 1024);
                    memoryDetail.put("memoryType", memory.getType());
                    machineEntity.setMemoryType(memory.getType());
                    memoryDetails.add(memoryDetail);
                });

                HashMap<String, String> extendInfo = new HashMap<>();
                extendInfo.put("memoryDetails", memoryDetails.toString());
                machineEntity.setExtendInfo(extendInfo);
                machineEntity.setMemory(memoryDetails.stream().mapToInt(m -> m.getInteger("memorySize")).sum());

                //逻辑磁盘
                valuePairs = new LinkedList<>();
                valuePairs.add(new BasicNameValuePair("token", header.get("token")));
                valuePairs.add(new BasicNameValuePair("str_input", inpuMap.get("logdrive")));
                encodedFormEntity = new UrlEncodedFormEntity(valuePairs);

                result = HttpFutureUtils.postHttps(String.format(getMultiUrl, ip), encodedFormEntity, "application/x-www-form-urlencoded; charset=UTF-8", header);
                HuaweiLogDrivesDTO huaweiLogDrivesDTO = gson.fromJson(decodeResponse(result), HuaweiLogDrivesDTO.class);
                Map<Integer, String> phRaidMap = new HashMap<>();
                if (huaweiLogDrivesDTO != null && huaweiLogDrivesDTO.getLogicalDrive().size() > 0) {
                    huaweiLogDrivesDTO.getLogicalDrive().forEach(d -> {
                        d.getParticipatedPDSlot().forEach(s -> {
                            phRaidMap.put(s, d.getRaidLevel() + "");
                        });
                    });
                }

                //物理磁盘
                valuePairs = new LinkedList<>();
                valuePairs.add(new BasicNameValuePair("token", header.get("token")));
                valuePairs.add(new BasicNameValuePair("str_input", inpuMap.get("hdd")));
                encodedFormEntity = new UrlEncodedFormEntity(valuePairs);

                result = HttpFutureUtils.postHttps(String.format(getMultiUrl, ip), encodedFormEntity, "application/x-www-form-urlencoded; charset=UTF-8", header);

                HuaweiPhyDrivesDTO hpPhyDrivesDTO = gson.fromJson(decodeResponse(result), HuaweiPhyDrivesDTO.class);
                if (hpPhyDrivesDTO != null && hpPhyDrivesDTO.getHdd() != null && hpPhyDrivesDTO.getHdd().size() > 0) {
                    hpPhyDrivesDTO.getHdd().forEach(d -> {
                        if (d.getCapacity() < 0) return;
                        F2CPmDisk disk = new F2CPmDisk();
                        disk.setModel(d.getModelNumber());
                        disk.setSn(d.getSerialNumber());
                        disk.setSize(DiskUtils.getDiskManufactorValue(d.getCapacity() + ""));
                        //需要拼接这几个参数 给Raid做准备
                        disk.setDrive(d.getId() + "");
                        disk.setRaid(phRaidMap.get(d.getId()));
                        disk.setEnclosureId(d.getEnclosureDeviceId());
                        disk.setControllerId(Integer.parseInt(d.getRefRAIDController().replace("RaidController-", "")));
                        disk.setManufactor(d.getManufacturer());
                        disk.setType(2 == d.getInterfaceType() ? "SAS" : "SSD");
                        diskList.add(disk);
                    });
                }

                //网卡
                valuePairs = new LinkedList<>();
                valuePairs.add(new BasicNameValuePair("token", header.get("token")));
                valuePairs.add(new BasicNameValuePair("str_input", inpuMap.get("nic")));
                encodedFormEntity = new UrlEncodedFormEntity(valuePairs);

                result = HttpFutureUtils.postHttps(String.format(getMultiUrl, ip), encodedFormEntity, "application/x-www-form-urlencoded; charset=UTF-8", header);
                HuaweiNicDTO huaweiNicDTO = gson.fromJson(decodeResponse(result), HuaweiNicDTO.class);
                huaweiNicDTO.getBusinessPort().forEach(n -> {
                    if (StringUtils.isBlank(n.getMacAddr())) {
                        return;
                    }
                    F2CPmNetworkCard networkCard = new F2CPmNetworkCard();
                    networkCard.setNumber(n.getObjName());
                    networkCard.setMac(n.getMacAddr());
                    networkCards.add(networkCard);
                });

                machineEntity.setDisks(diskList);
                machineEntity.setPmNetworkCards(networkCards);
            } catch (Exception e) {
                //session失效
                if (e instanceof com.google.gson.JsonSyntaxException) {
                    LogUtil.error("爬虫:" + ip + "检测到session失效e：" + e + " 开始重新登录！");
                    headerMap.remove(ip);
                    if (login(ip, userName, password)) {
                        return getMachineEntity(ip, userName, password);
                    }
                }
                LogUtil.error("爬虫:" + ip + "失效e：" + e);
                return null;
            }
        }
        setCPUandMemory(machineEntity, cpuDTOS, memoryDTOS);
        return machineEntity;
    }

    private void setCPUandMemory(MachineEntity
                                         machineEntity, List<HuaweiCpuDTO.CpuBean> cpuDTOS, List<HuaweiMemoryDTO.MemoryBean> memoryDTOS) {
        List<F2CPmCpu> cpus = new LinkedList<>();
        List<F2CPmMemory> memories = new LinkedList<>();

        cpuDTOS.forEach(c -> {
            F2CPmCpu cpu = new F2CPmCpu();
            cpu.setSn(c.getSN());
            cpu.setProcName(c.getVersion());
            cpu.setProcNumCores(c.getCoreCountThreadCount().split("/")[0]);
            cpu.setProcNumCoresEnabled(c.getCoreCountThreadCount().split("/")[0]);
            cpu.setProcNumThreads(c.getCoreCountThreadCount().split("/")[1]);
            cpu.setProcSocket(c.getObjName());
            cpu.setProcSpeed(c.getCurrentSpeed().replace("MHz", "").replace(" ", ""));
            cpu.setSyncTime(System.currentTimeMillis());
            cpus.add(cpu);
        });

        machineEntity.setCpu(cpus.size());

        memoryDTOS.forEach(c -> {
            if ("Unknown".equalsIgnoreCase(c.getCapacity()) || StringUtils.isBlank(c.getCapacity())) {
                return;
            }
            F2CPmMemory memory = new F2CPmMemory();
            memory.setMemModFrequency(c.getClockSpeed().replace("MHz", "").replace(" ", ""));
            memory.setMemModType(c.getType());
            memory.setMemModSize(Integer.parseInt(c.getCapacity().replace("MB", "").replace(" ", "")) / 1024 + "");
            memory.setMemModMinVolt(c.getMinimumVoltage() / 1000 + "");
            memory.setSn(c.getSN());
            memory.setSn(c.getSN());
            memories.add(memory);
        });
        machineEntity.setPmCpus(cpus);
        machineEntity.setPmMemories(memories);
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
        MetalPluginException.throwException("该插件暂时不支持Huawei组建Raid");
        return null;
    }

    @Override
    public JSONObject getDeleteRaidPayload() {
        MetalPluginException.throwException("暂时不支持");
        return null;
    }

    @Override
    public String getRaidWorkFlow() {
        MetalPluginException.throwException("暂时不支持");
        return null;
    }

    @Override
    public String getDeleteRaidWorkFlow() {
        MetalPluginException.throwException("暂时不支持");
        return null;
    }

    @Override
    public String getCatalogRaidWorkFlow() {
        MetalPluginException.throwException("暂时不支持");
        return null;
    }

    @Override
    public String getValidRaidType(String raidType) throws MetalPluginException {
        MetalPluginException.throwException("该插件暂时不支持Huawei组建Raid");
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
            String cpuTempStr = IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "CPU\\d_TEMP"), "\\|", 1, "degreesC");
            for (String s : cpuTempStr.split("\n")) {
                metric.getCpuTemp().add(Integer.valueOf(s));
            }

            //主板温度
            String mainBoardTempStr = IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "INLET\\d_\\d"), "\\|", 1, "degreesC");
            for (String s : mainBoardTempStr.split("\n")) {
                if (!"disabled".equalsIgnoreCase(s))
                    metric.setMainBoardTemp(Integer.valueOf(s));
            }

            //内存状态
            setMetricList(ipmiSdr, "MRB1_PVDDQ_CH\\d_\\d", metric.getMemoryStatus(), "");

            //电源状态
            setMetricList(ipmiSdr, "PMBPower_\\d", metric.getPowerStatus(), "");

            //电源瓦特
            String powerWattStr = IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "PMBPower_\\d"), "\\|", 1, "Watts");
            for (String s : powerWattStr.split("\n")) {
                metric.getPowerWatt().add(Integer.valueOf(s));
            }

            //风扇状态
            setMetricList(ipmiSdr, "FAN_\\d", metric.getFanStatus(), "");

            //磁盘状态
            setMetricList(ipmiSdr, "HDD\\d_Status", metric.getDisktatus(), "");
            return metric;
        } catch (Exception e) {
            MetalPluginException.throwException("查询监控数据出现异常！" + e);
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
