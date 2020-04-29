package com.fit2cloud.dell;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.AbstractMetalProvider;
import com.fit2cloud.metal.sdk.F2CMetalPlugin;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.constants.F2CResourceType;
import com.fit2cloud.metal.sdk.constants.InitMethod;
import com.fit2cloud.metal.sdk.model.*;
import com.fit2cloud.metal.sdk.util.DataFormatUtil;
import com.fit2cloud.metal.sdk.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@F2CMetalPlugin
public class DellMetalProvider extends AbstractMetalProvider {
    private static String name = "fit2cloud-dell-metal-plugin";
    Logger logger = LoggerFactory.getLogger(DellMetalProvider.class);

    //web控制台 Http接口
    private static final String loginUrl = "https://%s/data/login";
    private static final String logoutUrl = "https://%s/data/logout";

    private static final ConcurrentHashMap<String, Map<String, String>> headersMap = new ConcurrentHashMap();

    public String getName() {
        return name;
    }

    @Override
    public List<InitMethod> getSupportedInitMethod(String request) throws MetalPluginException {
        return Arrays.asList(InitMethod.LOGIN, InitMethod.RESTAPI, InitMethod.LOGOUT, InitMethod.SNMP, InitMethod.IPMI);
    }

    @Override
    public String getPlatformVersion(String request) throws MetalPluginException {
        return "暂时不支持该方法！";
    }

    @Override
    public Map<String, String> getHeader(String ip) {
        return headersMap.get(ip);
    }

    @Override
    public MachineEntity getMachineEntity(String ipmiSnmpRequestStr) throws MetalPluginException {
        if (login(ipmiSnmpRequestStr)) {
            IPMIRequest request = gson.fromJson(ipmiSnmpRequestStr, IPMIRequest.class);
            Map header = headersMap.get(request.getIp());
            //:todo https://r720.dahaia123.top/ 账号密码: root/calvin 读取硬件信息 参考inspur

            try {
                return null;

            } catch (Exception e) {
                logout(ipmiSnmpRequestStr);
            }

        }
        return null;
    }

    @Override
    public JSONObject getRaidPayload(String raidConfigDTOStr) throws MetalPluginException {

        //:todo 装配成rackhd 能支持的格式 注意多raid阵列支持
        F2CRaidConfigDTO raidConfigDTO = gson.fromJson(raidConfigDTOStr, F2CRaidConfigDTO.class);
        JSONObject raidPayload = JSONObject.parseObject(getPageTemplate());
        JSONObject createRaid = raidPayload.getJSONObject("options").getJSONObject("create-raid");
        JSONArray raidList = new JSONArray();

        for (F2CRaidConfigDTO.RaidConfig c : raidConfigDTO.getRaidConfigList()) {
            JSONObject raidConfigObj = new JSONObject();
            raidConfigObj.put("type", getValidRaidType(c.getRaid()));
            raidConfigObj.put("drives", c.getDisks().stream().sorted(Comparator.comparing(F2CPhysicalDisk::getDrive)).map(d -> d.getEnclosureId() + " " + d.getDrive() + " ").reduce(" ", String::concat).trim());
            raidList.add(raidConfigObj);
        }
        createRaid.put("raidList", raidList);
        return raidPayload;
    }

    @Override
    public JSONObject getDeletePayload() {
        try {
            //:todo 装配成rackhd 能支持的格式
            return JSONObject.parseObject(getPageTemplate(F2CResourceType.RACKHD_RAID_DEL_PAYLOAD));
        } catch (MetalPluginException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    @Override
    public String getRaidWorkFlow() {
        //:todo 找到南区办公室RackHD对应该机型的创建raid的workflow的名字
        return workflowPostUrl + "Graph.Raid.Create.AdaptecRAID";
    }

    @Override
    public String getDeleteRaidWorkFlow() {
        //:todo 找到南区办公室RackHD对应该机型的删除raid的workflow的名字
        return workflowPostUrl + "Graph.Raid.Delete.AdaptecRAID";
    }

    @Override
    public String getCatalogRaidWorkFlow() {
        //:todo 找到南区办公室RackHD对应该机型的搜集raid的workflow的名字
        return workflowPostUrl + "Graph.Raid.Catalog.AdaptecRAID";
    }

    @Override
    public String getValidRaidType(String raidType) throws MetalPluginException {
        //:todo 找到南区办公室RackHD对应该机型的创建raid的时，参数里面raid的表示格式 如inspur的raid10就是10，dell的需要你去发现
        return raidType.replace("raid", "");
    }

    @Override
    public boolean login(String ipmiRequest) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequest, IPMIRequest.class);
        checkIPMIRequest(request);
        //登录该机型的http方法
        String ip = request.getIp();
        String userName = request.getUserName();
        String pwd = request.getPassword();
        if (headersMap.get(ip) == null) {
            String payload = String.format("user=%s&password=%s", userName, pwd);
            Map headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            String rst = HttpUtils.post(String.format(loginUrl, ip), payload, headers, headersMap);
            try {
                // String 类型的 xml 数据转 json
                JSONObject jsonObject = JSONObject.parseObject(DataFormatUtil.XmlToJson(rst)).getJSONObject("root");
                if(jsonObject.getInteger("authResult") == 0){ //登录成功 authResult 才为 0
                    // 取出请求成功后的 cookie
                    Map<String, String> cookieMap = headersMap.get("login");
                    if(null != cookieMap){
                        headersMap.put(ip, cookieMap);
                        headersMap.remove("login");
                        return true;
                    }
                }else {
                    logger.error("登录 Dell ip：%s,账号：%s，密码：%s失败！返回结果：%s", request.getIp(), request.getUserName(), request.getPassword(), rst);
                    return false;
                }
            }catch (Exception e) {
                logger.error("登录 Dell ip：%s,账号：%s，密码：%s失败！返回结果：%s", request.getIp(), request.getUserName(), request.getPassword(), e);
                throw new MetalPluginException(e);
            }
        }
        return false;
    }

    @Override
    public boolean logout(String ipmiRequest) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequest, IPMIRequest.class);
        checkIPMIRequest(request);
        if (headersMap.get(request.getIp()) == null) {
            return true;
        }
        Map headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.putAll(headersMap.get(request.getIp()));// 原登录用户 cookie

        HttpUtils.post(String.format(logoutUrl, request.getIp()), null, headers);
        headersMap.remove(request.getIp());
        return true;
    }

    @Override
    public F2CMetrics getMetrics(String ipmiReqeuestStr) throws MetalPluginException {
        return null;
    }

}
