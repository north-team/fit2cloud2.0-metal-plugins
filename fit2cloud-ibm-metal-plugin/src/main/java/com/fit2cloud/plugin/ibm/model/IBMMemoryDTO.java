package com.fit2cloud.plugin.ibm.model;

import java.util.List;

public class IBMMemoryDTO {
    /*{
        "items" : [
        {
            "memory" : [
            {
                "   memory.index" :1,
                    "memory.status" :"Normal",
                    "memory.name" :"DIMM 1",
                    "memory.type" :"DDR3",
                    "memory.health_message" :"",
                    "memory.health_date" :"",
                    "memory.health_event_id" :"",
                    "memory.capacity" :8,
                    "memory.description" :"DIMM 1",
                    "memory.part_number" :"HMT31GR7CFR4C-PB  ",
                    "memory.serial_number" :"2046DF4C",
            }],
            "memory_events" : [
            ]
        }]
    }*/

    private List<Memory> memory;
    private List<MemoryEvents> memoryEvents;

    public List<Memory> getMemory() {
        return memory;
    }

    public void setMemory(List<Memory> memory) {
        this.memory = memory;
    }

    public List<MemoryEvents> getMemoryEvents() {
        return memoryEvents;
    }

    public void setMemoryEvents(List<MemoryEvents> memoryEvents) {
        this.memoryEvents = memoryEvents;
    }

    public static class Memory {
        private Long index;
        private String status;
        private String name;
        private String type;
        private String healthMessage;
        private String healthDate;
        private String healthEventId;
        private Long capacity;
        private String description;
        private String partNumber;
        private String serialNumber;
        private String manufDate;

        public Long getIndex() {
            return index;
        }

        public void setIndex(Long index) {
            this.index = index;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getHealthMessage() {
            return healthMessage;
        }

        public void setHealthMessage(String healthMessage) {
            this.healthMessage = healthMessage;
        }

        public String getHealthDate() {
            return healthDate;
        }

        public void setHealthDate(String healthDate) {
            this.healthDate = healthDate;
        }

        public String getHealthEventId() {
            return healthEventId;
        }

        public void setHealthEventId(String healthEventId) {
            this.healthEventId = healthEventId;
        }

        public Long getCapacity() {
            return capacity;
        }

        public void setCapacity(Long capacity) {
            this.capacity = capacity;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPartNumber() {
            return partNumber;
        }

        public void setPartNumber(String partNumber) {
            this.partNumber = partNumber;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public String getManufDate() {
            return manufDate;
        }

        public void setManufDate(String manufDate) {
            this.manufDate = manufDate;
        }
    }

    // 暂时没取到
    public static class MemoryEvents {

    }
}
