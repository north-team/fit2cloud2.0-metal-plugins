package com.fit2cloud.dell;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.AbstractMetalProvider;
import com.fit2cloud.metal.sdk.F2CMetalPlugin;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.constants.F2CResourceType;
import com.fit2cloud.metal.sdk.constants.InitMethod;
import com.fit2cloud.metal.sdk.constants.PluginConstants;
import com.fit2cloud.metal.sdk.model.*;
import com.fit2cloud.metal.sdk.util.DataFormatUtil;
import com.fit2cloud.metal.sdk.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@F2CMetalPlugin
public class DellMetalProvider extends AbstractMetalProvider {
    private static String name = "fit2cloud-dell-metal-plugin";
    Logger logger = LoggerFactory.getLogger(DellMetalProvider.class);

    //web控制台 Http接口
    private static final String loginUrl = "https://%s/data/login";
    private static final String logoutUrl = "https://%s/data/logout";
    private static final String overViewUrl = "https://%s/data?get="; //请求硬件数据的通用前缀
    private static final String cpuUrl = "https://%s/sysmgmt/2012/server/processor"; //获取 cpu
    private static final String cacheUrl = "https://%s/sysmgmt/2012/server/cache?processor="; //获取当前插槽 cpu 各级缓存大小

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
            try {
                //:todo https://r720.dahaia123.top/ 账号密码: root/calvin 读取硬件信息 参考inspur
                MachineEntity entity = new MachineEntity();
                List<F2CCpu> cpus = new LinkedList<>(); //cpu
                List<F2CMemory> memories = new LinkedList<>(); //内存
                List<F2CPhysicalDisk> disks = new LinkedList<>(); //磁盘
                List<F2CPmNetworkCard> pmNetworkCards = new LinkedList<>(); //网卡

                //爬取基本数据
                String url = overViewUrl + "sysDesc,nodeId";
                String response = HttpUtils.get(String.format(url, request.getIp()), header);
                JSONObject jsonObject = JSONObject.parseObject(DataFormatUtil.XmlToJson(response)).getJSONObject("root");
                if(jsonObject.getString("status").equals("ok")){
                    entity.setName(PluginConstants.DELL + " " +jsonObject.getString("sysDesc"));
                    entity.setBmcIp(request.getIp());
                    entity.setBrand(PluginConstants.DELL);
                    entity.setSerialNo(jsonObject.getString("nodeId"));
                }

                //  获取 cpu 信息 header 需添加 X_SYSMGMT_OPTIMIZE 参数为 true
                header.put("X_SYSMGMT_OPTIMIZE", "true");
                String cpuResponse = HttpUtils.get(String.format(cpuUrl, request.getIp()), header);
                net.sf.json.JSONObject jsonObjectCpu = net.sf.json.JSONObject.fromObject(cpuResponse);
                net.sf.json.JSONObject Processor = jsonObjectCpu.getJSONObject("Processor");
                Iterator cpuIt = Processor.keys();
                while (cpuIt.hasNext()) {
                    net.sf.json.JSONObject cpu = Processor.getJSONObject((String) cpuIt.next()); //每个 cpu 的信息
                    F2CCpu cCpu = new F2CCpu();
                    cCpu.setProcName(cpu.getString("brand"));

                    String procSocket = (cpu.getString("cache")).substring((cpu.getString("cache")).indexOf("processor=") + 10); //插槽名

                    cCpu.setProcSocket(procSocket);
                    cCpu.setProcNumCores(String.valueOf(cpu.getInt("core_count")));
                    cCpu.setProcSpeed(String.valueOf(cpu.getInt("current_speed")) + "MHz");
                    //获取当前插槽 cpu 各级缓存大小
                    String param = URLEncoder.encode(procSocket, "UTF-8"); //特殊字符转码
                    String cacheResponse = HttpUtils.get(String.format(cacheUrl, request.getIp()) + param, header);
                    net.sf.json.JSONObject jsonObjectCache = net.sf.json.JSONObject.fromObject(cacheResponse);
                    net.sf.json.JSONObject Cache = jsonObjectCache.getJSONObject("Cache");
                    Iterator cacheIt = Cache.keys();
                    int i = 0;
                    while (cacheIt.hasNext() && i<3) {
                        net.sf.json.JSONObject cache = Cache.getJSONObject((String) cacheIt.next()); //每级缓存的信息
                        if(null != cache && cache.getInt("level") == 0){
                            cCpu.setProcNumL1cache(cache.getInt("max_size") == 0 ? null : String.valueOf(cache.getInt("max_size")));
                        }
                        if(null != cache && cache.getInt("level") == 1){
                            cCpu.setProcNumL2cache(cache.getInt("max_size") == 0 ? null : String.valueOf(cache.getInt("max_size")));
                        }
                        if(null != cache && cache.getInt("level") == 2){
                            cCpu.setProcNumL3cache(cache.getInt("max_size") == 0 ? null : String.valueOf(cache.getInt("max_size")));
                        }
                        i++;
                    }
                    cpus.add(cCpu);
                }
                entity.setCpus(cpus);
                entity.setCpu(cpus.size());



                //:todo 内存


                //:todo 网卡



                return null;

            } catch (Exception e) {
                logout(ipmiSnmpRequestStr);
                logger.error("插件获取物理机信息失败");
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
                        // Dell 还需要把登录请求返回的 ST2 拿到，后续请求需要带上这个参数
                        String forwardUrl = jsonObject.getString("forwardUrl");
                        String ST2 = forwardUrl.substring(forwardUrl.indexOf("ST2=") + 4);// ST2= 占 4 个长度
                        cookieMap.put("ST2", ST2);
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
