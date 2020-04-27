
package com.fit2cloud.hp.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class HpMemDTO {


    /**
     * hostpwr_state : OFF
     * mem_type_configured : MEM_ADVANCED_ECC
     * mem_type_active : MEM_ADVANCED_ECC
     * mem_type_available : [{"available_type":"MEM_ADVANCED_ECC"},{"available_type":"MEM_RANK_SPARE"},{"available_type":"MEM_MIRROR_INTRA"}]
     * mem_status : MEM_ADVANCED_ECC
     * mem_condition : OP_STATUS_OK
     * mem_hot_plug : MEM_UNKNOWN
     * mem_op_speed : 1866
     * mem_os_mem_size : 0
     * mem_total_mem_size : 32768
     * mem_riv_state : MEM_UNKNOWN
     * mem_data_stale : 1
     * mem_boards : [{"brd_idx":0,"brd_slot_num":0,"brd_cpu_num":1,"brd_riser_num":0,"brd_online_status":"MEM_OTHER","brd_error_status":"MEM_OTHER","brd_locked":"MEM_OTHER","brd_num_of_sockets":12,"brd_os_mem_size":0,"brd_total_mem_size":16384,"brd_condition":"OP_STATUS_UNKNOWN","brd_hot_plug":"MEM_OTHER","brd_oper_freq":1866,"brd_oper_volt":1200},{"brd_idx":1,"brd_slot_num":1,"brd_cpu_num":2,"brd_riser_num":0,"brd_online_status":"MEM_OTHER","brd_error_status":"MEM_OTHER","brd_locked":"MEM_OTHER","brd_num_of_sockets":12,"brd_os_mem_size":0,"brd_total_mem_size":16384,"brd_condition":"OP_STATUS_UNKNOWN","brd_hot_plug":"MEM_OTHER","brd_oper_freq":1866,"brd_oper_volt":1200}]
     * mem_modules : [{"mem_mod_idx":0,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":1,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":1,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":2,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":2,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":3,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":3,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":4,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":4,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":5,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":5,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":6,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":6,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":7,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":7,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":8,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":8,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":9,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":9,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":10,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":10,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":11,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":11,"mem_brd_num":0,"mem_cpu_num":1,"mem_riser_num":0,"mem_mod_num":12,"mem_mod_size":16384,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_RDIMM","mem_mod_frequency":2133,"mem_mod_status":"MEM_GOOD_IN_USE","mem_mod_condition":"MEM_OK","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":1200,"mem_mod_ranks":2},{"mem_mod_idx":12,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":1,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":13,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":2,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":14,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":3,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":15,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":4,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":16,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":5,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":17,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":6,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":18,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":7,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":19,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":8,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":20,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":9,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":21,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":10,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":22,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":11,"mem_mod_size":0,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_OTHER","mem_mod_frequency":0,"mem_mod_status":"MEM_NOT_PRESENT","mem_mod_condition":"MEM_OTHER","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":0,"mem_mod_ranks":0},{"mem_mod_idx":23,"mem_brd_num":0,"mem_cpu_num":2,"mem_riser_num":0,"mem_mod_num":12,"mem_mod_size":16384,"mem_mod_type":"MEM_DIMM_DDR4","mem_mod_tech":"MEM_RDIMM","mem_mod_frequency":2133,"mem_mod_status":"MEM_GOOD_IN_USE","mem_mod_condition":"MEM_OK","mem_mod_smartmem":"MEM_NO","mem_mod_part_num":"NOT AVAILABLE","mem_mod_min_volt":1200,"mem_mod_ranks":2}]
     * memory : [{"mem_dev_loc":"PROC 1 DIMM 1","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 2","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 3","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 4","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 5","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 6","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 7","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 8","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 9","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 10","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 11","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 1 DIMM 12","mem_size":16384,"mem_speed":2133},{"mem_dev_loc":"PROC 2 DIMM 1","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 2","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 3","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 4","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 5","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 6","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 7","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 8","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 9","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 10","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 11","mem_size":0,"mem_speed":0},{"mem_dev_loc":"PROC 2 DIMM 12","mem_size":16384,"mem_speed":2133}]
     */

    @SerializedName("hostpwr_state")
    private String hostpwrState;
    @SerializedName("mem_type_configured")
    private String memTypeConfigured;
    @SerializedName("mem_type_active")
    private String memTypeActive;
    @SerializedName("mem_status")
    private String memStatus;
    @SerializedName("mem_condition")
    private String memCondition;
    @SerializedName("mem_hot_plug")
    private String memHotPlug;
    @SerializedName("mem_op_speed")
    private int memOpSpeed;
    @SerializedName("mem_os_mem_size")
    private int memOsMemSize;
    @SerializedName("mem_total_mem_size")
    private int memTotalMemSize;
    @SerializedName("mem_riv_state")
    private String memRivState;
    @SerializedName("mem_data_stale")
    private int memDataStale;
    @SerializedName("mem_type_available")
    private List<MemTypeAvailableBean> memTypeAvailable;
    @SerializedName("mem_boards")
    private List<MemBoardsBean> memBoards;
    @SerializedName("mem_modules")
    private List<MemModulesBean> memModules;
    @SerializedName("memory")
    private List<MemoryBean> memory;

    public String getHostpwrState() {
        return hostpwrState;
    }

    public void setHostpwrState(String hostpwrState) {
        this.hostpwrState = hostpwrState;
    }

    public String getMemTypeConfigured() {
        return memTypeConfigured;
    }

    public void setMemTypeConfigured(String memTypeConfigured) {
        this.memTypeConfigured = memTypeConfigured;
    }

    public String getMemTypeActive() {
        return memTypeActive;
    }

    public void setMemTypeActive(String memTypeActive) {
        this.memTypeActive = memTypeActive;
    }

    public String getMemStatus() {
        return memStatus;
    }

    public void setMemStatus(String memStatus) {
        this.memStatus = memStatus;
    }

    public String getMemCondition() {
        return memCondition;
    }

    public void setMemCondition(String memCondition) {
        this.memCondition = memCondition;
    }

    public String getMemHotPlug() {
        return memHotPlug;
    }

    public void setMemHotPlug(String memHotPlug) {
        this.memHotPlug = memHotPlug;
    }

    public int getMemOpSpeed() {
        return memOpSpeed;
    }

    public void setMemOpSpeed(int memOpSpeed) {
        this.memOpSpeed = memOpSpeed;
    }

    public int getMemOsMemSize() {
        return memOsMemSize;
    }

    public void setMemOsMemSize(int memOsMemSize) {
        this.memOsMemSize = memOsMemSize;
    }

    public int getMemTotalMemSize() {
        return memTotalMemSize;
    }

    public void setMemTotalMemSize(int memTotalMemSize) {
        this.memTotalMemSize = memTotalMemSize;
    }

    public String getMemRivState() {
        return memRivState;
    }

    public void setMemRivState(String memRivState) {
        this.memRivState = memRivState;
    }

    public int getMemDataStale() {
        return memDataStale;
    }

    public void setMemDataStale(int memDataStale) {
        this.memDataStale = memDataStale;
    }

    public List<MemTypeAvailableBean> getMemTypeAvailable() {
        return memTypeAvailable;
    }

    public void setMemTypeAvailable(List<MemTypeAvailableBean> memTypeAvailable) {
        this.memTypeAvailable = memTypeAvailable;
    }

    public List<MemBoardsBean> getMemBoards() {
        return memBoards;
    }

    public void setMemBoards(List<MemBoardsBean> memBoards) {
        this.memBoards = memBoards;
    }

    public List<MemModulesBean> getMemModules() {
        return memModules;
    }

    public void setMemModules(List<MemModulesBean> memModules) {
        this.memModules = memModules;
    }

    public List<MemoryBean> getMemory() {
        return memory;
    }

    public void setMemory(List<MemoryBean> memory) {
        this.memory = memory;
    }

    public static class MemTypeAvailableBean {
        /**
         * available_type : MEM_ADVANCED_ECC
         */

        @SerializedName("available_type")
        private String availableType;

        public String getAvailableType() {
            return availableType;
        }

        public void setAvailableType(String availableType) {
            this.availableType = availableType;
        }
    }

    public static class MemBoardsBean {
        /**
         * brd_idx : 0
         * brd_slot_num : 0
         * brd_cpu_num : 1
         * brd_riser_num : 0
         * brd_online_status : MEM_OTHER
         * brd_error_status : MEM_OTHER
         * brd_locked : MEM_OTHER
         * brd_num_of_sockets : 12
         * brd_os_mem_size : 0
         * brd_total_mem_size : 16384
         * brd_condition : OP_STATUS_UNKNOWN
         * brd_hot_plug : MEM_OTHER
         * brd_oper_freq : 1866
         * brd_oper_volt : 1200
         */

        @SerializedName("brd_idx")
        private int brdIdx;
        @SerializedName("brd_slot_num")
        private int brdSlotNum;
        @SerializedName("brd_cpu_num")
        private int brdCpuNum;
        @SerializedName("brd_riser_num")
        private int brdRiserNum;
        @SerializedName("brd_online_status")
        private String brdOnlineStatus;
        @SerializedName("brd_error_status")
        private String brdErrorStatus;
        @SerializedName("brd_locked")
        private String brdLocked;
        @SerializedName("brd_num_of_sockets")
        private int brdNumOfSockets;
        @SerializedName("brd_os_mem_size")
        private int brdOsMemSize;
        @SerializedName("brd_total_mem_size")
        private int brdTotalMemSize;
        @SerializedName("brd_condition")
        private String brdCondition;
        @SerializedName("brd_hot_plug")
        private String brdHotPlug;
        @SerializedName("brd_oper_freq")
        private int brdOperFreq;
        @SerializedName("brd_oper_volt")
        private int brdOperVolt;

        public int getBrdIdx() {
            return brdIdx;
        }

        public void setBrdIdx(int brdIdx) {
            this.brdIdx = brdIdx;
        }

        public int getBrdSlotNum() {
            return brdSlotNum;
        }

        public void setBrdSlotNum(int brdSlotNum) {
            this.brdSlotNum = brdSlotNum;
        }

        public int getBrdCpuNum() {
            return brdCpuNum;
        }

        public void setBrdCpuNum(int brdCpuNum) {
            this.brdCpuNum = brdCpuNum;
        }

        public int getBrdRiserNum() {
            return brdRiserNum;
        }

        public void setBrdRiserNum(int brdRiserNum) {
            this.brdRiserNum = brdRiserNum;
        }

        public String getBrdOnlineStatus() {
            return brdOnlineStatus;
        }

        public void setBrdOnlineStatus(String brdOnlineStatus) {
            this.brdOnlineStatus = brdOnlineStatus;
        }

        public String getBrdErrorStatus() {
            return brdErrorStatus;
        }

        public void setBrdErrorStatus(String brdErrorStatus) {
            this.brdErrorStatus = brdErrorStatus;
        }

        public String getBrdLocked() {
            return brdLocked;
        }

        public void setBrdLocked(String brdLocked) {
            this.brdLocked = brdLocked;
        }

        public int getBrdNumOfSockets() {
            return brdNumOfSockets;
        }

        public void setBrdNumOfSockets(int brdNumOfSockets) {
            this.brdNumOfSockets = brdNumOfSockets;
        }

        public int getBrdOsMemSize() {
            return brdOsMemSize;
        }

        public void setBrdOsMemSize(int brdOsMemSize) {
            this.brdOsMemSize = brdOsMemSize;
        }

        public int getBrdTotalMemSize() {
            return brdTotalMemSize;
        }

        public void setBrdTotalMemSize(int brdTotalMemSize) {
            this.brdTotalMemSize = brdTotalMemSize;
        }

        public String getBrdCondition() {
            return brdCondition;
        }

        public void setBrdCondition(String brdCondition) {
            this.brdCondition = brdCondition;
        }

        public String getBrdHotPlug() {
            return brdHotPlug;
        }

        public void setBrdHotPlug(String brdHotPlug) {
            this.brdHotPlug = brdHotPlug;
        }

        public int getBrdOperFreq() {
            return brdOperFreq;
        }

        public void setBrdOperFreq(int brdOperFreq) {
            this.brdOperFreq = brdOperFreq;
        }

        public int getBrdOperVolt() {
            return brdOperVolt;
        }

        public void setBrdOperVolt(int brdOperVolt) {
            this.brdOperVolt = brdOperVolt;
        }
    }

    public static class MemModulesBean {
        /**
         * mem_mod_idx : 0
         * mem_brd_num : 0
         * mem_cpu_num : 1
         * mem_riser_num : 0
         * mem_mod_num : 1
         * mem_mod_size : 0
         * mem_mod_type : MEM_DIMM_DDR4
         * mem_mod_tech : MEM_OTHER
         * mem_mod_frequency : 0
         * mem_mod_status : MEM_NOT_PRESENT
         * mem_mod_condition : MEM_OTHER
         * mem_mod_smartmem : MEM_NO
         * mem_mod_part_num : NOT AVAILABLE
         * mem_mod_min_volt : 0
         * mem_mod_ranks : 0
         */

        @SerializedName("mem_mod_idx")
        private int memModIdx;
        @SerializedName("mem_brd_num")
        private int memBrdNum;
        @SerializedName("mem_cpu_num")
        private int memCpuNum;
        @SerializedName("mem_riser_num")
        private int memRiserNum;
        @SerializedName("mem_mod_num")
        private int memModNum;
        @SerializedName("mem_mod_size")
        private int memModSize;
        @SerializedName("mem_mod_type")
        private String memModType;
        @SerializedName("mem_mod_tech")
        private String memModTech;
        @SerializedName("mem_mod_frequency")
        private int memModFrequency;
        @SerializedName("mem_mod_status")
        private String memModStatus;
        @SerializedName("mem_mod_condition")
        private String memModCondition;
        @SerializedName("mem_mod_smartmem")
        private String memModSmartmem;
        @SerializedName("mem_mod_part_num")
        private String memModPartNum;
        @SerializedName("mem_mod_min_volt")
        private int memModMinVolt;
        @SerializedName("mem_mod_ranks")
        private int memModRanks;

        public int getMemModIdx() {
            return memModIdx;
        }

        public void setMemModIdx(int memModIdx) {
            this.memModIdx = memModIdx;
        }

        public int getMemBrdNum() {
            return memBrdNum;
        }

        public void setMemBrdNum(int memBrdNum) {
            this.memBrdNum = memBrdNum;
        }

        public int getMemCpuNum() {
            return memCpuNum;
        }

        public void setMemCpuNum(int memCpuNum) {
            this.memCpuNum = memCpuNum;
        }

        public int getMemRiserNum() {
            return memRiserNum;
        }

        public void setMemRiserNum(int memRiserNum) {
            this.memRiserNum = memRiserNum;
        }

        public int getMemModNum() {
            return memModNum;
        }

        public void setMemModNum(int memModNum) {
            this.memModNum = memModNum;
        }

        public int getMemModSize() {
            return memModSize;
        }

        public void setMemModSize(int memModSize) {
            this.memModSize = memModSize;
        }

        public String getMemModType() {
            return memModType;
        }

        public void setMemModType(String memModType) {
            this.memModType = memModType;
        }

        public String getMemModTech() {
            return memModTech;
        }

        public void setMemModTech(String memModTech) {
            this.memModTech = memModTech;
        }

        public int getMemModFrequency() {
            return memModFrequency;
        }

        public void setMemModFrequency(int memModFrequency) {
            this.memModFrequency = memModFrequency;
        }

        public String getMemModStatus() {
            return memModStatus;
        }

        public void setMemModStatus(String memModStatus) {
            this.memModStatus = memModStatus;
        }

        public String getMemModCondition() {
            return memModCondition;
        }

        public void setMemModCondition(String memModCondition) {
            this.memModCondition = memModCondition;
        }

        public String getMemModSmartmem() {
            return memModSmartmem;
        }

        public void setMemModSmartmem(String memModSmartmem) {
            this.memModSmartmem = memModSmartmem;
        }

        public String getMemModPartNum() {
            return memModPartNum;
        }

        public void setMemModPartNum(String memModPartNum) {
            this.memModPartNum = memModPartNum;
        }

        public int getMemModMinVolt() {
            return memModMinVolt;
        }

        public void setMemModMinVolt(int memModMinVolt) {
            this.memModMinVolt = memModMinVolt;
        }

        public int getMemModRanks() {
            return memModRanks;
        }

        public void setMemModRanks(int memModRanks) {
            this.memModRanks = memModRanks;
        }
    }

    public static class MemoryBean {
        /**
         * mem_dev_loc : PROC 1 DIMM 1
         * mem_size : 0
         * mem_speed : 0
         */

        @SerializedName("mem_dev_loc")
        private String memDevLoc;
        @SerializedName("mem_size")
        private int memSize;
        @SerializedName("mem_speed")
        private int memSpeed;

        public String getMemDevLoc() {
            return memDevLoc;
        }

        public void setMemDevLoc(String memDevLoc) {
            this.memDevLoc = memDevLoc;
        }

        public int getMemSize() {
            return memSize;
        }

        public void setMemSize(int memSize) {
            this.memSize = memSize;
        }

        public int getMemSpeed() {
            return memSpeed;
        }

        public void setMemSpeed(int memSpeed) {
            this.memSpeed = memSpeed;
        }
    }
}
