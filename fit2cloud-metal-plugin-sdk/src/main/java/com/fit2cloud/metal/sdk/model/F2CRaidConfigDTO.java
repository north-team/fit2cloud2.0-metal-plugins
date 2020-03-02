package com.fit2cloud.metal.sdk.model;

import java.util.LinkedList;
import java.util.List;

public class F2CRaidConfigDTO {
    private List<RaidConfig> raidConfigList = new LinkedList<>();

    public List<RaidConfig> getRaidConfigList() {
        return raidConfigList;
    }

    public void setRaidConfigList(List<RaidConfig> raidConfigList) {
        this.raidConfigList = raidConfigList;
    }

    public void addRaidConfig(RaidConfig raidConfig) {
        raidConfigList.add(raidConfig);
    }

    public static class RaidConfig {
        private String raid;
        private List<F2CPhysicalDisk> disks;

        public String getRaid() {
            return raid;
        }

        public void setRaid(String raid) {
            this.raid = raid;
        }

        public List<F2CPhysicalDisk> getDisks() {
            return disks;
        }

        public void setDisks(List<F2CPhysicalDisk> disks) {
            this.disks = disks;
        }
    }
}
