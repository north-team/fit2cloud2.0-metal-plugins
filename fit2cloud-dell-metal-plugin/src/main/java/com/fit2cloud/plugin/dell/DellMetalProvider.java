package com.fit2cloud.plugin.dell;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.AbstractMetalProvider;
import com.fit2cloud.metal.sdk.F2CMetalPlugin;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.constants.BareMetalConstants;
import com.fit2cloud.metal.sdk.constants.F2CResourceTypeConstants;
import com.fit2cloud.metal.sdk.model.F2CPmMetric;
import com.fit2cloud.metal.sdk.model.MachineEntity;
import com.fit2cloud.metal.sdk.model.PluginResult;
import com.fit2cloud.metal.sdk.model.RaidConfigDTO;
import com.fit2cloud.metal.sdk.model.request.IPMISnmpRequest;
import com.fit2cloud.metal.sdk.util.*;
import com.fit2cloud.plugin.dell.utils.IDrac6NewRestSpider;
import com.fit2cloud.plugin.dell.utils.IDrac6RestSpider;
import com.fit2cloud.plugin.dell.utils.IDrac7RestSpider;
import com.fit2cloud.plugin.dell.utils.IDrac8RestSpider;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

import static com.fit2cloud.metal.sdk.constants.RackHDConstants.workflowPostUrl;

@F2CMetalPlugin
public class DellMetalProvider extends AbstractMetalProvider {

    public DellMetalProvider() {
        super.name = "fit2cloud-dell-metal-plugin";
    }

    private static final String temperatureLocal = "1.3.6.1.4.1.674.10892.5.4.700.20.1.8";
    private static final String temperatureValue = "1.3.6.1.4.1.674.10892.5.4.700.20.1.6";
    private static final String powerStatusLocal = "1.3.6.1.4.1.674.10892.5.4.600.12.1.5";
    private static final String fanStatusLocal = "1.3.6.1.4.1.674.10892.5.4.700.12.1.5";
    private static final String diskStatusLocal = "1.3.6.1.4.1.674.10892.5.5.1.20.130.4.1.24";
    private static final String idracVersionLocal = "1.3.6.1.4.1.674.10892.2.1.1.2.0";
    private IDrac7RestSpider iDrac7RestSpider = new IDrac7RestSpider();
    private IDrac8RestSpider iDrac8RestSpider = new IDrac8RestSpider();
    private IDrac6RestSpider iDrac6RestSpider = new IDrac6RestSpider();
    private IDrac6NewRestSpider iDrac6NewRestSpider = new IDrac6NewRestSpider();

    @Override
    public Map<String, String> getHeader(String ip) {
        return null;
    }

    @Override
    public PluginResult login(String ipmiRequestStr) throws MetalPluginException {
        IPMISnmpRequest request = gson.fromJson(ipmiRequestStr, IPMISnmpRequest.class);
        checkIPMIParameter(request);
        String version = getIdracVersion(request.getHost());
        LogUtil.info(String.format("开始Dell登录%s,获取到的iDrac版本为%s", ipmiRequestStr, version));
        if ("iDRAC7".equalsIgnoreCase(version)) {
            if (iDrac7RestSpider.login(request.getHost(), request.getUserName(), request.getPwd())) {
                LogUtil.error(String.format("Dell idrac7 %s 登录成功！" + gson.toJson(request), request.getHost()));
                return PluginResult.success();
            }
        } else if ("iDRAC8".equalsIgnoreCase(version)) {
            if (iDrac8RestSpider.login(request.getHost(), request.getUserName(), request.getPwd())) {
                LogUtil.error(String.format("Dell idrac8 %s 登录成功！" + gson.toJson(request), request.getHost()));
                return PluginResult.success();
            }
        } else if ("iDRAC6".equalsIgnoreCase(version)) {
            if (iDrac6RestSpider.login(request.getHost(), request.getUserName(), request.getPwd())) {
                LogUtil.error(String.format("Dell idrac6 %s 登录成功！" + gson.toJson(request), request.getHost()));
                return PluginResult.success();
            } else if (iDrac6NewRestSpider.login(request.getHost(), request.getUserName(), request.getPwd())) {
                LogUtil.error(String.format("Dell idrac6 新版本%s 登录成功！" + gson.toJson(request), request.getHost()));
                return PluginResult.success();
            }
        } else {
            if (iDrac8RestSpider.login(request.getHost(), request.getUserName(), request.getPwd())) {
                LogUtil.error(String.format("Dell idrac8 %s 登录成功！" + gson.toJson(request), request.getHost()));
                return PluginResult.success();
            }
        }
        LogUtil.error("登录失败！" + gson.toJson(request));
        return PluginResult.error("登录失败！");
    }

    @Override
    public PluginResult logout(String ipmiRequestStr) throws MetalPluginException {
        IPMISnmpRequest request = gson.fromJson(ipmiRequestStr, IPMISnmpRequest.class);
        String version = getIdracVersion(request.getHost());
        LogUtil.info(String.format("开始Dell登出%s,获取到的iDrac版本为%s", ipmiRequestStr, version));
        if ("iDRAC7".equalsIgnoreCase(version)) {
            if (iDrac7RestSpider.logout(request.getHost())) {
                LogUtil.error(String.format("Dell idrac7 %s 登出成功！" + gson.toJson(request), request.getHost()));
                return PluginResult.success();
            }
        } else if ("iDRAC8".equalsIgnoreCase(version)) {
            if (iDrac8RestSpider.logout(request.getHost())) {
                LogUtil.error(String.format("Dell idrac8 %s 登出成功！" + gson.toJson(request), request.getHost()));
                return PluginResult.success();
            }
        } else if ("iDRAC6".equalsIgnoreCase(version)) {
            if (iDrac6RestSpider.logout(request.getHost())) {
                LogUtil.error(String.format("Dell idrac6 %s 登出成功！" + gson.toJson(request), request.getHost()));
                return PluginResult.success();
            } else if (iDrac6NewRestSpider.logout(request.getHost())) {
                LogUtil.error(String.format("Dell idrac6 新版本%s 登出成功！" + gson.toJson(request), request.getHost()));
                return PluginResult.success();
            }
        } else {
            if (iDrac8RestSpider.logout(request.getHost())) {
                LogUtil.error(String.format("Dell idrac8 %s 登出成功！" + gson.toJson(request), request.getHost()));
                return PluginResult.success();
            }
        }
        LogUtil.error("登出失败！" + gson.toJson(request));
        return PluginResult.error("登出失败！");
    }

    /**
     * 由于南方中心宿主机不开通snmp 因此全部走rest爬虫 其他项目根据物理机的配置情况选择适合的
     *
     * @param ipmiSnmpRequestStr
     * @return
     * @throws MetalPluginException
     */
    @Override
    public MachineEntity getMachineEntity(String ipmiSnmpRequestStr) throws MetalPluginException {

        IPMISnmpRequest request = gson.fromJson(ipmiSnmpRequestStr, IPMISnmpRequest.class);
        checkIPMIParameter(request);
        String ip = request.getHost();
//        MachineEntity entity = new MachineEntity();

//        if (IpUtil.ping(ip)) {
//            try {
//                entity = new DellSnmpHelper(request.getHost(), request.getCommunity(), request.getPort()).getMachineEntry();
//            } catch (Exception e) {
//                try {
//                    entity = new DellSnmpHelper(request.getHost(), "public", 161).getMachineEntry();
//                } catch (Exception e2) {
//                    try {
//                        entity = new DellSnmpHelper(request.getHost(), request.getCommunity(), 161).getMachineEntry();
//                    } catch (Exception e3) {
//                    }
//                }
//            }
//            String bmcResult = null;
//
//            try {
//                bmcResult = IPMIUtils.exeCommand(request, "lan print");
//                JSONObject lanObj = IPMIUtils.transform(bmcResult);
//                if (lanObj.containsKey("MAC Address")) {
//                    entity.setBmcMac(lanObj.getString("MAC Address"));
//                }
//            } catch (Exception e) {
//                LogUtil.error("爬取Dell:" + ip + "时，通过IPMI获取BMC网卡信息失败!" + e);
//            }
//            entity.setBmcIp(ip);
//            List<F2CPmMemory> memoryDTOS = entity.getPmMemories();
//            if (memoryDTOS.size() > 0) {
//                //内存存两份一份存extendInfo作为直接展示 另一份存另一个表
//                List<JSONObject> memoryDetails = new ArrayList<>();
//                memoryDTOS.forEach(memory -> {
//                    JSONObject memoryDetail = new JSONObject();
//                    memoryDetail.put("memorySize", memory.getMemModSize());
//                    memoryDetail.put("memoryType", memory.getMemModType());
//                    memoryDetails.add(memoryDetail);
//                });
//                HashMap<String, String> extendInfo = new HashMap<>();
//                extendInfo.put("memoryDetails", memoryDetails.toString());
//                entity.setExtendInfo(extendInfo);
//            }
//
//            return entity;
//        } else {
        if (IpUtil.canConnect(ip)) {
            LogUtil.info("爬取未开通ping命令的机器硬件信息！");
            String idracVersion = getIdracVersion(ip);
            if ("iDRAC7".equalsIgnoreCase(idracVersion)) {
                return iDrac7RestSpider.getMachineEntity(request.getHost(), request.getUserName(), request.getPwd());
            } else if ("iDRAC8".equalsIgnoreCase(idracVersion)) {
                return iDrac8RestSpider.getMachineEntity(request.getHost(), request.getUserName(), request.getPwd());
            } else if ("iDRAC6".equalsIgnoreCase(idracVersion)) {
                //idrac6 也有多个子版本
                if (iDrac6NewRestSpider.login(request.getHost(), request.getUserName(), request.getPwd())) {
                    return iDrac6NewRestSpider.getMachineEntity(request.getHost(), request.getUserName(), request.getPwd());
                }
                return iDrac6RestSpider.getMachineEntity(request.getHost(), request.getUserName(), request.getPwd());
            } else {
                return iDrac8RestSpider.getMachineEntity(request.getHost(), request.getUserName(), request.getPwd());
            }
        }
        return null;
    }

    @Override
    public JSONObject getRaidPayLoad(String raidConfigRequestStr) throws MetalPluginException {
        RaidConfigDTO raidConfigDTO = gson.fromJson(raidConfigRequestStr, RaidConfigDTO.class);
        if (raidConfigDTO.getRaidConfigs().size() == 0) {
            return null;
        }
        JSONObject payload = JSONObject.parseObject(getPageTemplate(F2CResourceTypeConstants.RACKHD_RAID_PAYLOAD));

        List<RaidConfigDTO.OneRaidConfig> raidConfigList = raidConfigDTO.getRaidConfigs();
        JSONArray raidList = new JSONArray();
        for (int i = 0; i < raidConfigList.size(); i++) {
            JSONObject raid = new JSONObject();
            RaidConfigDTO.OneRaidConfig raidConfig = raidConfigList.get(i);
            raid.put("enclosure", raidConfig.getRaidDisks().get(0).getEnclosureId());
            raid.put("type", getValidRaidType(raidConfig.getRaidType()));
            raid.put("name", "VD" + i);
            int[] drives = new int[raidConfig.getRaidDisks().size()];

            for (int j = 0; j < drives.length; j++) {
                drives[j] = Integer.parseInt(raidConfig.getRaidDisks().get(j).getDrive());
            }

            raid.put("drives", drives);
            //如果是raid10/50/60 则必须添加参数 drivePerArray
            if ("raid10".equalsIgnoreCase(getValidRaidType(raidConfig.getRaidType())) ||
                    "raid50".equalsIgnoreCase(getValidRaidType(raidConfig.getRaidType())) ||
                    "raid60".equalsIgnoreCase(getValidRaidType(raidConfig.getRaidType()))) {
                raid.put("drivePerArray", 2);
            }
            raidList.add(raid);
        }

        JSONObject options = payload.getJSONObject("options");
        JSONObject bootstrap = payload.getJSONObject("bootstrap-rancher");
        JSONObject createRaid = options.getJSONObject("create-raid");
        createRaid.put("bootstrap-rancher", bootstrap);
        createRaid.put("raidList", raidList);
        return payload;
    }


    @Override
    public JSONObject getDeleteRaidPayload() {
        try {
            return JSONObject.parseObject(getPageTemplate(F2CResourceTypeConstants.RACKHD_RAID_DEL_PAYLOAD));
        } catch (MetalPluginException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    @Override
    public String getRaidWorkFlow() {
        return workflowPostUrl + "Graph.Raid.Create.PercRAID";
    }

    @Override
    public String getDeleteRaidWorkFlow() {
        return workflowPostUrl + "Graph.Raid.Delete.MegaRAID";
    }

    @Override
    public String getCatalogRaidWorkFlow() {
        return workflowPostUrl + "Graph.Dell.perccli.Catalog";
    }

    @Override
    public String getValidRaidType(String raidType) throws MetalPluginException {
        return raidType;
    }

    @Override
    public F2CPmMetric getMetric(String ipmiSnmpRequestStr) throws MetalPluginException {
        IPMISnmpRequest snmpRequest = gson.fromJson(ipmiSnmpRequestStr, IPMISnmpRequest.class);
        checkIPMISnmpParameter(snmpRequest);
        try {
            SnmpWorker snmpWorker = new SnmpWorker(snmpRequest.getHost(), snmpRequest.getCommunity(), snmpRequest.getPort());
            F2CPmMetric metric = new F2CPmMetric();
            Map<String, String> temperatureMap = snmpWorker.walk(temperatureLocal);
            Map<String, String> powerMap = snmpWorker.walk(powerStatusLocal);
            Map<String, String> fanMap = snmpWorker.walk(fanStatusLocal);
            Map<String, String> diskMap = snmpWorker.walk(diskStatusLocal);
            // 日志信息
            String selStr = IPMIUtils.exeCommand(snmpRequest, "sel");
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
            for (Map.Entry<String, String> entry : temperatureMap.entrySet()) {
                if (entry.getValue().contains("CPU")) {
                    metric.getCpuTemp().add(Integer.valueOf(snmpWorker.getAsString(entry.getKey().replace(temperatureLocal, temperatureValue))) / 10);
                }
                if (entry.getValue().contains("System Board Inlet Temp")) {
                    metric.setMainBoardTemp(Integer.valueOf(snmpWorker.getAsString(entry.getKey().replace(temperatureLocal, temperatureValue))) / 10);
                }
            }
            for (Map.Entry<String, String> entry : powerMap.entrySet()) {
                metric.getPowerStatus().add(unifyStatys(entry.getValue()));
            }
            for (Map.Entry<String, String> entry : fanMap.entrySet()) {
                metric.getFanStatus().add(unifyStatys(entry.getValue()));
            }
            for (Map.Entry<String, String> entry : diskMap.entrySet()) {
                metric.getDisktatus().add(unifyStatys(entry.getValue()));
            }
            String idracVersion = snmpWorker.getAsString(idracVersionLocal);
            if ("iDRAC7".equalsIgnoreCase(idracVersion)) {
                metric.getPowerWatt().add(iDrac7RestSpider.getPowerMetric(snmpRequest.getHost(), snmpRequest.getUserName(), snmpRequest.getPwd()));
            } else if ("iDRAC8".equalsIgnoreCase(idracVersion)) {
                metric.getPowerWatt().add(iDrac8RestSpider.getPowerMetric(snmpRequest.getHost(), snmpRequest.getUserName(), snmpRequest.getPwd()));
            } else {
                LogUtil.info(String.format("ip:%s,不支持的idrac版本%s", snmpRequest.getHost(), idracVersion));
            }
            return metric;
        } catch (Exception e) {
            MetalPluginException.throwException(String.format("Dell:%s监控信息获取失败！", snmpRequest.getHost()));
        }
        return null;
    }

    private Integer unifyStatys(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Integer.valueOf(value) == 3 ? BareMetalConstants.HEALTHY : BareMetalConstants.ERROR;
    }

    public String getIdracVersion(String ip) {
        String index = HttpFutureUtils.getHttps(String.format("https://%s/login.html", ip), null);
        LogUtil.info(String.format("获取idracVersion:%s,返回：【%s】", ip, index));
        if (StringUtils.isBlank(index)) return "unknown";
        if (index.contains("Controller 7")) {
            return "iDRAC7";
        }
        if (index.contains("Controller 6")) {
            return "iDRAC6";
        }
        if (index.contains("titleLbl_id")) {
            return "iDRAC8";
        }
        return "iDRAC7";
    }

    public static void main(String[] args) {
        System.out.println(HttpFutureUtils.getHttps("https://10.132.47.99/login.html", null));
    }
}
