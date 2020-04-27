package com.fit2cloud.metal.sdk.constants;

import com.fit2cloud.metal.sdk.MetalPluginException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PluginConstants {
    private static Map<List<String>, String> pluginBrandMap;
    public static final String Inspur = "Inspur";
    public static final String Dell_Inc = "Dell Inc.";
    public static final String DELL = "DELL";
    public static final String IBM = "IBM";
    public static final String HP = "HP";

    private static List<String> inspurBrand = new ArrayList<String>() {{
        add(Inspur);
    }};

    private static List<String> dellBrand = new ArrayList<String>() {{
        add(Dell_Inc);
        add(DELL);
    }};

    private static List<String> hpBrand = new ArrayList<String>() {{
        add(HP);
    }};

    static {
        pluginBrandMap = new ConcurrentHashMap();
        pluginBrandMap.put(inspurBrand, "fit2cloud-inspur-metal-plugin");
        pluginBrandMap.put(dellBrand, "fit2cloud-dell-metal-plugin");
        pluginBrandMap.put(hpBrand, "fit2cloud-hp-metal-plugin");
    }


    public static String getPNWithBrand(String brand) throws MetalPluginException {
        for (Map.Entry<List<String>, String> listStringEntry : pluginBrandMap.entrySet()) {
            if (listStringEntry.getKey().contains(brand)) {
                return listStringEntry.getValue();
            }
        }
        throw new MetalPluginException("该品牌" + brand + "暂时不支持！");
    }
}
