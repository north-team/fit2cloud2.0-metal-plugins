package com.fit2cloud.plugin.ibm.utils;

import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.model.F2CPmCpu;
import com.fit2cloud.metal.sdk.model.F2CPmMemory;
import com.fit2cloud.metal.sdk.util.LogUtil;
import com.fit2cloud.plugin.ibm.model.IBMCpuDTO;
import com.fit2cloud.plugin.ibm.model.IBMMemoryDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * IBM Rest解析工具
 */
public class IBMRestUtil {

    public static String getItemsByEntity(String resultStr) {
        if (StringUtils.isNotBlank(resultStr)) {
            return JSONObject.parseObject(resultStr).getJSONArray("items").toJSONString();
        }
        return null;
    }

    public static Object escapeStr(String str) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            engine.eval("function escapeStr(str)\n" +
                    "{\n" +
                    "    var tmp = new Array();\n" +
                    "    var i;\n" +
                    "    var escstr=\"\";\n" +
                    "    var desc;\n" +
                    "\n" +
                    "    str = str.replace(/\\\\/g, \"\\\\\\\\\");\n" +
                    "    tmp = str.split(\"\");\n" +
                    "    for(i=0; i<str.length; i++)\n" +
                    "    {\n" +
                    "        switch(tmp[i])\n" +
                    "        {\n" +
                    "            case '@':\n" +
                    "            case '(':\n" +
                    "            case ')':\n" +
                    "            case ',':\n" +
                    "            case ':':\n" +
                    "            case '?':\n" +
                    "            case '=':\n" +
                    "            case '&':\n" +
                    "            case '#':\n" +
                    "            case '+':\n" +
                    "            case '%':\n" +
                    "                dec = (tmp[i]+'').charCodeAt(0);\n" +
                    "                escstr+=\"@0\" + dec.toString(16);\n" +
                    "                break;\n" +
                    "            default:\n" +
                    "                escstr+=tmp[i];\n" +
                    "\n" +
                    "        }\n" +
                    "    }\n" +
                    "    return(escstr);\n" +
                    "}");
            if (engine instanceof Invocable) {
                Invocable in = (Invocable) engine;
                return in.invokeFunction("escapeStr", str);
            }
        } catch (Exception e) {
            LogUtil.error("IBM 转换字符串出错.....");
        }
        return str;
    }

    public static List<F2CPmMemory> covertToF2CMemory(IBMMemoryDTO ibmMemoryDTO) {
        List<F2CPmMemory> list = new ArrayList<>();
        if (ibmMemoryDTO != null && !CollectionUtils.isEmpty(ibmMemoryDTO.getMemory())) {
            for (IBMMemoryDTO.Memory memory : ibmMemoryDTO.getMemory()) {
                F2CPmMemory F2CPmMemory = new F2CPmMemory();
                F2CPmMemory.setSn(memory.getSerialNumber());
                F2CPmMemory.setMemModType(memory.getType());
                F2CPmMemory.setMemModPartNum(memory.getPartNumber());
                F2CPmMemory.setMemModSize(String.valueOf(memory.getCapacity()));
                F2CPmMemory.setSyncTime(new Date().getTime());
                list.add(F2CPmMemory);
            }
        }
        return list;
    }

    public static List<F2CPmCpu> covertToF2CProcessor(IBMCpuDTO ibmCpuDTO) {
        List<F2CPmCpu> list = new ArrayList<>();
        if (ibmCpuDTO != null && !CollectionUtils.isEmpty(ibmCpuDTO.getProcessors())) {
            for (IBMCpuDTO.Processors processors : ibmCpuDTO.getProcessors()) {
                F2CPmCpu F2CPmCpu = new F2CPmCpu();
                F2CPmCpu.setProcStatus(processors.getStatus());
                F2CPmCpu.setProcName(processors.getManufId());
                F2CPmCpu.setProcSpeed(String.valueOf(processors.getClockSpeed()));
                F2CPmCpu.setSyncTime(new Date().getTime());
                list.add(F2CPmCpu);
            }

        }
        return list;
    }

}
