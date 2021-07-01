package com.fit2cloud.plugin.h3c;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.AbstractMetalProvider;
import com.fit2cloud.metal.sdk.F2CMetalPlugin;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.constants.BareMetalConstants;
import com.fit2cloud.metal.sdk.constants.F2CResourceTypeConstants;
import com.fit2cloud.metal.sdk.model.*;
import com.fit2cloud.metal.sdk.model.request.IPMIRequest;
import com.fit2cloud.metal.sdk.util.ExceptionUtils;
import com.fit2cloud.metal.sdk.util.IPMIUtils;
import com.fit2cloud.metal.sdk.util.LogUtil;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.fit2cloud.metal.sdk.constants.RackHDConstants.workflowPostUrl;

@F2CMetalPlugin
public class H3CMetalProvider extends AbstractMetalProvider {

    public H3CMetalProvider() {
        super.name = "fit2cloud-h3c-metal-plugin";
    }


    private static Gson gson;

    static {
        try {
            gson = new Gson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getHeader(String ip) {
        return null;
    }

    @Override
    public PluginResult login(String ipmiRequestStr) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequestStr, IPMIRequest.class);
        String ip = request.getHost();
        String userName = request.getUserName();
        String password = request.getPwd();
        checkIPMIParameter(request);

        return PluginResult.error("");
    }

    @Override
    public PluginResult logout(String ipmiRequestStr) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequestStr, IPMIRequest.class);
        String ip = request.getHost();
        String userName = request.getUserName();
        String password = request.getPwd();
        if (login(ipmiRequestStr).isSuccess()) {
        } else {
            MetalPluginException.throwException(String.format("登出华3%s失败！", ip));
        }
        return PluginResult.success();
    }

    @Override
    public MachineEntity getMachineEntity(String ipmiRequestStr) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequestStr, IPMIRequest.class);
        String ip = request.getHost();
        String userName = request.getUserName();
        String password = request.getPwd();
        checkIPMIParameter(request);

        if (login(ipmiRequestStr).isSuccess()) {
            try {

            } catch (Exception e) {
                LogUtil.error(String.format("爬取华3ip:%s时发生异常%s   ", ip, ExceptionUtils.getExceptionDetail(e)));
                return null;
            }
        } else {
            MetalPluginException.throwException(String.format("登录华3%s失败！", ip));
        }
        return null;
    }

    @Override
    public JSONObject getRaidPayLoad(String raidConfigDTOStr) throws MetalPluginException {
        RaidConfigDTO raidConfigDTO = gson.fromJson(raidConfigDTOStr, RaidConfigDTO.class);
        JSONObject raidPayload = JSONObject.parseObject(getPageTemplate());
        JSONObject createRaid = raidPayload.getJSONObject("options").getJSONObject("create-raid");
        JSONArray raidList = new JSONArray();

        for (RaidConfigDTO.OneRaidConfig c : raidConfigDTO.getRaidConfigs()) {
            JSONObject raidConfigObj = new JSONObject();
            raidConfigObj.put("type", getValidRaidType(c.getRaidType()));
            raidConfigObj.put("drives", c.getRaidDisks().stream().sorted(Comparator.comparing(F2CPmDisk::getDrive)).map(d -> d.getEnclosureId() + " " + d.getDrive() + " ").reduce(" ", String::concat).trim());
            raidList.add(raidConfigObj);
        }
        createRaid.put("raidList", raidList);
        return raidPayload;
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
        return workflowPostUrl + "Graph.Raid.Create.AdaptecRAID";
    }

    @Override
    public String getDeleteRaidWorkFlow() {
        return workflowPostUrl + "Graph.Raid.Delete.AdaptecRAID";
    }

    @Override
    public String getCatalogRaidWorkFlow() {
        return workflowPostUrl + "Graph.Raid.Catalog.AdaptecRAID";
    }

    @Override
    public String getValidRaidType(String raidType) throws MetalPluginException {
        return raidType.replace("raid", "");
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
            String cpuTempStr = IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "CPU\\d_T"), "\\|", 1, "degreesC");
            for (String s : cpuTempStr.split("\n")) {
                if ("noreading".equalsIgnoreCase(s)) {
                    continue;
                }
                metric.getCpuTemp().add(Double.valueOf(s).intValue());
            }

            //主板温度
            String mainBoardTempStr = Optional.ofNullable(IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "INLET\\d_\\d"), "\\|", 1, "degreesC")).orElse(IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "Inlet_Temp"), "\\|", 1, "degreesC"));
            for (String s : mainBoardTempStr.split("\n")) {
                if (!"disabled".equalsIgnoreCase(s))
                    metric.setMainBoardTemp(Double.valueOf(s).intValue());
            }

            //内存状态
            setMetricList(ipmiSdr, "MRB1_PVDDQ_CH\\d_\\d", "MEM_CH\\w\\d_Status", metric.getMemoryStatus(), "");

            //电源状态
            setMetricList(ipmiSdr, "PMBPower_\\d", "PSU\\d_Supply", metric.getPowerStatus(), "");

            //电源瓦特Total_Power
            String powerWattStr = Optional.ofNullable(IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "PMBPower_\\d"), "\\|", 1, "Watts")).orElse(IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, "Total_Power"), "\\|", 1, "Watts"));
            for (String s : powerWattStr.split("\n")) {
                metric.getPowerWatt().add(Double.valueOf(s).intValue());
            }

            //风扇状态
            setMetricList(ipmiSdr, "FAN_\\d", metric.getFanStatus(), "");

            //磁盘状态
            setMetricList(ipmiSdr, "HDD\\d+_Status", metric.getDisktatus(), "");
            return metric;
        } catch (Exception e) {
            MetalPluginException.throwException("查询监控数据出现异常！" + ExceptionUtils.getExceptionDetail(e));
        }
        return null;
    }

    private void setMetricList(String ipmiSdr, String regex1, String regex2, List<Integer> disktatus, String replaceVal) {
        String diskStatusStr = Optional.ofNullable(IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, regex1), "\\|", 2, replaceVal)).orElse(IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, regex2), "\\|", 2, replaceVal));
        if (StringUtils.isBlank(diskStatusStr)) return;
        for (String s : diskStatusStr.split("\n")) {
            disktatus.add("ok".equalsIgnoreCase(s) ? BareMetalConstants.HEALTHY : "ns".equalsIgnoreCase(s) || "nc".equalsIgnoreCase(s) ? BareMetalConstants.NOTDETECTED : BareMetalConstants.ERROR);
        }
    }

    private void setMetricList(String ipmiSdr, String regex1, List<Integer> disktatus, String replaceVal) {
        String diskStatusStr = IPMIUtils.extractSdrIndexValue(IPMIUtils.grep(ipmiSdr, regex1), "\\|", 2, replaceVal);
        if (StringUtils.isBlank(diskStatusStr)) return;
        for (String s : diskStatusStr.split("\n")) {
            disktatus.add("ok".equalsIgnoreCase(s) ? BareMetalConstants.HEALTHY : "ns".equalsIgnoreCase(s) || "nc".equalsIgnoreCase(s) ? BareMetalConstants.NOTDETECTED : BareMetalConstants.ERROR);
        }
    }

}
