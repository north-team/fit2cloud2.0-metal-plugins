package com.fit2cloud.plugin.dell;

import com.fit2cloud.dell.DellMetalProvider;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.model.IPMIRequest;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

public class DellMetalProviderTest {
    IPMIRequest request;
    Gson gson;
    private DellMetalProvider iMetalProvider;

    @Before
    public void initArgs() {
        request = new IPMIRequest("r720.dahaia123.top", "root", "calvin");
        gson = new Gson();
        iMetalProvider = new DellMetalProvider();
    }

    @Test
    public void testSpider() throws MetalPluginException {
        System.out.println(gson.toJson(iMetalProvider.getMachineEntity(gson.toJson(request))));
    }
}
