package com.fit2cloud.plugin.ibm.model;

import java.util.List;

public class IBMCoolingDevices {
    /*{
        "items" : [
        {
            "cooling" : [
            {
                "cooling.name" : 0,
                    "cooling.fan" : "Fan 1A Tach",
                    "cooling.speed_rpm" : 3515,
                    "cooling.speed_percent" : 37,
                    "cooling.status" : "Normal",
                    "cooling.health_message" : "",
                    "cooling.health_date" : "",
                    "cooling.health_event_id" : "",
                    "cooling.sensor_number" : 64
            },
            "cooling_events" : [
            ]
        }
    }*/

    private List<Cooling> cooling;
    private List<CoolingEvents> coolingEvents;

    public List<Cooling> getCooling() {
        return cooling;
    }

    public void setCooling(List<Cooling> cooling) {
        this.cooling = cooling;
    }

    public List<CoolingEvents> getCoolingEvents() {
        return coolingEvents;
    }

    public void setCoolingEvents(List<CoolingEvents> coolingEvents) {
        this.coolingEvents = coolingEvents;
    }

    public static class Cooling {
        private Long name;
        private String fan;
        private String speedRpm;
        private Long speedPercent;
        private String status;
        private String healthMessage;
        private String healthDate;
        private String healthEventId;
        private Long sensorNumber;

        public Long getName() {
            return name;
        }

        public void setName(Long name) {
            this.name = name;
        }

        public String getFan() {
            return fan;
        }

        public void setFan(String fan) {
            this.fan = fan;
        }

        public String getSpeedRpm() {
            return speedRpm;
        }

        public void setSpeedRpm(String speedRpm) {
            this.speedRpm = speedRpm;
        }

        public Long getSpeedPercent() {
            return speedPercent;
        }

        public void setSpeedPercent(Long speedPercent) {
            this.speedPercent = speedPercent;
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

        public Long getSensorNumber() {
            return sensorNumber;
        }

        public void setSensorNumber(Long sensorNumber) {
            this.sensorNumber = sensorNumber;
        }
    }

    public static class CoolingEvents {

    }
}
