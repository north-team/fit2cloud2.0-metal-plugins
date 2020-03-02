package com.fit2cloud.metal.sdk;

import com.fit2cloud.metal.sdk.constants.F2CResourceType;
import com.fit2cloud.metal.sdk.model.IPMIRequest;
import com.fit2cloud.metal.sdk.model.SNMPRequest;
import com.fit2cloud.metal.sdk.util.IPMIUtil;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;


public abstract class AbstractMetalProvider implements IMetalProvider {
    protected Logger log = LoggerFactory.getLogger(getClass());
    protected Gson gson = new Gson();
    //RackHD api
    protected String workflowPostUrl = "/api/2.0/nodes/%s/workflows?name=";

    public String getPageTemplate() throws MetalPluginException {
        return getPageTemplate(F2CResourceType.RACKHD_RAID_PAYLOAD);
    }

    public void checkIPMIRequest(IPMIRequest request) throws MetalPluginException {
        if (StringUtils.isAnyBlank(request.getIp(), request.getUserName(), request.getPassword())) {
            throw new MetalPluginException("参数错误！");
        }
    }

    public void checkSnmpRequest(SNMPRequest request) throws MetalPluginException {
        if (StringUtils.isAnyBlank(request.getCommunity(), request.getIp())) {
            throw new MetalPluginException("参数错误！");
        }
    }

    public String getPageTemplate(String resourceType) throws MetalPluginException {
        String pageFile = "launchConfigure.json";
        if (F2CResourceType.IP.equalsIgnoreCase(resourceType)) {
            pageFile = "ip.json";
        } else if (F2CResourceType.RACKHD_RAID_PAYLOAD.equalsIgnoreCase(resourceType)) {
            pageFile = F2CResourceType.RACKHD_RAID_PAYLOAD;
        } else if (F2CResourceType.RACKHD_RAID_DEL_PAYLOAD.equalsIgnoreCase(resourceType)) {
            pageFile = F2CResourceType.RACKHD_RAID_DEL_PAYLOAD;
        }
        return readConfigFile(pageFile);
    }

    public String getCredentialPageTemplate() throws MetalPluginException {
        return readConfigFile("credential.json");
    }

    @SuppressWarnings("resource")
    private String readConfigFile(String fileName) throws MetalPluginException {
        InputStream is = null;
        BufferedReader br = null;
        try {
            URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
            JarFile jarFile = new JarFile(url.getPath());
            is = jarFile.getInputStream(jarFile.getEntry(fileName));
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("读取json文件失败", e);
            throw new MetalPluginException("该插件尚不支持该操作!");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
                is = null;
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
                br = null;
            }
        }
    }


    @Override
    public boolean powerOn(String ipmiReqeuestStr) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiReqeuestStr, IPMIRequest.class);
        checkIPMIRequest(request);
        try {
            IPMIUtil.exeCommand(new IPMIUtil.Account(request.getIp(), request.getUserName(), request.getPassword()), "power on");
        } catch (Exception e) {
            throw new MetalPluginException(e);
        }
        return true;
    }

    @Override
    public boolean powerOff(String ipmiReqeuestStr) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiReqeuestStr, IPMIRequest.class);
        checkIPMIRequest(request);
        try {
            IPMIUtil.exeCommand(new IPMIUtil.Account(request.getIp(), request.getUserName(), request.getPassword()), "power off");
        } catch (Exception e) {
            throw new MetalPluginException(e);
        }
        return true;
    }

    @Override
    public boolean powerReset(String ipmiReqeuestStr) throws MetalPluginException {
        IPMIRequest request = gson.fromJson(ipmiReqeuestStr, IPMIRequest.class);
        checkIPMIRequest(request);
        try {
            IPMIUtil.exeCommand(new IPMIUtil.Account(request.getIp(), request.getUserName(), request.getPassword()), "power reset");
        } catch (Exception e) {
            throw new MetalPluginException(e);
        }
        return true;
    }

    @Override
    public boolean resetPwd(String ipmiReqeuestStr) throws MetalPluginException {
        //:todo
        return false;
    }

    @Override
    public boolean resetIp(String ipmiReqeuestStr) throws MetalPluginException {
        //:todo
        return false;
    }

    public <T> T invokeCustomMethod(String methodName, Object... parameters) throws MetalPluginException {
        try {
            List<Class> paramsClass = new ArrayList<Class>();
            for (Object param : parameters) {
                paramsClass.add(param.getClass());
            }
            Method targetMethod = this.getClass().getDeclaredMethod(methodName, paramsClass.toArray(new Class[]{}));
            return (T) targetMethod.invoke(this, parameters);
        } catch (Exception e) {
            throw new MetalPluginException(e);
        }
    }
}
