
package com.fit2cloud.hp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HpPhyNicDTO {

    /**
     * hostpwr_state : OFF
     * in_post : 0
     * ams_ready : AMS_UNAVAILABLE
     * data_state : DATA_CACHED_POWER_ON
     * phy_nics : [{"name":"iLO 4","location":"Embedded","firmware":"N/A","status":"OP_STATUS_OK","ports":[{"port_name":"DEDICATED","port_num":1,"mac_addr":"3c:a8:2a:18:8f:40","ip_addr":"192.168.1.105","status":"OP_STATUS_OK","team":"N/A","duplex_state":"Unknown","speed_mbps":null,"good_transmits":"N/A","good_receives":"N/A","bad_transmits":"N/A","bad_receives":"N/A"}]},{"name":"HPE Ethernet 1Gb 4-port 331FLR Adapter - NIC","location":"Embedded","firmware":"20.14.54","status":"OP_STATUS_OK","ports":[{"port_name":"","port_num":1,"mac_addr":"3c:a8:2a:0a:5c:30","ip_addr":"N/A","status":"OP_STATUS_OK","team":"N/A","duplex_state":"Unknown","speed_mbps":null,"good_transmits":"28402","good_receives":"45842","bad_transmits":"0","bad_receives":"0"},{"port_name":"","port_num":2,"mac_addr":"3c:a8:2a:0a:5c:31","ip_addr":"N/A","status":"OP_STATUS_UNKNOWN","team":"N/A","duplex_state":"Unknown","speed_mbps":null,"good_transmits":"0","good_receives":"0","bad_transmits":"0","bad_receives":"0"},{"port_name":"","port_num":3,"mac_addr":"3c:a8:2a:0a:5c:32","ip_addr":"N/A","status":"OP_STATUS_UNKNOWN","team":"N/A","duplex_state":"Unknown","speed_mbps":null,"good_transmits":"0","good_receives":"0","bad_transmits":"0","bad_receives":"0"},{"port_name":"","port_num":4,"mac_addr":"3c:a8:2a:0a:5c:33","ip_addr":"N/A","status":"OP_STATUS_UNKNOWN","team":"N/A","duplex_state":"Unknown","speed_mbps":null,"good_transmits":"0","good_receives":"0","bad_transmits":"0","bad_receives":"0"}]}]
     */

    @SerializedName("hostpwr_state")
    private String hostpwrState;
    @SerializedName("in_post")
    private int inPost;
    @SerializedName("ams_ready")
    private String amsReady;
    @SerializedName("data_state")
    private String dataState;
    @SerializedName("phy_nics")
    private List<PhyNicsBean> phyNics;

    public String getHostpwrState() {
        return hostpwrState;
    }

    public void setHostpwrState(String hostpwrState) {
        this.hostpwrState = hostpwrState;
    }

    public int getInPost() {
        return inPost;
    }

    public void setInPost(int inPost) {
        this.inPost = inPost;
    }

    public String getAmsReady() {
        return amsReady;
    }

    public void setAmsReady(String amsReady) {
        this.amsReady = amsReady;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public List<PhyNicsBean> getPhyNics() {
        return phyNics;
    }

    public void setPhyNics(List<PhyNicsBean> phyNics) {
        this.phyNics = phyNics;
    }

    public static class PhyNicsBean {
        /**
         * name : iLO 4
         * location : Embedded
         * firmware : N/A
         * status : OP_STATUS_OK
         * ports : [{"port_name":"DEDICATED","port_num":1,"mac_addr":"3c:a8:2a:18:8f:40","ip_addr":"192.168.1.105","status":"OP_STATUS_OK","team":"N/A","duplex_state":"Unknown","speed_mbps":null,"good_transmits":"N/A","good_receives":"N/A","bad_transmits":"N/A","bad_receives":"N/A"}]
         */

        @SerializedName("name")
        private String name;
        @SerializedName("location")
        private String location;
        @SerializedName("firmware")
        private String firmware;
        @SerializedName("status")
        private String status;
        @SerializedName("ports")
        private List<PortsBean> ports;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getFirmware() {
            return firmware;
        }

        public void setFirmware(String firmware) {
            this.firmware = firmware;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<PortsBean> getPorts() {
            return ports;
        }

        public void setPorts(List<PortsBean> ports) {
            this.ports = ports;
        }

        public static class PortsBean {
            /**
             * port_name : DEDICATED
             * port_num : 1
             * mac_addr : 3c:a8:2a:18:8f:40
             * ip_addr : 192.168.1.105
             * status : OP_STATUS_OK
             * team : N/A
             * duplex_state : Unknown
             * speed_mbps : null
             * good_transmits : N/A
             * good_receives : N/A
             * bad_transmits : N/A
             * bad_receives : N/A
             */

            @SerializedName("port_name")
            private String portName;
            @SerializedName("port_num")
            private int portNum;
            @SerializedName("mac_addr")
            private String macAddr;
            @SerializedName("ip_addr")
            private String ipAddr;
            @SerializedName("status")
            private String status;
            @SerializedName("team")
            private String team;
            @SerializedName("duplex_state")
            private String duplexState;
            @SerializedName("speed_mbps")
            private Object speedMbps;
            @SerializedName("good_transmits")
            private String goodTransmits;
            @SerializedName("good_receives")
            private String goodReceives;
            @SerializedName("bad_transmits")
            private String badTransmits;
            @SerializedName("bad_receives")
            private String badReceives;

            public String getPortName() {
                return portName;
            }

            public void setPortName(String portName) {
                this.portName = portName;
            }

            public int getPortNum() {
                return portNum;
            }

            public void setPortNum(int portNum) {
                this.portNum = portNum;
            }

            public String getMacAddr() {
                return macAddr;
            }

            public void setMacAddr(String macAddr) {
                this.macAddr = macAddr;
            }

            public String getIpAddr() {
                return ipAddr;
            }

            public void setIpAddr(String ipAddr) {
                this.ipAddr = ipAddr;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTeam() {
                return team;
            }

            public void setTeam(String team) {
                this.team = team;
            }

            public String getDuplexState() {
                return duplexState;
            }

            public void setDuplexState(String duplexState) {
                this.duplexState = duplexState;
            }

            public Object getSpeedMbps() {
                return speedMbps;
            }

            public void setSpeedMbps(Object speedMbps) {
                this.speedMbps = speedMbps;
            }

            public String getGoodTransmits() {
                return goodTransmits;
            }

            public void setGoodTransmits(String goodTransmits) {
                this.goodTransmits = goodTransmits;
            }

            public String getGoodReceives() {
                return goodReceives;
            }

            public void setGoodReceives(String goodReceives) {
                this.goodReceives = goodReceives;
            }

            public String getBadTransmits() {
                return badTransmits;
            }

            public void setBadTransmits(String badTransmits) {
                this.badTransmits = badTransmits;
            }

            public String getBadReceives() {
                return badReceives;
            }

            public void setBadReceives(String badReceives) {
                this.badReceives = badReceives;
            }
        }
    }
}
