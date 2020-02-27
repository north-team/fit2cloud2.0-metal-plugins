package com.fit2cloud.metal.sdk.model;

import java.util.List;

public class F2CMetrics {
    /**
     * cpu 温度列表 按照slot顺序 或则cpuid的顺序
     */
    private List<Integer> cpuTempList;

    /**
     * 主板南桥温度
     */
    private Integer pchTemp;

    /**
     * cpu状态
     */
    private List<Integer> cpuStatus;

    /**
     * 内存状态
     */
    private List<Integer> memoryStatus;

    /**
     * 风扇状态
     */
    private List<Integer> fanStatus;

    /**
     * 磁盘状态
     */
    private List<Integer> diskStatus;

    /**
     * 是否日志满
     */
    private Integer selFull;

    public List<Integer> getCpuTempList() {
        return cpuTempList;
    }

    public void setCpuTempList(List<Integer> cpuTempList) {
        this.cpuTempList = cpuTempList;
    }

    public Integer getPchTemp() {
        return pchTemp;
    }

    public void setPchTemp(Integer pchTemp) {
        this.pchTemp = pchTemp;
    }

    public List<Integer> getCpuStatus() {
        return cpuStatus;
    }

    public void setCpuStatus(List<Integer> cpuStatus) {
        this.cpuStatus = cpuStatus;
    }

    public List<Integer> getMemoryStatus() {
        return memoryStatus;
    }

    public void setMemoryStatus(List<Integer> memoryStatus) {
        this.memoryStatus = memoryStatus;
    }

    public List<Integer> getFanStatus() {
        return fanStatus;
    }

    public void setFanStatus(List<Integer> fanStatus) {
        this.fanStatus = fanStatus;
    }

    public List<Integer> getDiskStatus() {
        return diskStatus;
    }

    public void setDiskStatus(List<Integer> diskStatus) {
        this.diskStatus = diskStatus;
    }

    public Integer getSelFull() {
        return selFull;
    }

    public void setSelFull(Integer selFull) {
        this.selFull = selFull;
    }
}
