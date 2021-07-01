package com.fit2cloud.plugin.inspur;

import com.fit2cloud.metal.sdk.IMetalProvider;
import com.fit2cloud.metal.sdk.model.request.IPMISnmpRequest;
import com.fit2cloud.plugin.huawei.HuaweiMetalProvider;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

public class PluginTest {
    private IPMISnmpRequest request;
    private IMetalProvider iMetalProvider;
    private Gson gson;

    @Before
    public void setP() {
        request = new IPMISnmpRequest("xx", "xx", "xx");
        request.setCommunity("public");
        request.setPort(161);
        iMetalProvider = new HuaweiMetalProvider();
        gson = new Gson();
    }

    @Test
    public void testGetMachineEntity() {
        System.out.println(gson.toJson(iMetalProvider.getMachineEntity(gson.toJson(request))));
    }

    @Test
    public void testGetMetrics() {
        System.out.println(gson.toJson(iMetalProvider.getMetric(gson.toJson(request))));
    }

}
