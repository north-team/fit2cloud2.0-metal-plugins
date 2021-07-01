package com.fit2cloud.plugin.huawei.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HuaweiCpuDTO {
    @SerializedName("Cpu")
    private List<CpuBean> Cpu;

    public List<CpuBean> getCpu() {
        return Cpu;
    }

    public void setCpu(List<CpuBean> Cpu) {
        this.Cpu = Cpu;
    }

    public static class CpuBean {
        /**
         * Name : CPU1
         * Presence : 1
         * Manufacturer : Hisilicon
         * Version : Kunpeng 920-4826
         * ProcessorID : 10-D0-1F-48-00-00-00-00
         * CurrentSpeed : 2600 MHz
         * CoreCount_ThreadCount : 48/48
         * MemoryTec : 64-bit Capable| Multi-Core| Execute Protection| Enhanced Virtualization| Power/Performance Control
         * L1Cache_L2Cache_L3Cache : 3072/24576/46080
         * DisableCpuHw : 0
         * CpuHealth : 0
         * PartNum : N/A
         * SN : BF54B61301C0C500
         * obj_name : Cpu1
         */

        @SerializedName("Name")
        private String Name;
        @SerializedName("Presence")
        private int Presence;
        @SerializedName("Manufacturer")
        private String Manufacturer;
        @SerializedName("Version")
        private String Version;
        @SerializedName("ProcessorID")
        private String ProcessorID;
        @SerializedName("CurrentSpeed")
        private String CurrentSpeed;
        @SerializedName("CoreCount_ThreadCount")
        private String CoreCountThreadCount;
        @SerializedName("MemoryTec")
        private String MemoryTec;
        @SerializedName("L1Cache_L2Cache_L3Cache")
        private String L1CacheL2CacheL3Cache;
        @SerializedName("DisableCpuHw")
        private int DisableCpuHw;
        @SerializedName("CpuHealth")
        private int CpuHealth;
        @SerializedName("PartNum")
        private String PartNum;
        @SerializedName("SN")
        private String SN;
        @SerializedName("obj_name")
        private String objName;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getPresence() {
            return Presence;
        }

        public void setPresence(int Presence) {
            this.Presence = Presence;
        }

        public String getManufacturer() {
            return Manufacturer;
        }

        public void setManufacturer(String Manufacturer) {
            this.Manufacturer = Manufacturer;
        }

        public String getVersion() {
            return Version;
        }

        public void setVersion(String Version) {
            this.Version = Version;
        }

        public String getProcessorID() {
            return ProcessorID;
        }

        public void setProcessorID(String ProcessorID) {
            this.ProcessorID = ProcessorID;
        }

        public String getCurrentSpeed() {
            return CurrentSpeed;
        }

        public void setCurrentSpeed(String CurrentSpeed) {
            this.CurrentSpeed = CurrentSpeed;
        }

        public String getCoreCountThreadCount() {
            return CoreCountThreadCount;
        }

        public void setCoreCountThreadCount(String CoreCountThreadCount) {
            this.CoreCountThreadCount = CoreCountThreadCount;
        }

        public String getMemoryTec() {
            return MemoryTec;
        }

        public void setMemoryTec(String MemoryTec) {
            this.MemoryTec = MemoryTec;
        }

        public String getL1CacheL2CacheL3Cache() {
            return L1CacheL2CacheL3Cache;
        }

        public void setL1CacheL2CacheL3Cache(String L1CacheL2CacheL3Cache) {
            this.L1CacheL2CacheL3Cache = L1CacheL2CacheL3Cache;
        }

        public int getDisableCpuHw() {
            return DisableCpuHw;
        }

        public void setDisableCpuHw(int DisableCpuHw) {
            this.DisableCpuHw = DisableCpuHw;
        }

        public int getCpuHealth() {
            return CpuHealth;
        }

        public void setCpuHealth(int CpuHealth) {
            this.CpuHealth = CpuHealth;
        }

        public String getPartNum() {
            return PartNum;
        }

        public void setPartNum(String PartNum) {
            this.PartNum = PartNum;
        }

        public String getSN() {
            return SN;
        }

        public void setSN(String SN) {
            this.SN = SN;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }
}
