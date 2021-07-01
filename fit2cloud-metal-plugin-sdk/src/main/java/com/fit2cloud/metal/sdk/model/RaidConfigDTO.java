package com.fit2cloud.metal.sdk.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.LinkedList;
import java.util.List;

/**
 * raidconfig配置
 */
public class RaidConfigDTO {
    private List<OneRaidConfig> raidConfigs = new LinkedList<>();

    public List<OneRaidConfig> getRaidConfigs() {
        return raidConfigs;
    }

    public void setRaidConfigs(List<OneRaidConfig> raidConfigs) {
        this.raidConfigs = raidConfigs;
    }

    public static class OneRaidConfig {
        private String raidType;
        private List<F2CPmDisk> raidDisks;

        public String getRaidType() {
            return raidType;
        }

        public void setRaidType(String raidType) {
            this.raidType = raidType;
        }

        public List<F2CPmDisk> getRaidDisks() {
            return raidDisks;
        }

        public void setRaidDisks(List<F2CPmDisk> raidDisks) {
            this.raidDisks = raidDisks;
        }
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
