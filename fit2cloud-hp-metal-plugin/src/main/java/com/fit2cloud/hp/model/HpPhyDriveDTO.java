
package com.fit2cloud.hp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HpPhyDriveDTO {

    /**
     * hostpwr_state : OFF
     * in_post : 0
     * ams_ready : AMS_UNAVAILABLE
     * data_state : DATA_NOT_AVAILABLE
     * phy_drive_arrays : [{"storage_type":"SMART_ARRAY_CONTROLLER_TYPE","name":"Controller on System Board","status":"OP_STATUS_OK","hw_status":"OP_STATUS_OK","serial_no":"PDNLH0BRH9B44R","model":"Smart Array P440ar Controller","fw_version":"7.00","accel_cond":"OP_STATUS_OK","accel_serial":"PDNLH0BRH9B44R","accel_tot_mem":"2097152 KB","has_accel":1,"encr_stat":"ENCR_NOT_ENABLED","encr_self_stat":"OP_STATUS_OK","encr_csp_stat":"OP_STATUS_OK","has_encrypt":1,"physical_drives":[{"name":"Physical Drive in Port 1I Box 3 Bay 4","status":"OP_STATUS_OK","serial_no":"9XF216VE00009315XGPW","model":"ST9500620SS","capacity":"465 GB","location":"Port 1I Box 3 Bay 4","fw_version":"FTS3","phys_status":"PHYS_OK","drive_type":"PHY_ARRAY","encr_stat":"ENCR_NOT_ENCR","phys_idx":0,"drive_mediatype":"HDD"},{"name":"Physical Drive in Port 1I Box 3 Bay 3","status":"OP_STATUS_OK","serial_no":"9XF1S7FZ00009302YSCV","model":"ST9500620SS","capacity":"465 GB","location":"Port 1I Box 3 Bay 3","fw_version":"FTS1","phys_status":"PHYS_OK","drive_type":"PHY_ARRAY","encr_stat":"ENCR_NOT_ENCR","phys_idx":1,"drive_mediatype":"HDD"},{"name":"Physical Drive in Port 1I Box 3 Bay 2","status":"OP_STATUS_OK","serial_no":"9XF20P2000009317H6K3","model":"ST9500620SS","capacity":"465 GB","location":"Port 1I Box 3 Bay 2","fw_version":"FTS3","phys_status":"PHYS_OK","drive_type":"PHY_ARRAY","encr_stat":"ENCR_NOT_ENCR","phys_idx":2,"drive_mediatype":"HDD"},{"name":"Physical Drive in Port 1I Box 3 Bay 1","status":"OP_STATUS_OK","serial_no":"9XF1VD5X00009308P8LZ","model":"ST9500620SS","capacity":"465 GB","location":"Port 1I Box 3 Bay 1","fw_version":"FTS3","phys_status":"PHYS_OK","drive_type":"PHY_ARRAY","encr_stat":"ENCR_NOT_ENCR","phys_idx":3,"drive_mediatype":"HDD"}],"enclosures":[{"name":"Drive Enclosure Port 1I Box 3","status":"OP_STATUS_OK","ports":"4"},{"name":"Drive Enclosure Port 2I Box 0","status":"OP_STATUS_OK","ports":"4"}]}]
     */

    @SerializedName("hostpwr_state")
    private String hostpwrState;
    @SerializedName("in_post")
    private int inPost;
    @SerializedName("ams_ready")
    private String amsReady;
    @SerializedName("data_state")
    private String dataState;
    @SerializedName("phy_drive_arrays")
    private List<PhyDriveArraysBean> phyDriveArrays;

    public String getHostpwrState() {
        return hostpwrState;
    }

    public void setHostpwrState(String hostpwrState) {
        this.hostpwrState = hostpwrState;
    }

    public int getInPost() {
        return inPost;
    }

    public void setInPost(int inPost) {
        this.inPost = inPost;
    }

    public String getAmsReady() {
        return amsReady;
    }

    public void setAmsReady(String amsReady) {
        this.amsReady = amsReady;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public List<PhyDriveArraysBean> getPhyDriveArrays() {
        return phyDriveArrays;
    }

    public void setPhyDriveArrays(List<PhyDriveArraysBean> phyDriveArrays) {
        this.phyDriveArrays = phyDriveArrays;
    }

    public static class PhyDriveArraysBean {
        /**
         * storage_type : SMART_ARRAY_CONTROLLER_TYPE
         * name : Controller on System Board
         * status : OP_STATUS_OK
         * hw_status : OP_STATUS_OK
         * serial_no : PDNLH0BRH9B44R
         * model : Smart Array P440ar Controller
         * fw_version : 7.00
         * accel_cond : OP_STATUS_OK
         * accel_serial : PDNLH0BRH9B44R
         * accel_tot_mem : 2097152 KB
         * has_accel : 1
         * encr_stat : ENCR_NOT_ENABLED
         * encr_self_stat : OP_STATUS_OK
         * encr_csp_stat : OP_STATUS_OK
         * has_encrypt : 1
         * physical_drives : [{"name":"Physical Drive in Port 1I Box 3 Bay 4","status":"OP_STATUS_OK","serial_no":"9XF216VE00009315XGPW","model":"ST9500620SS","capacity":"465 GB","location":"Port 1I Box 3 Bay 4","fw_version":"FTS3","phys_status":"PHYS_OK","drive_type":"PHY_ARRAY","encr_stat":"ENCR_NOT_ENCR","phys_idx":0,"drive_mediatype":"HDD"},{"name":"Physical Drive in Port 1I Box 3 Bay 3","status":"OP_STATUS_OK","serial_no":"9XF1S7FZ00009302YSCV","model":"ST9500620SS","capacity":"465 GB","location":"Port 1I Box 3 Bay 3","fw_version":"FTS1","phys_status":"PHYS_OK","drive_type":"PHY_ARRAY","encr_stat":"ENCR_NOT_ENCR","phys_idx":1,"drive_mediatype":"HDD"},{"name":"Physical Drive in Port 1I Box 3 Bay 2","status":"OP_STATUS_OK","serial_no":"9XF20P2000009317H6K3","model":"ST9500620SS","capacity":"465 GB","location":"Port 1I Box 3 Bay 2","fw_version":"FTS3","phys_status":"PHYS_OK","drive_type":"PHY_ARRAY","encr_stat":"ENCR_NOT_ENCR","phys_idx":2,"drive_mediatype":"HDD"},{"name":"Physical Drive in Port 1I Box 3 Bay 1","status":"OP_STATUS_OK","serial_no":"9XF1VD5X00009308P8LZ","model":"ST9500620SS","capacity":"465 GB","location":"Port 1I Box 3 Bay 1","fw_version":"FTS3","phys_status":"PHYS_OK","drive_type":"PHY_ARRAY","encr_stat":"ENCR_NOT_ENCR","phys_idx":3,"drive_mediatype":"HDD"}]
         * enclosures : [{"name":"Drive Enclosure Port 1I Box 3","status":"OP_STATUS_OK","ports":"4"},{"name":"Drive Enclosure Port 2I Box 0","status":"OP_STATUS_OK","ports":"4"}]
         */

        @SerializedName("storage_type")
        private String storageType;
        @SerializedName("name")
        private String name;
        @SerializedName("status")
        private String status;
        @SerializedName("hw_status")
        private String hwStatus;
        @SerializedName("serial_no")
        private String serialNo;
        @SerializedName("model")
        private String model;
        @SerializedName("fw_version")
        private String fwVersion;
        @SerializedName("accel_cond")
        private String accelCond;
        @SerializedName("accel_serial")
        private String accelSerial;
        @SerializedName("accel_tot_mem")
        private String accelTotMem;
        @SerializedName("has_accel")
        private int hasAccel;
        @SerializedName("encr_stat")
        private String encrStat;
        @SerializedName("encr_self_stat")
        private String encrSelfStat;
        @SerializedName("encr_csp_stat")
        private String encrCspStat;
        @SerializedName("has_encrypt")
        private int hasEncrypt;
        @SerializedName("physical_drives")
        private List<PhysicalDrivesBean> physicalDrives;
        @SerializedName("enclosures")
        private List<EnclosuresBean> enclosures;

        public String getStorageType() {
            return storageType;
        }

        public void setStorageType(String storageType) {
            this.storageType = storageType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getHwStatus() {
            return hwStatus;
        }

        public void setHwStatus(String hwStatus) {
            this.hwStatus = hwStatus;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getFwVersion() {
            return fwVersion;
        }

        public void setFwVersion(String fwVersion) {
            this.fwVersion = fwVersion;
        }

        public String getAccelCond() {
            return accelCond;
        }

        public void setAccelCond(String accelCond) {
            this.accelCond = accelCond;
        }

        public String getAccelSerial() {
            return accelSerial;
        }

        public void setAccelSerial(String accelSerial) {
            this.accelSerial = accelSerial;
        }

        public String getAccelTotMem() {
            return accelTotMem;
        }

        public void setAccelTotMem(String accelTotMem) {
            this.accelTotMem = accelTotMem;
        }

        public int getHasAccel() {
            return hasAccel;
        }

        public void setHasAccel(int hasAccel) {
            this.hasAccel = hasAccel;
        }

        public String getEncrStat() {
            return encrStat;
        }

        public void setEncrStat(String encrStat) {
            this.encrStat = encrStat;
        }

        public String getEncrSelfStat() {
            return encrSelfStat;
        }

        public void setEncrSelfStat(String encrSelfStat) {
            this.encrSelfStat = encrSelfStat;
        }

        public String getEncrCspStat() {
            return encrCspStat;
        }

        public void setEncrCspStat(String encrCspStat) {
            this.encrCspStat = encrCspStat;
        }

        public int getHasEncrypt() {
            return hasEncrypt;
        }

        public void setHasEncrypt(int hasEncrypt) {
            this.hasEncrypt = hasEncrypt;
        }

        public List<PhysicalDrivesBean> getPhysicalDrives() {
            return physicalDrives;
        }

        public void setPhysicalDrives(List<PhysicalDrivesBean> physicalDrives) {
            this.physicalDrives = physicalDrives;
        }

        public List<EnclosuresBean> getEnclosures() {
            return enclosures;
        }

        public void setEnclosures(List<EnclosuresBean> enclosures) {
            this.enclosures = enclosures;
        }

        public static class PhysicalDrivesBean {
            /**
             * name : Physical Drive in Port 1I Box 3 Bay 4
             * status : OP_STATUS_OK
             * serial_no : 9XF216VE00009315XGPW
             * model : ST9500620SS
             * capacity : 465 GB
             * location : Port 1I Box 3 Bay 4
             * fw_version : FTS3
             * phys_status : PHYS_OK
             * drive_type : PHY_ARRAY
             * encr_stat : ENCR_NOT_ENCR
             * phys_idx : 0
             * drive_mediatype : HDD
             */

            @SerializedName("name")
            private String name;
            @SerializedName("status")
            private String status;
            @SerializedName("serial_no")
            private String serialNo;
            @SerializedName("model")
            private String model;
            @SerializedName("capacity")
            private String capacity;
            @SerializedName("location")
            private String location;
            @SerializedName("fw_version")
            private String fwVersion;
            @SerializedName("phys_status")
            private String physStatus;
            @SerializedName("drive_type")
            private String driveType;
            @SerializedName("encr_stat")
            private String encrStat;
            @SerializedName("phys_idx")
            private int physIdx;
            @SerializedName("drive_mediatype")
            private String driveMediatype;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSerialNo() {
                return serialNo;
            }

            public void setSerialNo(String serialNo) {
                this.serialNo = serialNo;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getCapacity() {
                return capacity;
            }

            public void setCapacity(String capacity) {
                this.capacity = capacity;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getFwVersion() {
                return fwVersion;
            }

            public void setFwVersion(String fwVersion) {
                this.fwVersion = fwVersion;
            }

            public String getPhysStatus() {
                return physStatus;
            }

            public void setPhysStatus(String physStatus) {
                this.physStatus = physStatus;
            }

            public String getDriveType() {
                return driveType;
            }

            public void setDriveType(String driveType) {
                this.driveType = driveType;
            }

            public String getEncrStat() {
                return encrStat;
            }

            public void setEncrStat(String encrStat) {
                this.encrStat = encrStat;
            }

            public int getPhysIdx() {
                return physIdx;
            }

            public void setPhysIdx(int physIdx) {
                this.physIdx = physIdx;
            }

            public String getDriveMediatype() {
                return driveMediatype;
            }

            public void setDriveMediatype(String driveMediatype) {
                this.driveMediatype = driveMediatype;
            }
        }

        public static class EnclosuresBean {
            /**
             * name : Drive Enclosure Port 1I Box 3
             * status : OP_STATUS_OK
             * ports : 4
             */

            @SerializedName("name")
            private String name;
            @SerializedName("status")
            private String status;
            @SerializedName("ports")
            private String ports;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPorts() {
                return ports;
            }

            public void setPorts(String ports) {
                this.ports = ports;
            }
        }
    }
}
