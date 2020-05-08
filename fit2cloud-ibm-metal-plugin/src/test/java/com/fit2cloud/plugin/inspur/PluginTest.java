package com.fit2cloud.plugin.inspur;

import com.fit2cloud.metal.sdk.IMetalProvider;
import com.fit2cloud.metal.sdk.model.PluginResult;
import com.fit2cloud.metal.sdk.model.request.IPMISnmpRequest;
import com.fit2cloud.plugin.ibm.IBMMetalProvider;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class PluginTest {
    private IPMISnmpRequest request;
    private IMetalProvider iMetalProvider;
    private Gson gson;

    @Before
    public void setP() {
//        request = new IPMISnmpRequest("10.132.47.212", "admin", "admin");
//        request = new IPMISnmpRequest("10.132.47.213", "kaijun", "Szse@ipmi307");
//        request = new IPMISnmpRequest("10.132.47.213", "USERID", "Bo1701@sse");
        request = new IPMISnmpRequest("10.132.47.213", "USERID", "PASSW0RD");
        request.setCommunity("public");
        request.setPort(161);
        iMetalProvider = new IBMMetalProvider();
        gson = new Gson();
    }

    @After
    public void logout() {
        PluginResult logout = iMetalProvider.logout(gson.toJson(request));
        System.out.println("logout:" + gson.toJson(logout));
    }

    @Test
    public void testGetMachineEntity() {
        System.out.println(gson.toJson(iMetalProvider.getMachineEntity(gson.toJson(request))));
    }

    @Test
    public void testGetMetrics() {
        System.out.println(gson.toJson(iMetalProvider.getMetric(gson.toJson(request))));
    }


    @Test
    public void testLoginAndLogout() {
        PluginResult login = iMetalProvider.login(gson.toJson(request));
        System.out.println("login:" + gson.toJson(login));

        try {
            //休眠一分钟，期间可以看到在IBM 带外控制台可以看到有登录状态，调用logout方法，状态消失
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PluginResult logout = iMetalProvider.logout(gson.toJson(request));
        System.out.println("logout:" + gson.toJson(logout));

    }

    @Test
    public void testHighFrequencySpider() {
        for (int i = 0; i < 10; i++) {
            System.out.println(gson.toJson(iMetalProvider.getMachineEntity(gson.toJson(request))));
        }
    }
}
