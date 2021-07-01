package com.fit2cloud.plugin.ibm.model;

import java.util.List;

public class IBMDiskDTO {
    /* {
        "items" : [
        {
            "disks" : [
            {
                    "disks.name" : 0,
                    "disks.status" : "Critical",
                    "disks.health_message" : "The Drive 0 has been disabled due to a detected fault.",
                    "disks.health_date" : "2019-09-24T09:36",
                    "disks.health_event_id" : "806F010D0400FFFF"
            }
            ],
            "disks_events" : [
            {
                    "disks_events.index" : 1,
                    "disks_events.disk_id" : 0,
                    "disks_events.severity" : "E",
                    "disks_events.source" : "Drive 0",
                    "disks_events.date" : "2019-09-24T09:36:16.761",
                    "disks_events.message" : "The Drive 0 has been disabled due to a detected fault.",
                    "disks_events.message_more" : ""
            }
            ]
        }
    ]
    }*/

    private List<Disks> disks;
    private List<DisksEvents> disksEvents;

    public List<Disks> getDisks() {
        return disks;
    }

    public void setDisks(List<Disks> disks) {
        this.disks = disks;
    }

    public List<DisksEvents> getDisksEvents() {
        return disksEvents;
    }

    public void setDisksEvents(List<DisksEvents> disksEvents) {
        this.disksEvents = disksEvents;
    }

    public static class Disks {
        private Long name; // id
        private String status;
        private String healthMessage;
        private String healthDate;
        private String healthEventId;

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

    }

    public static class DisksEvents {
        private Long index;
        private Long diskId;
        private String severity;
        private String source;
        private String date;
        private String message;
        private String messageMore;

        public Long getIndex() {
            return index;
        }

        public void setIndex(Long index) {
            this.index = index;
        }

        public Long getDiskId() {
            return diskId;
        }

        public void setDiskId(Long diskId) {
            this.diskId = diskId;
        }

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessageMore() {
            return messageMore;
        }

        public void setMessageMore(String messageMore) {
            this.messageMore = messageMore;
        }
    }

}
