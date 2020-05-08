package com.fit2cloud.plugin.ibm.model;

import java.util.List;

public class IBMCpuDTO {
    /* {
        "items" : [
        {
            "processors" : [
            {
                "processors.name" : 1,
                    "processors.status" : "Normal",
                    "processors.health_message" : "",
                    "processors.health_date" : "",
                    "processors.health_event_id" : "",
                    "processors.clock_speed" : 2600,
                    "processors.fru_name" : "CPU 1",
                    "processors.fru_number" : "144",
                    "processors.manuf_id" : "Intel(R) Corporation",
                    "processors.serial_number" : "Unknown"
            },
            {
                    "processors.name" : 2,
                    "processors.status" : "Normal",
                    "processors.health_message" : "",
                    "processors.health_date" : "",
                    "processors.health_event_id" : "",
                    "processors.clock_speed" : 2600,
                    "processors.fru_name" : "CPU 2",
                    "processors.fru_number" : "145",
                    "processors.manuf_id" : "Intel(R) Corporation",
                    "processors.serial_number" : "Unknown"
            }
            ],
            "processor_events" : [
            ]
        }
    ]
    }*/

    private List<Processors> processors;

    private List<ProcessorEvents> processorEvents;

    public List<Processors> getProcessors() {
        return processors;
    }

    public void setProcessors(List<Processors> processors) {
        this.processors = processors;
    }

    public List<ProcessorEvents> getProcessorEvents() {
        return processorEvents;
    }

    public void setProcessorEvents(List<ProcessorEvents> processorEvents) {
        this.processorEvents = processorEvents;
    }

    public static class Processors {
        private Long name;
        private String status;
        private String healthMessage;
        private String healthEventId;
        private Long clockSpeed;
        private String fruName;
        private String fruNumber;
        private String manufId;
        private String serialNumber;

        public Long getName() {
            return name;
        }

        public void setName(Long name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getHealthMessage() {
            return healthMessage;
        }

        public void setHealthMessage(String healthMessage) {
            this.healthMessage = healthMessage;
        }

        public String getHealthEventId() {
            return healthEventId;
        }

        public void setHealthEventId(String healthEventId) {
            this.healthEventId = healthEventId;
        }

        public Long getClockSpeed() {
            return clockSpeed;
        }

        public void setClockSpeed(Long clockSpeed) {
            this.clockSpeed = clockSpeed;
        }

        public String getFruName() {
            return fruName;
        }

        public void setFruName(String fruName) {
            this.fruName = fruName;
        }

        public String getFruNumber() {
            return fruNumber;
        }

        public void setFruNumber(String fruNumber) {
            this.fruNumber = fruNumber;
        }

        public String getManufId() {
            return manufId;
        }

        public void setManufId(String manufId) {
            this.manufId = manufId;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

    }

    // 暂时没取到
    public static class ProcessorEvents {

    }

}
