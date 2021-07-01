package com.fit2cloud.metal.sdk;

import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.model.F2CPmMetric;
import com.fit2cloud.metal.sdk.model.MachineEntity;
import com.fit2cloud.metal.sdk.model.PluginResult;

import java.util.Map;

public interface IMetalProvider {
    /**
     * 返回插件名称
     **/
    String getName();

    /**
     * 获取某台机器对应的Cookie
     **/
    Map<String, String> getHeader(String ip);

    //查询机器带外连通状态
    PluginResult status(String ipmiRequestStr) throws MetalPluginException;

    //启动机器
    PluginResult powerOn(String ipmiRequestStr) throws MetalPluginException;

    //关闭机器
    PluginResult powerOff(String ipmiRequestStr) throws MetalPluginException;

    //重启机器
    PluginResult reset(String ipmiRequestStr) throws MetalPluginException;

    //以PXE方式重启机器
    PluginResult resetPXE(String ipmiRequestStr) throws MetalPluginException;

    //重设机器密码
    PluginResult resetPwd(String ipmiRequestStr) throws MetalPluginException;

    //重设机器IP
    PluginResult resetIp(String ipmiRequestStr) throws MetalPluginException;

    //登录带外web管理控制台
    PluginResult login(String ipmiRequestStr) throws MetalPluginException;

    //登出带外web管理控制台
    PluginResult logout(String ipmiRequestStr) throws MetalPluginException;

    //获取物理机硬件信息
    MachineEntity getMachineEntity(String ipmiRequestStr) throws MetalPluginException;

    /**
     * 获取RackHD raid payload
     */
    JSONObject getRaidPayLoad(String raidConfigDTO) throws MetalPluginException;

    /**
     * 获取RackHD 擦除raid payload 一般都是全部擦除
     */
    JSONObject getDeleteRaidPayload();

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


    // 转换成合适 RackHD 调用cli工具制作raid的raidtype
    String getValidRaidType(String raidType) throws MetalPluginException;

    //获取物理机监控数据
    F2CPmMetric getMetric(String ipmiSnmpRequestStr) throws MetalPluginException;

    /**
     * 自定义方法，不通用的逻辑请override这个方法
     **/
    <T> T invokeCustomMethod(String methodName, Object... parameters) throws MetalPluginException;
}
