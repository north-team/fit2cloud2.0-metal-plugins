package com.fit2cloud.plugin.huawei.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HuaweiLogDrivesDTO {
    @SerializedName("LogicalDrive")
    private List<LogicalDriveBean> LogicalDrive;

    public List<LogicalDriveBean> getLogicalDrive() {
        return LogicalDrive;
    }

    public void setLogicalDrive(List<LogicalDriveBean> LogicalDrive) {
        this.LogicalDrive = LogicalDrive;
    }

    public static class LogicalDriveBean {
        /**
         * DedicatedSparedPDEnclosures : [65535]
         * ParticipatedPDEnclosure : [64,64,64,64]
         * OSDriveName : N/A
         * AccessPolicy : 0
         * DriveName : N/A
         * DedicatedSparedPDSlots : [255]
         * InitState : 0
         * SpanDepth : 2
         * BGIEnabled : 1
         * IsSSCD : 0
         * CacheCadeLD : 0
         * DiskCachePolicy : 0
         * ParticipatedPDSlot : [0,1,2,3]
         * TargetID : 0
         * IsBootable : 1
         * Size : 1142500
         * RaidLevel : 10
         * StripSize : 7
         * DriveStatus : 3
         * CurrentReadPolicy : 0
         * DefaultReadPolicy : 0
         * CurrentWritePolicy : 0
         * DefaultWritePolicy : 0
         * CurrentCachePolicy : 1
         * DefaultCachePolicy : 1
         * ConsistencyCheck : 0
         * RefRAIDController : RaidController-1
         * RefArray : [0,1,65535,65535,65535,65535,65535,65535]
         * obj_name : LogicalDrive0-101
         */

        @SerializedName("OSDriveName")
        private String OSDriveName;
        @SerializedName("AccessPolicy")
        private int AccessPolicy;
        @SerializedName("DriveName")
        private String DriveName;
        @SerializedName("InitState")
        private int InitState;
        @SerializedName("SpanDepth")
        private int SpanDepth;
        @SerializedName("BGIEnabled")
        private int BGIEnabled;
        @SerializedName("IsSSCD")
        private int IsSSCD;
        @SerializedName("CacheCadeLD")
        private int CacheCadeLD;
        @SerializedName("DiskCachePolicy")
        private int DiskCachePolicy;
        @SerializedName("TargetID")
        private int TargetID;
        @SerializedName("IsBootable")
        private int IsBootable;
        @SerializedName("Size")
        private int Size;
        @SerializedName("RaidLevel")
        private int RaidLevel;
        @SerializedName("StripSize")
        private int StripSize;
        @SerializedName("DriveStatus")
        private int DriveStatus;
        @SerializedName("CurrentReadPolicy")
        private int CurrentReadPolicy;
        @SerializedName("DefaultReadPolicy")
        private int DefaultReadPolicy;
        @SerializedName("CurrentWritePolicy")
        private int CurrentWritePolicy;
        @SerializedName("DefaultWritePolicy")
        private int DefaultWritePolicy;
        @SerializedName("CurrentCachePolicy")
        private int CurrentCachePolicy;
        @SerializedName("DefaultCachePolicy")
        private int DefaultCachePolicy;
        @SerializedName("ConsistencyCheck")
        private int ConsistencyCheck;
        @SerializedName("RefRAIDController")
        private String RefRAIDController;
        @SerializedName("obj_name")
        private String objName;
        @SerializedName("DedicatedSparedPDEnclosures")
        private List<Integer> DedicatedSparedPDEnclosures;
        @SerializedName("ParticipatedPDEnclosure")
        private List<Integer> ParticipatedPDEnclosure;
        @SerializedName("DedicatedSparedPDSlots")
        private List<Integer> DedicatedSparedPDSlots;
        @SerializedName("ParticipatedPDSlot")
        private List<Integer> ParticipatedPDSlot;
        @SerializedName("RefArray")
        private List<Integer> RefArray;

        public String getOSDriveName() {
            return OSDriveName;
        }

        public void setOSDriveName(String OSDriveName) {
            this.OSDriveName = OSDriveName;
        }

        public int getAccessPolicy() {
            return AccessPolicy;
        }

        public void setAccessPolicy(int AccessPolicy) {
            this.AccessPolicy = AccessPolicy;
        }

        public String getDriveName() {
            return DriveName;
        }

        public void setDriveName(String DriveName) {
            this.DriveName = DriveName;
        }

        public int getInitState() {
            return InitState;
        }

        public void setInitState(int InitState) {
            this.InitState = InitState;
        }

        public int getSpanDepth() {
            return SpanDepth;
        }

        public void setSpanDepth(int SpanDepth) {
            this.SpanDepth = SpanDepth;
        }

        public int getBGIEnabled() {
            return BGIEnabled;
        }

        public void setBGIEnabled(int BGIEnabled) {
            this.BGIEnabled = BGIEnabled;
        }

        public int getIsSSCD() {
            return IsSSCD;
        }

        public void setIsSSCD(int IsSSCD) {
            this.IsSSCD = IsSSCD;
        }

        public int getCacheCadeLD() {
            return CacheCadeLD;
        }

        public void setCacheCadeLD(int CacheCadeLD) {
            this.CacheCadeLD = CacheCadeLD;
        }

        public int getDiskCachePolicy() {
            return DiskCachePolicy;
        }

        public void setDiskCachePolicy(int DiskCachePolicy) {
            this.DiskCachePolicy = DiskCachePolicy;
        }

        public int getTargetID() {
            return TargetID;
        }

        public void setTargetID(int TargetID) {
            this.TargetID = TargetID;
        }

        public int getIsBootable() {
            return IsBootable;
        }

        public void setIsBootable(int IsBootable) {
            this.IsBootable = IsBootable;
        }

        public int getSize() {
            return Size;
        }

        public void setSize(int Size) {
            this.Size = Size;
        }

        public int getRaidLevel() {
            return RaidLevel;
        }

        public void setRaidLevel(int RaidLevel) {
            this.RaidLevel = RaidLevel;
        }

        public int getStripSize() {
            return StripSize;
        }

        public void setStripSize(int StripSize) {
            this.StripSize = StripSize;
        }

        public int getDriveStatus() {
            return DriveStatus;
        }

        public void setDriveStatus(int DriveStatus) {
            this.DriveStatus = DriveStatus;
        }

        public int getCurrentReadPolicy() {
            return CurrentReadPolicy;
        }

        public void setCurrentReadPolicy(int CurrentReadPolicy) {
            this.CurrentReadPolicy = CurrentReadPolicy;
        }

        public int getDefaultReadPolicy() {
            return DefaultReadPolicy;
        }

        public void setDefaultReadPolicy(int DefaultReadPolicy) {
            this.DefaultReadPolicy = DefaultReadPolicy;
        }

        public int getCurrentWritePolicy() {
            return CurrentWritePolicy;
        }

        public void setCurrentWritePolicy(int CurrentWritePolicy) {
            this.CurrentWritePolicy = CurrentWritePolicy;
        }

        public int getDefaultWritePolicy() {
            return DefaultWritePolicy;
        }

        public void setDefaultWritePolicy(int DefaultWritePolicy) {
            this.DefaultWritePolicy = DefaultWritePolicy;
        }

        public int getCurrentCachePolicy() {
            return CurrentCachePolicy;
        }

        public void setCurrentCachePolicy(int CurrentCachePolicy) {
            this.CurrentCachePolicy = CurrentCachePolicy;
        }

        public int getDefaultCachePolicy() {
            return DefaultCachePolicy;
        }

        public void setDefaultCachePolicy(int DefaultCachePolicy) {
            this.DefaultCachePolicy = DefaultCachePolicy;
        }

        public int getConsistencyCheck() {
            return ConsistencyCheck;
        }

        public void setConsistencyCheck(int ConsistencyCheck) {
            this.ConsistencyCheck = ConsistencyCheck;
        }

        public String getRefRAIDController() {
            return RefRAIDController;
        }

        public void setRefRAIDController(String RefRAIDController) {
            this.RefRAIDController = RefRAIDController;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }

        public List<Integer> getDedicatedSparedPDEnclosures() {
            return DedicatedSparedPDEnclosures;
        }

        public void setDedicatedSparedPDEnclosures(List<Integer> DedicatedSparedPDEnclosures) {
            this.DedicatedSparedPDEnclosures = DedicatedSparedPDEnclosures;
        }

        public List<Integer> getParticipatedPDEnclosure() {
            return ParticipatedPDEnclosure;
        }

        public void setParticipatedPDEnclosure(List<Integer> ParticipatedPDEnclosure) {
            this.ParticipatedPDEnclosure = ParticipatedPDEnclosure;
        }

        public List<Integer> getDedicatedSparedPDSlots() {
            return DedicatedSparedPDSlots;
        }

        public void setDedicatedSparedPDSlots(List<Integer> DedicatedSparedPDSlots) {
            this.DedicatedSparedPDSlots = DedicatedSparedPDSlots;
        }

        public List<Integer> getParticipatedPDSlot() {
            return ParticipatedPDSlot;
        }

        public void setParticipatedPDSlot(List<Integer> ParticipatedPDSlot) {
            this.ParticipatedPDSlot = ParticipatedPDSlot;
        }

        public List<Integer> getRefArray() {
            return RefArray;
        }

        public void setRefArray(List<Integer> RefArray) {
            this.RefArray = RefArray;
        }
    }
}
