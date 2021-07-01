package com.fit2cloud.plugin.ibm.model;

public class IBMInfoDTO {
    /*{
        "items" : [
        {
            "machine_name" : "System x3650 M4",
                "machine_type" : "7915",
                "serial_number" : "06LLLM8",
                "model" : "OIJ",
                "UUID" : "E3CE474C4DDD11E297816CAE8B3866B2",
                "power_state" : 1,
                "server_state" : 4,
                "power_on_hours" : 50982,
                "restart_count" : 536,
                "ambient_temp" : "64.40 F / 18.00 C",
                "location_status" : 0,
                "info_status" : 0
        }
    ]
    }*/

    private String machineName;
    private String machineType;
    private String serialNumber;
    private String model;
    private String UUID;
    private Long powerState;
    private Long serverState;
    private Long powerOnHours;
    private Long restart;
    private String ambientTemp;
    private Long locationStatus;
    private Long infoStatus;

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Long getPowerState() {
        return powerState;
    }

    public void setPowerState(Long powerState) {
        this.powerState = powerState;
    }

    public Long getServerState() {
        return serverState;
    }

    public void setServerState(Long serverState) {
        this.serverState = serverState;
    }

    public Long getPowerOnHours() {
        return powerOnHours;
    }

    public void setPowerOnHours(Long powerOnHours) {
        this.powerOnHours = powerOnHours;
    }

    public Long getRestart() {
        return restart;
    }

    public void setRestart(Long restart) {
        this.restart = restart;
    }

    public String getAmbientTemp() {
        return ambientTemp;
    }

    public void setAmbientTemp(String ambientTemp) {
        this.ambientTemp = ambientTemp;
    }

    public Long getLocationStatus() {
        return locationStatus;
    }

    public void setLocationStatus(Long locationStatus) {
        this.locationStatus = locationStatus;
    }

    public Long getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Long infoStatus) {
        this.infoStatus = infoStatus;
    }

}
