
package com.fit2cloud.hp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HpOverViewDTO {


    /**
     * server_name :
     * product_name : ProLiant DL380 Gen9
     * serial_num : 6CU534VDMJ
     * virtual_serial_num : null
     * product_id : 719064-B21
     * uuid : 30393137-3436-4336-5535-333456444D4A
     * virtual_uuid : null
     * system_rom : P89 v2.72 (03/25/2019)
     * system_rom_date : 03/25/2019
     * backup_rom_date : 07/18/2016
     * license : iLO Advanced
     * ilo_fw_version : 2.40 Dec 02 2015
     * ilo_fw_bootleg :
     * nic : 0
     * ip_address : 192.168.1.105
     * ipv6_link_local : FE80::3EA8:2AFF:FE18:8F40
     * system_health : OP_STATUS_OK
     * uid_led : UID_OFF
     * power : OFF
     * date : Mon Apr 27 14:01:58 2020
     * https_port : 443
     * ilo_name : ILO6CU534VDMJ.
     * removable_hw : [{"tpm_status":"NOT_PRESENT","module_type":"UNSPECIFIED","sd_card":"NOT_PRESENT"}]
     * option_ROM_measuring : Disabled
     * has_reset_priv : 1
     * chassis_sn :
     * isUEFI : 1
     * ers_state : ERS_INACTIVE
     */

    @SerializedName("server_name")
    private String serverName;
    @SerializedName("product_name")
    private String productName;
    @SerializedName("serial_num")
    private String serialNum;
    @SerializedName("virtual_serial_num")
    private Object virtualSerialNum;
    @SerializedName("product_id")
    private String productId;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("virtual_uuid")
    private Object virtualUuid;
    @SerializedName("system_rom")
    private String systemRom;
    @SerializedName("system_rom_date")
    private String systemRomDate;
    @SerializedName("backup_rom_date")
    private String backupRomDate;
    @SerializedName("license")
    private String license;
    @SerializedName("ilo_fw_version")
    private String iloFwVersion;
    @SerializedName("ilo_fw_bootleg")
    private String iloFwBootleg;
    @SerializedName("nic")
    private int nic;
    @SerializedName("ip_address")
    private String ipAddress;
    @SerializedName("ipv6_link_local")
    private String ipv6LinkLocal;
    @SerializedName("system_health")
    private String systemHealth;
    @SerializedName("uid_led")
    private String uidLed;
    @SerializedName("power")
    private String power;
    @SerializedName("date")
    private String date;
    @SerializedName("https_port")
    private int httpsPort;
    @SerializedName("ilo_name")
    private String iloName;
    @SerializedName("option_ROM_measuring")
    private String optionROMMeasuring;
    @SerializedName("has_reset_priv")
    private int hasResetPriv;
    @SerializedName("chassis_sn")
    private String chassisSn;
    @SerializedName("isUEFI")
    private int isUEFI;
    @SerializedName("ers_state")
    private String ersState;
    @SerializedName("removable_hw")
    private List<RemovableHwBean> removableHw;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Object getVirtualSerialNum() {
        return virtualSerialNum;
    }

    public void setVirtualSerialNum(Object virtualSerialNum) {
        this.virtualSerialNum = virtualSerialNum;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Object getVirtualUuid() {
        return virtualUuid;
    }

    public void setVirtualUuid(Object virtualUuid) {
        this.virtualUuid = virtualUuid;
    }

    public String getSystemRom() {
        return systemRom;
    }

    public void setSystemRom(String systemRom) {
        this.systemRom = systemRom;
    }

    public String getSystemRomDate() {
        return systemRomDate;
    }

    public void setSystemRomDate(String systemRomDate) {
        this.systemRomDate = systemRomDate;
    }

    public String getBackupRomDate() {
        return backupRomDate;
    }

    public void setBackupRomDate(String backupRomDate) {
        this.backupRomDate = backupRomDate;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getIloFwVersion() {
        return iloFwVersion;
    }

    public void setIloFwVersion(String iloFwVersion) {
        this.iloFwVersion = iloFwVersion;
    }

    public String getIloFwBootleg() {
        return iloFwBootleg;
    }

    public void setIloFwBootleg(String iloFwBootleg) {
        this.iloFwBootleg = iloFwBootleg;
    }

    public int getNic() {
        return nic;
    }

    public void setNic(int nic) {
        this.nic = nic;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpv6LinkLocal() {
        return ipv6LinkLocal;
    }

    public void setIpv6LinkLocal(String ipv6LinkLocal) {
        this.ipv6LinkLocal = ipv6LinkLocal;
    }

    public String getSystemHealth() {
        return systemHealth;
    }

    public void setSystemHealth(String systemHealth) {
        this.systemHealth = systemHealth;
    }

    public String getUidLed() {
        return uidLed;
    }

    public void setUidLed(String uidLed) {
        this.uidLed = uidLed;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHttpsPort() {
        return httpsPort;
    }

    public void setHttpsPort(int httpsPort) {
        this.httpsPort = httpsPort;
    }

    public String getIloName() {
        return iloName;
    }

    public void setIloName(String iloName) {
        this.iloName = iloName;
    }

    public String getOptionROMMeasuring() {
        return optionROMMeasuring;
    }

    public void setOptionROMMeasuring(String optionROMMeasuring) {
        this.optionROMMeasuring = optionROMMeasuring;
    }

    public int getHasResetPriv() {
        return hasResetPriv;
    }

    public void setHasResetPriv(int hasResetPriv) {
        this.hasResetPriv = hasResetPriv;
    }

    public String getChassisSn() {
        return chassisSn;
    }

    public void setChassisSn(String chassisSn) {
        this.chassisSn = chassisSn;
    }

    public int getIsUEFI() {
        return isUEFI;
    }

    public void setIsUEFI(int isUEFI) {
        this.isUEFI = isUEFI;
    }

    public String getErsState() {
        return ersState;
    }

    public void setErsState(String ersState) {
        this.ersState = ersState;
    }

    public List<RemovableHwBean> getRemovableHw() {
        return removableHw;
    }

    public void setRemovableHw(List<RemovableHwBean> removableHw) {
        this.removableHw = removableHw;
    }

    public static class RemovableHwBean {
        /**
         * tpm_status : NOT_PRESENT
         * module_type : UNSPECIFIED
         * sd_card : NOT_PRESENT
         */

        @SerializedName("tpm_status")
        private String tpmStatus;
        @SerializedName("module_type")
        private String moduleType;
        @SerializedName("sd_card")
        private String sdCard;

        public String getTpmStatus() {
            return tpmStatus;
        }

        public void setTpmStatus(String tpmStatus) {
            this.tpmStatus = tpmStatus;
        }

        public String getModuleType() {
            return moduleType;
        }

        public void setModuleType(String moduleType) {
            this.moduleType = moduleType;
        }

        public String getSdCard() {
            return sdCard;
        }

        public void setSdCard(String sdCard) {
            this.sdCard = sdCard;
        }
    }
}
