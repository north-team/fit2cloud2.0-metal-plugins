package com.fit2cloud.metal.sdk.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class F2CCpu implements Serializable {
    @ApiModelProperty("")
    private String id;

    @ApiModelProperty("物理机id")
    private String physicalMachineId;

    @ApiModelProperty("制造商")
    private String manufactor;

    @ApiModelProperty("插槽")
    private String slot;

    @ApiModelProperty("主频")
    private String freq;

    @ApiModelProperty("型号")
    private String model;

    @ApiModelProperty("序列号")
    private String sn;

    @ApiModelProperty("核心数")
    private String core;

    @ApiModelProperty("线程数/1个核心")
    private String thread;

    @ApiModelProperty("同步时间")
    private Long syncTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhysicalMachineId() {
        return physicalMachineId;
    }

    public void setPhysicalMachineId(String physicalMachineId) {
        this.physicalMachineId = physicalMachineId;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Long syncTime) {
        this.syncTime = syncTime;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }
}