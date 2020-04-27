
package com.fit2cloud.hp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HpCpuDTO {


    /**
     * hostpwr_state : OFF
     * processors : [{"proc_socket":"Proc 1","proc_name":"Intel(R) Xeon(R) CPU E5-2620 v3 @ 2.40GHz","proc_status":"OP_STATUS_OK","proc_speed":2400,"proc_num_cores_enabled":6,"proc_num_cores":6,"proc_num_threads":12,"proc_mem_technology":"64-bit Capable","proc_num_l1cache":384,"proc_num_l2cache":1536,"proc_num_l3cache":15360},{"proc_socket":"Proc 2","proc_name":"Intel(R) Xeon(R) CPU E5-2620 v3 @ 2.40GHz","proc_status":"OP_STATUS_OK","proc_speed":2400,"proc_num_cores_enabled":6,"proc_num_cores":6,"proc_num_threads":12,"proc_mem_technology":"64-bit Capable","proc_num_l1cache":384,"proc_num_l2cache":1536,"proc_num_l3cache":15360}]
     */

    @SerializedName("hostpwr_state")
    private String hostpwrState;
    @SerializedName("processors")
    private List<ProcessorsBean> processors;

    public String getHostpwrState() {
        return hostpwrState;
    }

    public void setHostpwrState(String hostpwrState) {
        this.hostpwrState = hostpwrState;
    }

    public List<ProcessorsBean> getProcessors() {
        return processors;
    }

    public void setProcessors(List<ProcessorsBean> processors) {
        this.processors = processors;
    }

    public static class ProcessorsBean {
        /**
         * proc_socket : Proc 1
         * proc_name : Intel(R) Xeon(R) CPU E5-2620 v3 @ 2.40GHz
         * proc_status : OP_STATUS_OK
         * proc_speed : 2400
         * proc_num_cores_enabled : 6
         * proc_num_cores : 6
         * proc_num_threads : 12
         * proc_mem_technology : 64-bit Capable
         * proc_num_l1cache : 384
         * proc_num_l2cache : 1536
         * proc_num_l3cache : 15360
         */

        @SerializedName("proc_socket")
        private String procSocket;
        @SerializedName("proc_name")
        private String procName;
        @SerializedName("proc_status")
        private String procStatus;
        @SerializedName("proc_speed")
        private int procSpeed;
        @SerializedName("proc_num_cores_enabled")
        private int procNumCoresEnabled;
        @SerializedName("proc_num_cores")
        private int procNumCores;
        @SerializedName("proc_num_threads")
        private int procNumThreads;
        @SerializedName("proc_mem_technology")
        private String procMemTechnology;
        @SerializedName("proc_num_l1cache")
        private int procNumL1cache;
        @SerializedName("proc_num_l2cache")
        private int procNumL2cache;
        @SerializedName("proc_num_l3cache")
        private int procNumL3cache;

        public String getProcSocket() {
            return procSocket;
        }

        public void setProcSocket(String procSocket) {
            this.procSocket = procSocket;
        }

        public String getProcName() {
            return procName;
        }

        public void setProcName(String procName) {
            this.procName = procName;
        }

        public String getProcStatus() {
            return procStatus;
        }

        public void setProcStatus(String procStatus) {
            this.procStatus = procStatus;
        }

        public int getProcSpeed() {
            return procSpeed;
        }

        public void setProcSpeed(int procSpeed) {
            this.procSpeed = procSpeed;
        }

        public int getProcNumCoresEnabled() {
            return procNumCoresEnabled;
        }

        public void setProcNumCoresEnabled(int procNumCoresEnabled) {
            this.procNumCoresEnabled = procNumCoresEnabled;
        }

        public int getProcNumCores() {
            return procNumCores;
        }

        public void setProcNumCores(int procNumCores) {
            this.procNumCores = procNumCores;
        }

        public int getProcNumThreads() {
            return procNumThreads;
        }

        public void setProcNumThreads(int procNumThreads) {
            this.procNumThreads = procNumThreads;
        }

        public String getProcMemTechnology() {
            return procMemTechnology;
        }

        public void setProcMemTechnology(String procMemTechnology) {
            this.procMemTechnology = procMemTechnology;
        }

        public int getProcNumL1cache() {
            return procNumL1cache;
        }

        public void setProcNumL1cache(int procNumL1cache) {
            this.procNumL1cache = procNumL1cache;
        }

        public int getProcNumL2cache() {
            return procNumL2cache;
        }

        public void setProcNumL2cache(int procNumL2cache) {
            this.procNumL2cache = procNumL2cache;
        }

        public int getProcNumL3cache() {
            return procNumL3cache;
        }

        public void setProcNumL3cache(int procNumL3cache) {
            this.procNumL3cache = procNumL3cache;
        }
    }
}
