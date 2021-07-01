package com.fit2cloud.plugin.inspur.utils;

import com.fit2cloud.metal.sdk.model.F2CPmMetric;
import com.fit2cloud.metal.sdk.model.MachineEntity;
import com.fit2cloud.metal.sdk.model.PluginResult;
import com.google.gson.Gson;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface IMSRestApi {
    boolean login(String ip, String userName, String password);

    MachineEntity getMachineEntity(String ip, String userName, String password);

    F2CPmMetric getMetric(String ip, String userName, String password);

    Float getPowerMetric(String ip, String userName, String password);

    ConcurrentHashMap<String, Map> headerMap = new ConcurrentHashMap();
    Gson gson = new Gson();

    PluginResult logout(String ip, String userName, String password);
}
