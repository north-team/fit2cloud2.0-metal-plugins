package com.fit2cloud.plugin.huawei.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HuaweiNicDTO {
    @SerializedName("BusinessPort")
    private List<BusinessPortBean> BusinessPort;
    @SerializedName("NetCard")
    private List<NetCardBean> NetCard;

    public List<BusinessPortBean> getBusinessPort() {
        return BusinessPort;
    }

    public void setBusinessPort(List<BusinessPortBean> BusinessPort) {
        this.BusinessPort = BusinessPort;
    }

    public List<NetCardBean> getNetCard() {
        return NetCard;
    }

    public void setNetCard(List<NetCardBean> NetCard) {
        this.NetCard = NetCard;
    }

    public static class BusinessPortBean {
        /**
         * RefNetCard : LomCard-3
         * NetDevFuncType : 1
         * MacAddr : C8:A7:76:E8:4C:71
         * CardType : 255
         * OSEthName : N/A
         * IPv6Info : [""]
         * IPv4Info : [""]
         * obj_name : Port0-3
         */

        @SerializedName("RefNetCard")
        private String RefNetCard;
        @SerializedName("NetDevFuncType")
        private int NetDevFuncType;
        @SerializedName("MacAddr")
        private String MacAddr;
        @SerializedName("CardType")
        private int CardType;
        @SerializedName("OSEthName")
        private String OSEthName;
        @SerializedName("obj_name")
        private String objName;
        @SerializedName("IPv6Info")
        private List<String> IPv6Info;
        @SerializedName("IPv4Info")
        private List<String> IPv4Info;

        public String getRefNetCard() {
            return RefNetCard;
        }

        public void setRefNetCard(String RefNetCard) {
            this.RefNetCard = RefNetCard;
        }

        public int getNetDevFuncType() {
            return NetDevFuncType;
        }

        public void setNetDevFuncType(int NetDevFuncType) {
            this.NetDevFuncType = NetDevFuncType;
        }

        public String getMacAddr() {
            return MacAddr;
        }

        public void setMacAddr(String MacAddr) {
            this.MacAddr = MacAddr;
        }

        public int getCardType() {
            return CardType;
        }

        public void setCardType(int CardType) {
            this.CardType = CardType;
        }

        public String getOSEthName() {
            return OSEthName;
        }

        public void setOSEthName(String OSEthName) {
            this.OSEthName = OSEthName;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }

        public List<String> getIPv6Info() {
            return IPv6Info;
        }

        public void setIPv6Info(List<String> IPv6Info) {
            this.IPv6Info = IPv6Info;
        }

        public List<String> getIPv4Info() {
            return IPv4Info;
        }

        public void setIPv4Info(List<String> IPv4Info) {
            this.IPv4Info = IPv4Info;
        }
    }

    public static class NetCardBean {
        /**
         * ProductName : NIC 1 (TM210)
         * VirtualNetCardFlag : 0
         * obj_name : LomCard-3
         */

        @SerializedName("ProductName")
        private String ProductName;
        @SerializedName("VirtualNetCardFlag")
        private int VirtualNetCardFlag;
        @SerializedName("obj_name")
        private String objName;

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public int getVirtualNetCardFlag() {
            return VirtualNetCardFlag;
        }

        public void setVirtualNetCardFlag(int VirtualNetCardFlag) {
            this.VirtualNetCardFlag = VirtualNetCardFlag;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }
    }
}
