package com.fit2cloud.plugin.huawei.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HuaweiBMCDTO {
    @SerializedName("BMC")
    private List<BMCBean> BMC;
    @SerializedName("Fru")
    private List<FruBean> Fru;
    @SerializedName("Elabel")
    private List<ElabelBean> Elabel;
    @SerializedName("Bios")
    private List<BiosBean> Bios;
    @SerializedName("BOARD")
    private List<BOARDBean> BOARD;
    @SerializedName("PRODUCT")
    private List<PRODUCTBean> PRODUCT;
    @SerializedName("MainBoard")
    private List<MainBoardBean> MainBoard;

    public List<BMCBean> getBMC() {
        return BMC;
    }

    public void setBMC(List<BMCBean> BMC) {
        this.BMC = BMC;
    }

    public List<FruBean> getFru() {
        return Fru;
    }

    public void setFru(List<FruBean> Fru) {
        this.Fru = Fru;
    }

    public List<ElabelBean> getElabel() {
        return Elabel;
    }

    public void setElabel(List<ElabelBean> Elabel) {
        this.Elabel = Elabel;
    }

    public List<BiosBean> getBios() {
        return Bios;
    }

    public void setBios(List<BiosBean> Bios) {
        this.Bios = Bios;
    }

    public List<BOARDBean> getBOARD() {
        return BOARD;
    }

    public void setBOARD(List<BOARDBean> BOARD) {
        this.BOARD = BOARD;
    }

    public List<PRODUCTBean> getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(List<PRODUCTBean> PRODUCT) {
        this.PRODUCT = PRODUCT;
    }

    public List<MainBoardBean> getMainBoard() {
        return MainBoard;
    }

    public void setMainBoard(List<MainBoardBean> MainBoard) {
        this.MainBoard = MainBoard;
    }

    public static class BMCBean {
        /**
         * CpldVersion : 2.02
         * PMEVer : 3.62
         * FlashUnitNum : 68
         * CpldUnitNum : 6076
         * UbootVersion : 2.1.13 (Dec 24 2018 - 20:23:20)
         * UbootBackupVer : 2.1.13 (Dec 24 2018 - 20:23:20)
         * SystemName : TaiShan 2280 V2
         * DeviceId : 1
         * DeviceSerialNumber : 2102312NFF10K9000095
         * obj_name : BMC
         */

        @SerializedName("CpldVersion")
        private String CpldVersion;
        @SerializedName("PMEVer")
        private String PMEVer;
        @SerializedName("FlashUnitNum")
        private int FlashUnitNum;
        @SerializedName("CpldUnitNum")
        private int CpldUnitNum;
        @SerializedName("UbootVersion")
        private String UbootVersion;
        @SerializedName("UbootBackupVer")
        private String UbootBackupVer;
        @SerializedName("SystemName")
        private String SystemName;
        @SerializedName("DeviceId")
        private int DeviceId;
        @SerializedName("DeviceSerialNumber")
        private String DeviceSerialNumber;
        @SerializedName("obj_name")
        private String objName;

        public String getCpldVersion() {
            return CpldVersion;
        }

        public void setCpldVersion(String CpldVersion) {
            this.CpldVersion = CpldVersion;
        }

        public String getPMEVer() {
            return PMEVer;
        }

        public void setPMEVer(String PMEVer) {
            this.PMEVer = PMEVer;
        }

        public int getFlashUnitNum() {
            return FlashUnitNum;
        }

        public void setFlashUnitNum(int FlashUnitNum) {
            this.FlashUnitNum = FlashUnitNum;
        }

        public int getCpldUnitNum() {
            return CpldUnitNum;
        }

        public void setCpldUnitNum(int CpldUnitNum) {
            this.CpldUnitNum = CpldUnitNum;
        }

        public String getUbootVersion() {
            return UbootVersion;
        }

        public void setUbootVersion(String UbootVersion) {
            this.UbootVersion = UbootVersion;
        }

        public String getUbootBackupVer() {
            return UbootBackupVer;
        }

        public void setUbootBackupVer(String UbootBackupVer) {
            this.UbootBackupVer = UbootBackupVer;
        }

        public String getSystemName() {
            return SystemName;
        }

        public void setSystemName(String SystemName) {
            this.SystemName = SystemName;
        }

        public int getDeviceId() {
            return DeviceId;
        }

        public void setDeviceId(int DeviceId) {
            this.DeviceId = DeviceId;
        }

        public String getDeviceSerialNumber() {
            return DeviceSerialNumber;
        }

        public void setDeviceSerialNumber(String DeviceSerialNumber) {
            this.DeviceSerialNumber = DeviceSerialNumber;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }

    public static class FruBean {
        /**
         * Name : Mainboard
         * PcbVersion : .A
         * BoardId : 185
         * obj_name : MotherBoard
         */

        @SerializedName("Name")
        private String Name;
        @SerializedName("PcbVersion")
        private String PcbVersion;
        @SerializedName("BoardId")
        private int BoardId;
        @SerializedName("obj_name")
        private String objName;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPcbVersion() {
            return PcbVersion;
        }

        public void setPcbVersion(String PcbVersion) {
            this.PcbVersion = PcbVersion;
        }

        public int getBoardId() {
            return BoardId;
        }

        public void setBoardId(int BoardId) {
            this.BoardId = BoardId;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }

    public static class ElabelBean {
        /**
         * ProductAssetTag :
         * BoardManufacturer : Huawei
         * BoardSerialNumber : 026PPV10K8005694
         * BoardProductName : BC82AMDDA
         * ProductPartNumber : 02312NFF
         * obj_name : Fru0Elabel
         */

        @SerializedName("ProductAssetTag")
        private String ProductAssetTag;
        @SerializedName("BoardManufacturer")
        private String BoardManufacturer;
        @SerializedName("BoardSerialNumber")
        private String BoardSerialNumber;
        @SerializedName("BoardProductName")
        private String BoardProductName;
        @SerializedName("ProductPartNumber")
        private String ProductPartNumber;
        @SerializedName("obj_name")
        private String objName;

        public String getProductAssetTag() {
            return ProductAssetTag;
        }

        public void setProductAssetTag(String ProductAssetTag) {
            this.ProductAssetTag = ProductAssetTag;
        }

        public String getBoardManufacturer() {
            return BoardManufacturer;
        }

        public void setBoardManufacturer(String BoardManufacturer) {
            this.BoardManufacturer = BoardManufacturer;
        }

        public String getBoardSerialNumber() {
            return BoardSerialNumber;
        }

        public void setBoardSerialNumber(String BoardSerialNumber) {
            this.BoardSerialNumber = BoardSerialNumber;
        }

        public String getBoardProductName() {
            return BoardProductName;
        }

        public void setBoardProductName(String BoardProductName) {
            this.BoardProductName = BoardProductName;
        }

        public String getProductPartNumber() {
            return ProductPartNumber;
        }

        public void setProductPartNumber(String ProductPartNumber) {
            this.ProductPartNumber = ProductPartNumber;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }

    public static class BiosBean {
        /**
         * Version : 0.98
         * UnitNum : 75
         * obj_name : Bios
         */

        @SerializedName("Version")
        private String Version;
        @SerializedName("UnitNum")
        private int UnitNum;
        @SerializedName("obj_name")
        private String objName;

        public String getVersion() {
            return Version;
        }

        public void setVersion(String Version) {
            this.Version = Version;
        }

        public int getUnitNum() {
            return UnitNum;
        }

        public void setUnitNum(int UnitNum) {
            this.UnitNum = UnitNum;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }

    public static class BOARDBean {
        /**
         * Name : TaiShan 2280
         * SlotId : 0
         * obj_name : BOARD
         */

        @SerializedName("Name")
        private String Name;
        @SerializedName("SlotId")
        private int SlotId;
        @SerializedName("obj_name")
        private String objName;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getSlotId() {
            return SlotId;
        }

        public void setSlotId(int SlotId) {
            this.SlotId = SlotId;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }

    public static class PRODUCTBean {
        /**
         * ChassisNum : 0
         * ProductName : TaiShan 2280
         * obj_name : PRODUCT
         */

        @SerializedName("ChassisNum")
        private int ChassisNum;
        @SerializedName("ProductName")
        private String ProductName;
        @SerializedName("obj_name")
        private String objName;

        public int getChassisNum() {
            return ChassisNum;
        }

        public void setChassisNum(int ChassisNum) {
            this.ChassisNum = ChassisNum;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }

    public static class MainBoardBean {
        /**
         * PCHModel :
         * obj_name : MainBoard
         */

        @SerializedName("PCHModel")
        private String PCHModel;
        @SerializedName("obj_name")
        private String objName;

        public String getPCHModel() {
            return PCHModel;
        }

        public void setPCHModel(String PCHModel) {
            this.PCHModel = PCHModel;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }
}
