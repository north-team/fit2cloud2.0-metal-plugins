package com.fit2cloud.metal.sdk;

import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.constants.InitMethod;
import com.fit2cloud.metal.sdk.model.F2CMetrics;
import com.fit2cloud.metal.sdk.model.MachineEntity;

import java.util.List;
import java.util.Map;

public interface IMetalProvider {
    /**
     * 返回插件名称
     **/
    String getName();

    /**
     * 获取插件支持的初始化方法
     **/
    List<InitMethod> getSupportedInitMethod(String request) throws MetalPluginException;

    /**
     * 获取平台版本
     **/
    String getPlatformVersion(String request) throws MetalPluginException;

    /**
     * 获取某台机器对应的Cookie
     **/
    Map<String, String> getHeader(String ip);

    /**
     * 爬取硬件信息
     **/
    MachineEntity getMachineEntity(String ipmiSnmpRequestStr) throws MetalPluginException;

    /**
     * 获取RackHD raid payload
     */
    JSONObject getRaidPayload(String raidConfigDTO) throws MetalPluginException;

    /**
     * 获取RackHD 擦除raid payload 一般都是全部擦除
     */
    JSONObject getDeletePayload();

    /**
     * 获取 RackHD 制作raid的workflow
     *
     * @return
     */
    String getRaidWorkFlow();

    /**
     * 获取 RackHD 删除raid的workflow
     *
     * @return
     */
    String getDeleteRaidWorkFlow();

    /**
     * 获取 RackHD 收集raid的workflow
     *
     * @return
     */
    String getCatalogRaidWorkFlow();

    /**
     * 获取RackHD raid payload 合适的raidType
     */
    String getValidRaidType(String raidType) throws MetalPluginException;

    /**
     * 登录
     **/
    boolean login(String ipmiRequest) throws MetalPluginException;

    /***
     * 登出
     **/
    boolean logout(String ipmiRequest) throws MetalPluginException;

    /**
     * 验证帐号是否有效
     **/
    boolean validateCredential(String credential) throws MetalPluginException;

    /**
     * 开机
     **/
    boolean powerOn(String ipmiReqeuestStr) throws MetalPluginException;

    /**
     * 关机
     **/
    boolean powerOff(String ipmiReqeuestStr) throws MetalPluginException;

    /**
     * 重启
     **/
    boolean powerReset(String ipmiReqeuestStr) throws MetalPluginException;

    /**
     * 重设密码
     **/
    boolean resetPwd(String ipmiReqeuestStr) throws MetalPluginException;

    /**
     * 重设ip
     **/
    boolean resetIp(String ipmiReqeuestStr) throws MetalPluginException;

    /**
     * 采集硬件监控数据
     **/
    F2CMetrics getMetrics(String ipmiReqeuestStr) throws MetalPluginException;

    /**
     * 自定义方法，不通用的逻辑请override这个方法
     **/
    <T> T invokeCustomMethod(String methodName, Object... parameters) throws MetalPluginException;

}
