package com.fit2cloud.plugin.zte;

import com.fit2cloud.metal.sdk.IMetalProvider;
import com.fit2cloud.metal.sdk.model.request.IPMISnmpRequest;
import com.google.gson.Gson;
import org.junit.Before;

public class PluginTest {
    private IPMISnmpRequest request;
    private IMetalProvider iMetalProvider;
    private Gson gson;

    @Before
    public void setP() {
        request = new IPMISnmpRequest("xx", "root", "calvin");
//        request = new IPMISnmpRequest("10.132.47.217", "root", "Fit2cloud@2019");
        request.setCommunity("public");
        request.setPort(161);
        iMetalProvider = new ZTEMetalProvider();
        gson = new Gson();
    }

}
