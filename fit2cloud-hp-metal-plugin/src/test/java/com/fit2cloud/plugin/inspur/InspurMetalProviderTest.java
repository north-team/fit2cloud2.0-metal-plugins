package com.fit2cloud.plugin.inspur;

import com.fit2cloud.inspur.InspurMetalProvider;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.model.IPMIRequest;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

public class InspurMetalProviderTest {
    IPMIRequest request;
    Gson gson;

    @Before
    public void initArgs() {
        request = new IPMIRequest("149.129.105.194:4000", "admin", "Fit2cloud@2019");
        gson = new Gson();
    }

    @Test
    public void testSpider() throws MetalPluginException {
        System.out.println(gson.toJson(new InspurMetalProvider().getMachineEntity(gson.toJson(request))));
    }
}
