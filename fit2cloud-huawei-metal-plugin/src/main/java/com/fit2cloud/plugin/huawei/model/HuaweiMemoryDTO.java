package com.fit2cloud.plugin.huawei.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HuaweiMemoryDTO {
    @SerializedName("Memory")
    private List<MemoryBean> Memory;

    public List<MemoryBean> getMemory() {
        return Memory;
    }

    public void setMemory(List<MemoryBean> Memory) {
        this.Memory = Memory;
    }

    public static class MemoryBean {
        /**
         * DimmName : DIMM000
         * Presence : 1
         * Location : mainboard
         * Manufacturer : Micron
         * Capacity :  32768 MB
         * ClockSpeed :  2666 MHz
         * SN : 0x2339975C
         * Type : DDR4
         * MinimumVoltage : 1200
         * Rank : 2
         * BitWidth : 72
         * Technology : Synchronous| Registered (Buffered)
         * PartNum : Unknown
         * MemHealth : 0
         * RemainLife : 255
         * MediaTemp : 16384
         * ControllerTemp : 16384
         * VolatileCapacity : 0
         * PersistentCapacity : 0
         * HealthStatus : 240
         * obj_name : Memory000
         */

        @SerializedName("DimmName")
        private String DimmName;
        @SerializedName("Presence")
        private int Presence;
        @SerializedName("Location")
        private String Location;
        @SerializedName("Manufacturer")
        private String Manufacturer;
        @SerializedName("Capacity")
        private String Capacity;
        @SerializedName("ClockSpeed")
        private String ClockSpeed;
        @SerializedName("SN")
        private String SN;
        @SerializedName("Type")
        private String Type;
        @SerializedName("MinimumVoltage")
        private int MinimumVoltage;
        @SerializedName("Rank")
        private int Rank;
        @SerializedName("BitWidth")
        private int BitWidth;
        @SerializedName("Technology")
        private String Technology;
        @SerializedName("PartNum")
        private String PartNum;
        @SerializedName("MemHealth")
        private int MemHealth;
        @SerializedName("RemainLife")
        private int RemainLife;
        @SerializedName("MediaTemp")
        private int MediaTemp;
        @SerializedName("ControllerTemp")
        private int ControllerTemp;
        @SerializedName("VolatileCapacity")
        private int VolatileCapacity;
        @SerializedName("PersistentCapacity")
        private int PersistentCapacity;
        @SerializedName("HealthStatus")
        private int HealthStatus;
        @SerializedName("obj_name")
        private String objName;

        public String getDimmName() {
            return DimmName;
        }

        public void setDimmName(String DimmName) {
            this.DimmName = DimmName;
        }

        public int getPresence() {
            return Presence;
        }

        public void setPresence(int Presence) {
            this.Presence = Presence;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
        }

        public String getManufacturer() {
            return Manufacturer;
        }

        public void setManufacturer(String Manufacturer) {
            this.Manufacturer = Manufacturer;
        }

        public String getCapacity() {
            return Capacity;
        }

        public void setCapacity(String Capacity) {
            this.Capacity = Capacity;
        }

        public String getClockSpeed() {
            return ClockSpeed;
        }

        public void setClockSpeed(String ClockSpeed) {
            this.ClockSpeed = ClockSpeed;
        }

        public String getSN() {
            return SN;
        }

        public void setSN(String SN) {
            this.SN = SN;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public int getMinimumVoltage() {
            return MinimumVoltage;
        }

        public void setMinimumVoltage(int MinimumVoltage) {
            this.MinimumVoltage = MinimumVoltage;
        }

        public int getRank() {
            return Rank;
        }

        public void setRank(int Rank) {
            this.Rank = Rank;
        }

        public int getBitWidth() {
            return BitWidth;
        }

        public void setBitWidth(int BitWidth) {
            this.BitWidth = BitWidth;
        }

        public String getTechnology() {
            return Technology;
        }

        public void setTechnology(String Technology) {
            this.Technology = Technology;
        }

        public String getPartNum() {
            return PartNum;
        }

        public void setPartNum(String PartNum) {
            this.PartNum = PartNum;
        }

        public int getMemHealth() {
            return MemHealth;
        }

        public void setMemHealth(int MemHealth) {
            this.MemHealth = MemHealth;
        }

        public int getRemainLife() {
            return RemainLife;
        }

        public void setRemainLife(int RemainLife) {
            this.RemainLife = RemainLife;
        }

        public int getMediaTemp() {
            return MediaTemp;
        }

        public void setMediaTemp(int MediaTemp) {
            this.MediaTemp = MediaTemp;
        }

        public int getControllerTemp() {
            return ControllerTemp;
        }

        public void setControllerTemp(int ControllerTemp) {
            this.ControllerTemp = ControllerTemp;
        }

        public int getVolatileCapacity() {
            return VolatileCapacity;
        }

        public void setVolatileCapacity(int VolatileCapacity) {
            this.VolatileCapacity = VolatileCapacity;
        }

        public int getPersistentCapacity() {
            return PersistentCapacity;
        }

        public void setPersistentCapacity(int PersistentCapacity) {
            this.PersistentCapacity = PersistentCapacity;
        }

        public int getHealthStatus() {
            return HealthStatus;
        }

        public void setHealthStatus(int HealthStatus) {
            this.HealthStatus = HealthStatus;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }
}
