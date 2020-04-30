package com.fit2cloud.plugin.dell;

import com.fit2cloud.dell.DellMetalProvider;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.model.IPMIRequest;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

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
        System.out.println("结束");
    }

    @Test
    public void testLoginOut() throws MetalPluginException {
        String loginRst = gson.toJson(iMetalProvider.login(gson.toJson(request)));
        String logoutRst = gson.toJson(iMetalProvider.logout(gson.toJson(request)));
        System.out.println("结束");
    }

    public static void main(String[] args){
        String json = "{\"Processor\":{\"D2||CPU.Socket.1\":{\"brand\":\"Intel(R) Xeon(R) CPU E5-2620 v2 @ 2.10GHz\",\"cache\":\"/sysmgmt/2012/server/cache?processor=D2||CPU.Socket.1\",\"core_count\":6,\"current_speed\":2100,\"device_description\":\"CPU 1\",\"executeDisable\":[{\"capable\":1,\"enabled\":1}],\"hyperThreading\":[{\"capable\":1,\"enabled\":1}],\"name\":\"[CPU1]\",\"state\":3,\"status\":0,\"turboMode\":[{\"capable\":1,\"enabled\":1}],\"version\":\"Model 62 Stepping 4\",\"virtualizationTech\":[{\"capable\":1,\"enabled\":1}]},\"D2||CPU.Socket.2\":{\"brand\":\"Intel(R) Xeon(R) CPU E5-2620 v2 @ 2.10GHz\",\"cache\":\"/sysmgmt/2012/server/cache?processor=D2||CPU.Socket.2\",\"core_count\":6,\"current_speed\":2100,\"device_description\":\"CPU 2\",\"executeDisable\":[{\"capable\":1,\"enabled\":1}],\"hyperThreading\":[{\"capable\":1,\"enabled\":1}],\"name\":\"[CPU2]\",\"state\":3,\"status\":0,\"turboMode\":[{\"capable\":1,\"enabled\":1}],\"version\":\"Model 62 Stepping 4\",\"virtualizationTech\":[{\"capable\":1,\"enabled\":1}]}}}";

        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(json);
        net.sf.json.JSONObject Processor = jsonObject.getJSONObject("Processor");
        Iterator cpuIt = Processor.keys();
        while (cpuIt.hasNext()) {
            net.sf.json.JSONObject cpu = Processor.getJSONObject((String) cpuIt.next());
            System.out.println(cpu.toString());
        }
    }
}
