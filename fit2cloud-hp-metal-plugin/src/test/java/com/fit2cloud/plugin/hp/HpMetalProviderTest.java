package com.fit2cloud.plugin.hp;

import com.fit2cloud.hp.HpMetalProvider;
import com.fit2cloud.hp.model.HpOverViewDTO;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.model.IPMIRequest;
import com.fit2cloud.metal.sdk.util.HttpUtils;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

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

    private static final String overViewUrl = "https://%s/json/overview";

    @Test
    public void testLoginOut() throws MetalPluginException {
        System.out.println(gson.toJson(iMetalProvider.login(gson.toJson(request))));
        Map<String, String> header = iMetalProvider.getHeader(request.getIp());
        System.out.println(gson.toJson(iMetalProvider.logout(gson.toJson(request))));
        HpOverViewDTO hpOverViewDTO = gson.fromJson(HttpUtils.get(String.format(overViewUrl, request.getIp()), header), HpOverViewDTO.class);
        System.out.println(gson.toJson(hpOverViewDTO));
    }
}
