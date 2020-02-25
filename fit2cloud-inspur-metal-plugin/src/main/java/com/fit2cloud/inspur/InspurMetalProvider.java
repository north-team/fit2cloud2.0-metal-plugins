package com.fit2cloud.inspur;

import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.AbstractMetalProvider;
import com.fit2cloud.metal.sdk.F2CMetalPlugin;
import com.fit2cloud.metal.sdk.MetalPluginException;
import com.fit2cloud.metal.sdk.constants.InitMethod;
import com.fit2cloud.metal.sdk.model.MachineEntity;

import java.util.Arrays;
import java.util.List;

@F2CMetalPlugin
public class InspurMetalProvider extends AbstractMetalProvider {
    private static String name = "fit2cloud-inspur-metal-plugin";

    public String getName() {
        return name;
    }


    @Override
    public List<InitMethod> getSupportedInitMethod(String request) throws MetalPluginException {
        return Arrays.asList(InitMethod.ALLOCATE_IP, InitMethod.SET_PASSWORD, InitMethod.SET_HOSTNAME, InitMethod.ADD_DOMAIN);
    }

    @Override
    public String getPlatformVersion(String request) throws MetalPluginException {
        return null;
    }

    @Override
    public MachineEntity getMachineEntity(String ipmiSnmpRequestStr) throws MetalPluginException {
        return null;
    }

    @Override
    public JSONObject getRaidPayload(String raidConfigDTO) throws MetalPluginException {
        return null;
    }

    @Override
    public String getValidRaidType(String raidType) throws MetalPluginException {
        return null;
    }

    @Override
    public boolean login(String ipmiRequest) throws MetalPluginException {
        return false;
    }

    @Override
    public boolean logout(String ipmiRequest) throws MetalPluginException {
        return false;
    }

    @Override
    public boolean validateCredential(String credential) throws MetalPluginException {
        return false;
    }
}
