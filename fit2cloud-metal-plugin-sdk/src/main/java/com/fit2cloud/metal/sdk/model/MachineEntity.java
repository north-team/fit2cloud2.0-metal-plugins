package com.fit2cloud.metal.sdk.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;
import java.util.Map;

public class MachineEntity {
    private String ip;
    private String bmcIp;
    private String bmcMac;
    private String instanceUuid;
    //rackHD id
    private String nodeId;
    private String name;
    private int cpu;
    private String cpuType;
    private String cpuFre;
    private int core;
    private int thread;
    private long memory;
    private String memoryType;
    private long disk;
    private String osType;
    private String osDetail;
    private List<String> ipArray;
    private String brand;
    private String model;
    private HardwareEntity hardwareEntity;
    private String systemServiceTag;
    private String systemBiosVersion;
    private String procPwrPerf;
    private String memTest;
    private String nodeInterleave;
    //物理机序列号
    private String serialNo;
    private List<F2CPmDisk> disks;
    private List<F2CPmNetworkCard> pmNetworkCards;
    private List<F2CPmCpu> pmCpus;
    private List<F2CPmMemory> pmMemories;

    private Map<String, String> extendInfo;

    public Map<String, String> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(Map<String, String> extendInfo) {
        this.extendInfo = extendInfo;
    }

    public MachineEntity() {
    }

    public List<F2CPmNetworkCard> getPmNetworkCards() {
        return pmNetworkCards;
    }

    public void setPmNetworkCards(List<F2CPmNetworkCard> pmNetworkCards) {
        this.pmNetworkCards = pmNetworkCards;
    }

    public String getSystemServiceTag() {
        return this.systemServiceTag;
    }

    public void setSystemServiceTag(String systemServiceTag) {
        this.systemServiceTag = systemServiceTag;
    }

    public String getSystemBiosVersion() {
        return this.systemBiosVersion;
    }

    public void setSystemBiosVersion(String systemBiosVersion) {
        this.systemBiosVersion = systemBiosVersion;
    }

    public String getProcPwrPerf() {
        return this.procPwrPerf;
    }

    public void setProcPwrPerf(String procPwrPerf) {
        this.procPwrPerf = procPwrPerf;
    }

    public String getMemTest() {
        return this.memTest;
    }

    public void setMemTest(String memTest) {
        this.memTest = memTest;
    }

    public String getNodeInterleave() {
        return this.nodeInterleave;
    }

    public void setNodeInterleave(String nodeInterleave) {
        this.nodeInterleave = nodeInterleave;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getInstanceUuid() {
        return this.instanceUuid;
    }

    public void setInstanceUuid(String instanceUuid) {
        this.instanceUuid = instanceUuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCpu() {
        return this.cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public long getMemory() {
        return this.memory;
    }

    public void setMemory(long memory) {
        this.memory = memory;
    }

    public long getDisk() {
        return this.disk;
    }

    public void setDisk(long disk) {
        this.disk = disk;
    }

    public String getOsType() {
        return this.osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsDetail() {
        return this.osDetail;
    }

    public void setOsDetail(String osDetail) {
        this.osDetail = osDetail;
    }

    public List<String> getIpArray() {
        return this.ipArray;
    }

    public void setIpArray(List<String> ipArray) {
        this.ipArray = ipArray;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public HardwareEntity getHardwareEntity() {
        return this.hardwareEntity;
    }

    public void setHardwareEntity(HardwareEntity hardwareEntity) {
        this.hardwareEntity = hardwareEntity;
    }

    public String getBmcIp() {
        return bmcIp;
    }

    public void setBmcIp(String bmcIp) {
        this.bmcIp = bmcIp;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    public String getCpuFre() {
        return cpuFre;
    }

    public void setCpuFre(String cpuFre) {
        this.cpuFre = cpuFre;
    }

    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public List<F2CPmDisk> getDisks() {
        return disks;
    }

    public void setDisks(List<F2CPmDisk> disks) {
        this.disks = disks;
    }

    public String getBmcMac() {
        return bmcMac;
    }

    public void setBmcMac(String bmcMac) {
        this.bmcMac = bmcMac;
    }

    public List<F2CPmCpu> getPmCpus() {
        return pmCpus;
    }

    public void setPmCpus(List<F2CPmCpu> pmCpus) {
        this.pmCpus = pmCpus;
    }

    public List<F2CPmMemory> getPmMemories() {
        return pmMemories;
    }

    public void setPmMemories(List<F2CPmMemory> pmMemories) {
        this.pmMemories = pmMemories;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}