package com.fit2cloud.plugin.dell;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

public class LoginTest {
    private static final String JS_ENGINE_NAME = "nashorn";
    private static final ScriptEngineManager sem = new ScriptEngineManager();
    private static final ScriptEngine engine = sem.getEngineByName(JS_ENGINE_NAME);

    public static void main2(String[] args) throws ScriptException {
        Bindings bindings = engine.createBindings();
//        Object a = engine.eval(" WEBVAR_JSONVAR_WEB_SESSION = \n" +
//                " { \n" +
//                " WEBVAR_STRUCTNAME_WEB_SESSION : \n" +
//                " [ \n" +
//                " { 'SESSION_COOKIE' : 'AqltVuABNxZtIwAVUEoZEaGFAbn4cUEe000','BMC_IP_ADDR' : '192.168.1.250' },  {} ],  \n" +
//                " HAPI_STATUS:0 }; ");
        Object a = engine.eval("WEBVAR_JSONVAR_WEB_SESSION = \n" +
                " { \n" +
                " WEBVAR_STRUCTNAME_WEB_SESSION : \n" +
                " [ \n" +
                " { 'SESSION_COOKIE' : 'Failure_Login_IPMI_Then_LDAP_then_Active_Directory_Radius','BMC_IP_ADDR' : '192.168.1.250' },  {} ],  \n" +
                " HAPI_STATUS:225 }; ");
        net.sf.json.JSONArray resJsonArr = net.sf.json.JSONArray.fromObject(((Map) bindings.get("WEBVAR_JSONVAR_WEB_SESSION")).get("WEBVAR_STRUCTNAME_WEB_SESSION"));
        String sessionCookie = resJsonArr.getJSONObject(0).getJSONObject("0").getString("SESSION_COOKIE");

        System.out.println(sessionCookie.contains("Failure"));
    }

    public static void main(String[] args) throws ScriptException {
        Bindings bindings = engine.createBindings();
        String response = "//;*****************************************************************; //;*****************************************************************; //;** **; //;** (C) COPYRIGHT American Megatrends Inc. 2008-2009 **; //;** ALL RIGHTS RESERVED **; //;** **; //;** This computer software, including display screens and **; //;** all related materials, are confidential and the **; //;** exclusive property of American Megatrends, Inc. They **; //;** are available for limited use, but only pursuant to **; //;** a written license agreement distributed with this **; //;** computer software. This computer software, including **; //;** display screens and all related materials, shall not be **; //;** copied, reproduced, published or distributed, in whole **; //;** or in part, in any medium, by any means, for any **; //;** purpose without the express written consent of American **; //;** Megatrends, Inc. **; //;** **; //;** **; //;** American Megatrends, Inc. **; //;** 5555 Oakbook Parkway, Building 200 **; //;** Norcross, Georgia - 30071, USA. Phone-(770)-246-8600. **; //;** **; //;*****************************************************************; //;*****************************************************************; //Dynamic Data Begin WEBVAR_JSONVAR_GETCPUINFO = { WEBVAR_STRUCTNAME_GETCPUINFO : [ { 'CPUID' : 0,'Present' : 1,'Model' : 'Intel(R) Xeon(R) CPU E5-2609 v4 @ 1.70GHz','CoreNumber' : 8,'RatedPower' : 85,'MainFrequency' : 0,'L1Cache' : 512,'L2Cache' : 2048,'L3Cache' : 20480,'signature' : '406f1','microcode' : '0b00002e','maxspeed' : '4000 MHz','used_core' : 8 }, { 'CPUID' : 1,'Present' : 0,'Model' : '','CoreNumber' : 0,'RatedPower' : 0,'MainFrequency' : 0,'L1Cache' : 0,'L2Cache' : 0,'L3Cache' : 0,'signature' : '','microcode' : '','maxspeed' : '','used_core' : 0 }, {} ], HAPI_STATUS:0 }; //Dynamic data end";
        response = response.substring(response.indexOf("WEBVAR_JSONVAR"), response.indexOf("//Dynamic data end"));
        engine.eval(response);
        net.sf.json.JSONObject resObj = net.sf.json.JSONObject.fromObject(((Map) bindings.get("WEBVAR_JSONVAR_GETCPUINFO")));

    }
}
