package com.fit2cloud.metal.sdk;

import com.fit2cloud.metal.sdk.constants.F2CResourceType;
import com.google.gson.Gson;
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

    public String getPageTemplate() throws MetalPluginException {
        return getPageTemplate(F2CResourceType.VM);
    }

    public String getPageTemplate(String resourceType) throws MetalPluginException {
        String pageFile = "launchConfigure.json";
        if (F2CResourceType.IP.equalsIgnoreCase(resourceType)) {
            pageFile = "ip.json";
        } else if (F2CResourceType.DISK.equalsIgnoreCase(resourceType)) {
            pageFile = "diskConfigure.json";
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
        //:todo
        return false;
    }

    @Override
    public boolean powerOff(String ipmiReqeuestStr) throws MetalPluginException {
        //:todo
        return false;
    }

    @Override
    public boolean powerReset(String ipmiReqeuestStr) throws MetalPluginException {
        //:todo
        return false;
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
