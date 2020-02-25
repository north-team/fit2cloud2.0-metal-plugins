package com.fit2cloud.metal.sdk.model;

public class IPMIRequest {
    private String userName;
    private String password;
    private String ip;

    public IPMIRequest(String userName, String password, String ip) {
        this.userName = userName;
        this.password = password;
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
