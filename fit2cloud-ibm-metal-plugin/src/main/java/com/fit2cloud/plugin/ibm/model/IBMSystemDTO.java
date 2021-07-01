package com.fit2cloud.plugin.ibm.model;

import java.util.List;

public class IBMSystemDTO {
    /*{
        "items" : [
        {
                "MachineName" : "System x3650 M4",
                "MachineType" : "7915",
                "Model" : "OIJ",
                "SerialNumber" : "06LLLM8",
                "UUID" : "E3CE474C4DDD11E297816CAE8B3866B2",
                "system_events" : [
                ]
        }
    ]
    }*/

    private String machineName;
    private String machineType;
    private String serialNumber;
    private String model;
    private String UUID;
    private List<?> systemEvents;

    public List<?> getSystemEvents() {
        return systemEvents;
    }

    public void setSystemEvents(List<?> systemEvents) {
        this.systemEvents = systemEvents;
    }

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

}
