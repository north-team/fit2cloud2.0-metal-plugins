package com.fit2cloud.plugin.huawei.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HuaweiPhyDrivesDTO {
    @SerializedName("Hdd")
    private List<HddBean> Hdd;

    public List<HddBean> getHdd() {
        return Hdd;
    }

    public void setHdd(List<HddBean> Hdd) {
        this.Hdd = Hdd;
    }

    public static class HddBean {
        /**
         * EnclosureDeviceId : 64
         * ResId : 0
         * CapacityMB : 571250
         * HoursOfPoweredUp : 198
         * Location : 0
         * Fault : 0
         * FirmwareStatus : 6
         * SASAddress2 : 0000000000000000
         * SlotNumber : 0
         * RefRAIDController : RaidController-1
         * RebuildProgress : 0
         * PatrolState : 0
         * SASAddress1 : 5000c500c2296465
         * RemnantMediaWearout : 255
         * Presence : 1
         * SerialNumber : WFJ29K18
         * ModelNumber : ST600MM0009
         * Id : 0
         * DeviceName : Disk0
         * Manufacturer : SEAGATE
         * HddHealth : 0
         * FirmwareVersion : N003
         * PowerState : 0
         * MediaType : 0
         * InterfaceType : 2
         * InterfaceSpeed : 4
         * LinkSpeed : 4
         * Capacity : 557
         * Temperature : 26
         * HotSpare : 0
         * RebuildState : 0
         * PartNum :
         * obj_name : Hdd0-2
         */

        @SerializedName("EnclosureDeviceId")
        private int EnclosureDeviceId;
        @SerializedName("ResId")
        private int ResId;
        @SerializedName("CapacityMB")
        private int CapacityMB;
        @SerializedName("HoursOfPoweredUp")
        private int HoursOfPoweredUp;
        @SerializedName("Location")
        private int Location;
        @SerializedName("Fault")
        private int Fault;
        @SerializedName("FirmwareStatus")
        private int FirmwareStatus;
        @SerializedName("SASAddress2")
        private String SASAddress2;
        @SerializedName("SlotNumber")
        private int SlotNumber;
        @SerializedName("RefRAIDController")
        private String RefRAIDController;
        @SerializedName("RebuildProgress")
        private int RebuildProgress;
        @SerializedName("PatrolState")
        private int PatrolState;
        @SerializedName("SASAddress1")
        private String SASAddress1;
        @SerializedName("RemnantMediaWearout")
        private int RemnantMediaWearout;
        @SerializedName("Presence")
        private int Presence;
        @SerializedName("SerialNumber")
        private String SerialNumber;
        @SerializedName("ModelNumber")
        private String ModelNumber;
        @SerializedName("Id")
        private int Id;
        @SerializedName("DeviceName")
        private String DeviceName;
        @SerializedName("Manufacturer")
        private String Manufacturer;
        @SerializedName("HddHealth")
        private int HddHealth;
        @SerializedName("FirmwareVersion")
        private String FirmwareVersion;
        @SerializedName("PowerState")
        private int PowerState;
        @SerializedName("MediaType")
        private int MediaType;
        @SerializedName("InterfaceType")
        private int InterfaceType;
        @SerializedName("InterfaceSpeed")
        private int InterfaceSpeed;
        @SerializedName("LinkSpeed")
        private int LinkSpeed;
        @SerializedName("Capacity")
        private int Capacity;
        @SerializedName("Temperature")
        private int Temperature;
        @SerializedName("HotSpare")
        private int HotSpare;
        @SerializedName("RebuildState")
        private int RebuildState;
        @SerializedName("PartNum")
        private String PartNum;
        @SerializedName("obj_name")
        private String objName;

        public int getEnclosureDeviceId() {
            return EnclosureDeviceId;
        }

        public void setEnclosureDeviceId(int EnclosureDeviceId) {
            this.EnclosureDeviceId = EnclosureDeviceId;
        }

        public int getResId() {
            return ResId;
        }

        public void setResId(int ResId) {
            this.ResId = ResId;
        }

        public int getCapacityMB() {
            return CapacityMB;
        }

        public void setCapacityMB(int CapacityMB) {
            this.CapacityMB = CapacityMB;
        }

        public int getHoursOfPoweredUp() {
            return HoursOfPoweredUp;
        }

        public void setHoursOfPoweredUp(int HoursOfPoweredUp) {
            this.HoursOfPoweredUp = HoursOfPoweredUp;
        }

        public int getLocation() {
            return Location;
        }

        public void setLocation(int Location) {
            this.Location = Location;
        }

        public int getFault() {
            return Fault;
        }

        public void setFault(int Fault) {
            this.Fault = Fault;
        }

        public int getFirmwareStatus() {
            return FirmwareStatus;
        }

        public void setFirmwareStatus(int FirmwareStatus) {
            this.FirmwareStatus = FirmwareStatus;
        }

        public String getSASAddress2() {
            return SASAddress2;
        }

        public void setSASAddress2(String SASAddress2) {
            this.SASAddress2 = SASAddress2;
        }

        public int getSlotNumber() {
            return SlotNumber;
        }

        public void setSlotNumber(int SlotNumber) {
            this.SlotNumber = SlotNumber;
        }

        public String getRefRAIDController() {
            return RefRAIDController;
        }

        public void setRefRAIDController(String RefRAIDController) {
            this.RefRAIDController = RefRAIDController;
        }

        public int getRebuildProgress() {
            return RebuildProgress;
        }

        public void setRebuildProgress(int RebuildProgress) {
            this.RebuildProgress = RebuildProgress;
        }

        public int getPatrolState() {
            return PatrolState;
        }

        public void setPatrolState(int PatrolState) {
            this.PatrolState = PatrolState;
        }

        public String getSASAddress1() {
            return SASAddress1;
        }

        public void setSASAddress1(String SASAddress1) {
            this.SASAddress1 = SASAddress1;
        }

        public int getRemnantMediaWearout() {
            return RemnantMediaWearout;
        }

        public void setRemnantMediaWearout(int RemnantMediaWearout) {
            this.RemnantMediaWearout = RemnantMediaWearout;
        }

        public int getPresence() {
            return Presence;
        }

        public void setPresence(int Presence) {
            this.Presence = Presence;
        }

        public String getSerialNumber() {
            return SerialNumber;
        }

        public void setSerialNumber(String SerialNumber) {
            this.SerialNumber = SerialNumber;
        }

        public String getModelNumber() {
            return ModelNumber;
        }

        public void setModelNumber(String ModelNumber) {
            this.ModelNumber = ModelNumber;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getDeviceName() {
            return DeviceName;
        }

        public void setDeviceName(String DeviceName) {
            this.DeviceName = DeviceName;
        }

        public String getManufacturer() {
            return Manufacturer;
        }

        public void setManufacturer(String Manufacturer) {
            this.Manufacturer = Manufacturer;
        }

        public int getHddHealth() {
            return HddHealth;
        }

        public void setHddHealth(int HddHealth) {
            this.HddHealth = HddHealth;
        }

        public String getFirmwareVersion() {
            return FirmwareVersion;
        }

        public void setFirmwareVersion(String FirmwareVersion) {
            this.FirmwareVersion = FirmwareVersion;
        }

        public int getPowerState() {
            return PowerState;
        }

        public void setPowerState(int PowerState) {
            this.PowerState = PowerState;
        }

        public int getMediaType() {
            return MediaType;
        }

        public void setMediaType(int MediaType) {
            this.MediaType = MediaType;
        }

        public int getInterfaceType() {
            return InterfaceType;
        }

        public void setInterfaceType(int InterfaceType) {
            this.InterfaceType = InterfaceType;
        }

        public int getInterfaceSpeed() {
            return InterfaceSpeed;
        }

        public void setInterfaceSpeed(int InterfaceSpeed) {
            this.InterfaceSpeed = InterfaceSpeed;
        }

        public int getLinkSpeed() {
            return LinkSpeed;
        }

        public void setLinkSpeed(int LinkSpeed) {
            this.LinkSpeed = LinkSpeed;
        }

        public int getCapacity() {
            return Capacity;
        }

        public void setCapacity(int Capacity) {
            this.Capacity = Capacity;
        }

        public int getTemperature() {
            return Temperature;
        }

        public void setTemperature(int Temperature) {
            this.Temperature = Temperature;
        }

        public int getHotSpare() {
            return HotSpare;
        }

        public void setHotSpare(int HotSpare) {
            this.HotSpare = HotSpare;
        }

        public int getRebuildState() {
            return RebuildState;
        }

        public void setRebuildState(int RebuildState) {
            this.RebuildState = RebuildState;
        }

        public String getPartNum() {
            return PartNum;
        }

        public void setPartNum(String PartNum) {
            this.PartNum = PartNum;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }
}
