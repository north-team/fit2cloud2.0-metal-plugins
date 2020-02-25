package com.fit2cloud.metal.sdk.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class F2CMemory implements Serializable {
    @ApiModelProperty("")
    private String id;

    @ApiModelProperty("物理机id")
    private String physicalMachineId;

    @ApiModelProperty(" 磁盘类型")
    private String type;

    @ApiModelProperty("磁盘容量（GB）")
    private String size;

    @ApiModelProperty("制造商")
    private String manufactor;

    @ApiModelProperty("插槽")
    private String slot;

    @ApiModelProperty("频率")
    private String freq;

    @ApiModelProperty("序列号")
    private String sn;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
}