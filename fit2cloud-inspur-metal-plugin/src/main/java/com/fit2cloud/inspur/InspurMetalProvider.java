package com.fit2cloud.inspur;

import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.AbstractMetalProvider;
import com.fit2cloud.metal.sdk.F2CMetalPlugin;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.constants.InitMethod;
import com.fit2cloud.metal.sdk.model.*;
import com.fit2cloud.metal.sdk.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@F2CMetalPlugin
public class InspurMetalProvider extends AbstractMetalProvider {
    private static String name = "fit2cloud-inspur-metal-plugin";
    Logger logger = LoggerFactory.getLogger(InspurMetalProvider.class);

    private static final String JS_ENGINE_NAME = "nashorn";
    private static final ScriptEngineManager sem = new ScriptEngineManager();
    private static final ScriptEngine engine = sem.getEngineByName(JS_ENGINE_NAME);

    private static final Map<String, Map<String, String>> headersMap = new ConcurrentHashMap();

    private static final String loginUrl = "http://%s/rpc/WEBSES/create.asp";
    private static final String logoutUrl = "http://%s/rpc/WEBSES/logout.asp";
    private static final String overviewUrl = "http://%s/rpc/getfruinfo.asp";
    private static final String cpuUrl = "http://%s/rpc/getcpuinfo.asp";
    private static final String memoryUrl = "http://%s/rpc/getmeminfo.asp";

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
    public MachineEntity getMachineEntity(String ipmiSnmpRequestStr) throws MetalPluginException {
        if (login(ipmiSnmpRequestStr)) {
            IPMIRequest request = gson.fromJson(ipmiSnmpRequestStr, IPMIRequest.class);
            Map header = headersMap.get(request.getIp());
            Bindings bindings = engine.createBindings();

            try {

                //overview
                String response = HttpUtils.get(String.format(overviewUrl, request.getIp()), header);
                response = response.substring(response.indexOf("WEBVAR_JSONVAR"), response.indexOf("//Dynamic data end"));
                engine.eval(response);
                net.sf.json.JSONObject overviewObj = net.sf.json.JSONObject.fromObject(((Map) bindings.get("WEBVAR_JSONVAR_HL_GETALLFRUINFO"))).getJSONObject("WEBVAR_STRUCTNAME_HL_GETALLFRUINFO").getJSONObject("0");

                MachineEntity entity = new MachineEntity();
                entity.setBmcIp(request.getIp());
                entity.setBrand("Inspur");
                entity.setModel(overviewObj.getString("BI_BoardPartNum"));
                entity.setSerialNo(overviewObj.getString("PI_ProductSerialNum"));

                //cpu
                List<F2CCpu> cCpus = new LinkedList<>();
                response = HttpUtils.get(String.format(cpuUrl, request.getIp()), header);
                response = response.substring(response.indexOf("WEBVAR_JSONVAR"), response.indexOf("//Dynamic data end"));
                engine.eval(response);
                net.sf.json.JSONObject cpuArr = net.sf.json.JSONObject.fromObject(((Map) bindings.get("WEBVAR_JSONVAR_GETCPUINFO"))).getJSONObject("WEBVAR_STRUCTNAME_GETCPUINFO");
                Iterator cpuIt = cpuArr.keys();
                while (cpuIt.hasNext()) {
                    net.sf.json.JSONObject cpu = cpuArr.getJSONObject((String) cpuIt.next());
                    if (cpu.containsKey("Model")) {
                        F2CCpu cCpu = new F2CCpu();
                        cCpu.setModel(cpu.getString("Model"));
                        cCpu.setSlot(cpu.getString("CPUID"));
                        cCpu.setCore(cpu.getString("CoreNumber"));
                        cCpu.setManufactor(cpu.getString("Model").split(" ")[0]);
                        cCpu.setFreq("Intel(R) Xeon(R) CPU E5-2609 v4 @ 1.70GHz".substring("Intel(R) Xeon(R) CPU E5-2609 v4 @ 1.70GHz".indexOf("@") + 1).replace(" ", "").replace("GHz", ""));
                        cCpus.add(cCpu);
                    }
                }
                entity.setCpus(cCpus);
                entity.setCpu(cCpus.stream().mapToInt(c -> Integer.valueOf(c.getCore())).sum());

                //memory
                List<F2CMemory> memories = new LinkedList<>();
                response = HttpUtils.get(String.format(memoryUrl, request.getIp()), header);
                response = response.substring(response.indexOf("WEBVAR_JSONVAR"), response.indexOf("//Dynamic data end"));
                engine.eval(response);
                net.sf.json.JSONObject memoryArr = net.sf.json.JSONObject.fromObject(((Map) bindings.get("WEBVAR_JSONVAR_GETMEMINFO"))).getJSONObject("WEBVAR_STRUCTNAME_GETMEMINFO");
                Iterator memoryIt = memoryArr.keys();
                while (memoryIt.hasNext()) {
                    net.sf.json.JSONObject memory = memoryArr.getJSONObject((String) memoryIt.next());
                    if (memory.containsKey("Capacity") && memory.getInt("Present") == 1) {
                        F2CMemory memory1 = new F2CMemory();
                        memory1.setSlot(memory.getString("MEMID"));
                        memory1.setManufactor(memory.getString("Manufacture"));
                        memory1.setSize(memory.getString("Capacity"));
                        memory1.setFreq(memory.getString("Speed"));
                        memory1.setSn(memory.getString("SN"));
                        memories.add(memory1);
                    }
                }
                entity.setMemories(memories);
                entity.setMemory(memories.stream().mapToLong(m -> Long.valueOf(m.getSize())).sum());

                //:todo 增加网卡的读取

                return entity;

            } catch (ScriptException e) {
                logout(ipmiSnmpRequestStr);
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public JSONObject getRaidPayload(String raidConfigDTO) throws MetalPluginException {
        return null;
    }

    @Override
    public String getValidRaidType(String raidType) throws MetalPluginException {
        return null;
    }

    @Override
    public boolean login(String ipmiRequest) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequest, IPMIRequest.class);
        checkIPMIRequest(request);
        String response = HttpUtils.post(String.format(loginUrl, request.getIp()), String.format("WEBVAR_USERNAME=%s&WEBVAR_PASSWORD=%s", request.getUserName(), request.getPassword()));
        if (StringUtils.isBlank(response) || !response.contains("SESSION_COOKIE")) {
            logger.error("登录浪潮ip：%s,账号：%s，密码：%s失败！返回结果：%s", request.getIp(), request.getUserName(), request.getPassword(), response);
            return false;
        }

        Bindings bindings = engine.createBindings();
        try {
            response = response.substring(response.indexOf("WEBVAR_JSONVAR"), response.indexOf("//Dynamic data end"));
            engine.eval(response);
            net.sf.json.JSONArray resJsonArr = net.sf.json.JSONArray.fromObject(((Map) bindings.get("WEBVAR_JSONVAR_WEB_SESSION")).get("WEBVAR_STRUCTNAME_WEB_SESSION"));
            String sessionCookie = resJsonArr.getJSONObject(0).getJSONObject("0").getString("SESSION_COOKIE");
            if (sessionCookie.contains("Failure")) {
                logger.error("登录浪潮ip：%s,账号：%s，密码：%s失败！返回结果：%s", request.getIp(), request.getUserName(), request.getPassword(), sessionCookie);
                return false;
            }
            Map headerMap = new HashMap();
            headerMap.put("Cookie", String.format("SessionCookie=%s;", sessionCookie));
            headersMap.put(request.getIp(), headerMap);
            return true;
        } catch (ScriptException e) {
            logger.error("登录浪潮ip：%s,账号：%s，密码：%s失败！返回结果：%s", request.getIp(), request.getUserName(), request.getPassword(), e);
            throw new MetalPluginException(e);
        }
    }

    @Override
    public boolean logout(String ipmiRequest) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiRequest, IPMIRequest.class);
        checkIPMIRequest(request);
        if (headersMap.get(request.getIp()) == null) {
            return true;
        }
        HttpUtils.get(String.format(logoutUrl, request.getIp()), headersMap.get(request.getIp()));
        headersMap.remove(request.getIp());
        return true;
    }

    @Override
    public boolean validateCredential(String credential) throws MetalPluginException {
        return login(credential);
    }

    @Override
    public F2CMetrics getMetrics(String ipmiReqeuestStr) throws MetalPluginException {
        return null;
    }


}
