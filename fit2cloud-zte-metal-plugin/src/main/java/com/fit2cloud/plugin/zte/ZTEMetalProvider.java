package com.fit2cloud.plugin.zte;

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
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

import static com.fit2cloud.metal.sdk.constants.RackHDConstants.workflowPostUrl;

@F2CMetalPlugin
public class ZTEMetalProvider extends AbstractMetalProvider {

    public ZTEMetalProvider() {
        super.name = "fit2cloud-zte-metal-plugin";
    }

    private static final String temperatureLocal = "1.3.6.1.4.1.674.10892.5.4.700.20.1.8";
    private static final String temperatureValue = "1.3.6.1.4.1.674.10892.5.4.700.20.1.6";
    private static final String powerStatusLocal = "1.3.6.1.4.1.674.10892.5.4.600.12.1.5";
    private static final String fanStatusLocal = "1.3.6.1.4.1.674.10892.5.4.700.12.1.5";
    private static final String diskStatusLocal = "1.3.6.1.4.1.674.10892.5.5.1.20.130.4.1.24";

    @Override
    public Map<String, String> getHeader(String ip) {
        return null;
    }

    @Override
    public PluginResult login(String ipmiRequestStr) throws MetalPluginException {
        IPMISnmpRequest request = gson.fromJson(ipmiRequestStr, IPMISnmpRequest.class);
        checkIPMIParameter(request);
        return PluginResult.error("登录失败！");
    }

    @Override
    public PluginResult logout(String ipmiRequestStr) throws MetalPluginException {

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
        return workflowPostUrl + "Graph.Raid.Create.MegaRAID";
    }

    @Override
    public String getDeleteRaidWorkFlow() {
        return workflowPostUrl + "Graph.Raid.Delete.MegaRAID";
    }

    @Override
    public String getCatalogRaidWorkFlow() {
        return workflowPostUrl + "Graph.Quanta.storcli.Catalog";
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

            return metric;
        } catch (Exception e) {
            MetalPluginException.throwException(String.format("zte:%s监控信息获取失败！", snmpRequest.getHost()));
        }
        return null;
    }

    private Integer unifyStatys(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Integer.valueOf(value) == 3 ? BareMetalConstants.HEALTHY : BareMetalConstants.ERROR;
    }
}
