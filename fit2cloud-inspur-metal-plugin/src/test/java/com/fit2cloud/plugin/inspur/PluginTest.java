package com.fit2cloud.plugin.inspur;

import com.fit2cloud.metal.sdk.IMetalProvider;
import com.fit2cloud.metal.sdk.model.request.IPMISnmpRequest;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

public class PluginTest {
    private IPMISnmpRequest request;
    private IMetalProvider iMetalProvider;
    private Gson gson;

    @Before
    public void setP() {
        request = new IPMISnmpRequest("xxx", "xxx", "xxx");
        request.setCommunity("public");
        request.setPort(161);
        iMetalProvider = new InspurMetalProvider();
        gson = new Gson();
    }

    @Test
    public void testGetMachineEntity() {
        System.out.println(gson.toJson(iMetalProvider.getMachineEntity(gson.toJson(request))));
        System.out.println("---haghahahh");
//        System.out.println(gson.toJson(iMetalProvider.logout(gson.toJson(request))));
        System.out.println(gson.toJson(iMetalProvider.getMachineEntity(gson.toJson(request))));
    }

    @Test
    public void testGetMetrics() {
        System.out.println(gson.toJson(iMetalProvider.getMetric(gson.toJson(request))));
    }

    @Test
    public void testHighFrequencySpider() {
        for (int i = 0; i < 20; i++) {
            System.out.println(gson.toJson(iMetalProvider.getMachineEntity(gson.toJson(request))));
        }
    }

}
