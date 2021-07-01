package com.fit2cloud.metal.sdk.model;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class F2CPmCpu implements Serializable {
    @ApiModelProperty("")
    private String id;

    @ApiModelProperty("物理机id")
    private String physicalMachineId;

    @ApiModelProperty("cpu型号")
    private String procName;

    @ApiModelProperty("插槽号")
    private String procSocket;

    @ApiModelProperty("状态")
    private String procStatus;

    @ApiModelProperty("核心速度")
    private String procSpeed;

    @ApiModelProperty("开启的核心数")
    private String procNumCoresEnabled;

    @ApiModelProperty("全部核心数")
    private String procNumCores;

    @ApiModelProperty("全部线程数")
    private String procNumThreads;

    @ApiModelProperty("全部线程数")
    private String procMemTechnology;

    @ApiModelProperty("1级缓存大小 kb")
    private String procNumL1cache;

    @ApiModelProperty("2级缓存大小 kb")
    private String procNumL2cache;

    @ApiModelProperty("3级缓存大小 kb")
    private String procNumL3cache;

    @ApiModelProperty("同步时间")
    private Long syncTime;

    @ApiModelProperty("序列号")
    private String sn;

    @ApiModelProperty("硬件状态:0 存量，1 新增， 2 删除")
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pm_cpu
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.id
     *
     * @return the value of pm_cpu.id
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.id
     *
     * @param id the value for pm_cpu.id
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.physical_machine_id
     *
     * @return the value of pm_cpu.physical_machine_id
     * @mbg.generated
     */
    public String getPhysicalMachineId() {
        return physicalMachineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.physical_machine_id
     *
     * @param physicalMachineId the value for pm_cpu.physical_machine_id
     * @mbg.generated
     */
    public void setPhysicalMachineId(String physicalMachineId) {
        this.physicalMachineId = physicalMachineId == null ? null : physicalMachineId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_name
     *
     * @return the value of pm_cpu.proc_name
     * @mbg.generated
     */
    public String getProcName() {
        return procName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_name
     *
     * @param procName the value for pm_cpu.proc_name
     * @mbg.generated
     */
    public void setProcName(String procName) {
        this.procName = procName == null ? null : procName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_socket
     *
     * @return the value of pm_cpu.proc_socket
     * @mbg.generated
     */
    public String getProcSocket() {
        return procSocket;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_socket
     *
     * @param procSocket the value for pm_cpu.proc_socket
     * @mbg.generated
     */
    public void setProcSocket(String procSocket) {
        this.procSocket = procSocket == null ? null : procSocket.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_status
     *
     * @return the value of pm_cpu.proc_status
     * @mbg.generated
     */
    public String getProcStatus() {
        return procStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_status
     *
     * @param procStatus the value for pm_cpu.proc_status
     * @mbg.generated
     */
    public void setProcStatus(String procStatus) {
        this.procStatus = procStatus == null ? null : procStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_speed
     *
     * @return the value of pm_cpu.proc_speed
     * @mbg.generated
     */
    public String getProcSpeed() {
        return procSpeed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_speed
     *
     * @param procSpeed the value for pm_cpu.proc_speed
     * @mbg.generated
     */
    public void setProcSpeed(String procSpeed) {
        this.procSpeed = procSpeed == null ? null : procSpeed.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_num_cores_enabled
     *
     * @return the value of pm_cpu.proc_num_cores_enabled
     * @mbg.generated
     */
    public String getProcNumCoresEnabled() {
        return procNumCoresEnabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_num_cores_enabled
     *
     * @param procNumCoresEnabled the value for pm_cpu.proc_num_cores_enabled
     * @mbg.generated
     */
    public void setProcNumCoresEnabled(String procNumCoresEnabled) {
        this.procNumCoresEnabled = procNumCoresEnabled == null ? null : procNumCoresEnabled.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_num_cores
     *
     * @return the value of pm_cpu.proc_num_cores
     * @mbg.generated
     */
    public String getProcNumCores() {
        return procNumCores;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_num_cores
     *
     * @param procNumCores the value for pm_cpu.proc_num_cores
     * @mbg.generated
     */
    public void setProcNumCores(String procNumCores) {
        this.procNumCores = procNumCores == null ? null : procNumCores.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_num_threads
     *
     * @return the value of pm_cpu.proc_num_threads
     * @mbg.generated
     */
    public String getProcNumThreads() {
        return procNumThreads;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_num_threads
     *
     * @param procNumThreads the value for pm_cpu.proc_num_threads
     * @mbg.generated
     */
    public void setProcNumThreads(String procNumThreads) {
        this.procNumThreads = procNumThreads == null ? null : procNumThreads.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_mem_technology
     *
     * @return the value of pm_cpu.proc_mem_technology
     * @mbg.generated
     */
    public String getProcMemTechnology() {
        return procMemTechnology;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_mem_technology
     *
     * @param procMemTechnology the value for pm_cpu.proc_mem_technology
     * @mbg.generated
     */
    public void setProcMemTechnology(String procMemTechnology) {
        this.procMemTechnology = procMemTechnology == null ? null : procMemTechnology.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_num_l1cache
     *
     * @return the value of pm_cpu.proc_num_l1cache
     * @mbg.generated
     */
    public String getProcNumL1cache() {
        return procNumL1cache;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_num_l1cache
     *
     * @param procNumL1cache the value for pm_cpu.proc_num_l1cache
     * @mbg.generated
     */
    public void setProcNumL1cache(String procNumL1cache) {
        this.procNumL1cache = procNumL1cache == null ? null : procNumL1cache.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_num_l2cache
     *
     * @return the value of pm_cpu.proc_num_l2cache
     * @mbg.generated
     */
    public String getProcNumL2cache() {
        return procNumL2cache;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_num_l2cache
     *
     * @param procNumL2cache the value for pm_cpu.proc_num_l2cache
     * @mbg.generated
     */
    public void setProcNumL2cache(String procNumL2cache) {
        this.procNumL2cache = procNumL2cache == null ? null : procNumL2cache.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.proc_num_l3cache
     *
     * @return the value of pm_cpu.proc_num_l3cache
     * @mbg.generated
     */
    public String getProcNumL3cache() {
        return procNumL3cache;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.proc_num_l3cache
     *
     * @param procNumL3cache the value for pm_cpu.proc_num_l3cache
     * @mbg.generated
     */
    public void setProcNumL3cache(String procNumL3cache) {
        this.procNumL3cache = procNumL3cache == null ? null : procNumL3cache.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.sync_time
     *
     * @return the value of pm_cpu.sync_time
     * @mbg.generated
     */
    public Long getSyncTime() {
        return syncTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.sync_time
     *
     * @param syncTime the value for pm_cpu.sync_time
     * @mbg.generated
     */
    public void setSyncTime(Long syncTime) {
        this.syncTime = syncTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.sn
     *
     * @return the value of pm_cpu.sn
     * @mbg.generated
     */
    public String getSn() {
        return sn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.sn
     *
     * @param sn the value for pm_cpu.sn
     * @mbg.generated
     */
    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pm_cpu.status
     *
     * @return the value of pm_cpu.status
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pm_cpu.status
     *
     * @param status the value for pm_cpu.status
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}