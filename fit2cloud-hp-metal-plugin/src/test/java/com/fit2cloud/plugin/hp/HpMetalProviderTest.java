package com.fit2cloud.plugin.hp;

import com.fit2cloud.hp.HpMetalProvider;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.model.IPMIRequest;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

public class HpMetalProviderTest {
    IPMIRequest request;
    Gson gson;
    private HpMetalProvider iMetalProvider;

    @Before
    public void initArgs() {
        request = new IPMIRequest("dl3809.dahaia123.top", "administrator", "Fit2cloud@2019");
        gson = new Gson();
        iMetalProvider = new HpMetalProvider();
    }

    @Test
    public void testSpider() throws MetalPluginException {
        System.out.println(gson.toJson(iMetalProvider.getMachineEntity(gson.toJson(request))));
    }
}
