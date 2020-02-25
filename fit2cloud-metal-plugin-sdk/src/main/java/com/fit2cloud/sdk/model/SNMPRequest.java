package com.fit2cloud.sdk.model;

public class SNMPRequest extends IPMIRequest {

    private String community;
    private int port;

    public SNMPRequest(String userName, String password, String ip) {
        super(userName, password, ip);
    }

    public SNMPRequest(String userName, String password, String ip, String community, int port) {
        super(userName, password, ip);
        this.community = community;
        this.port = port;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
