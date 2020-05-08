package com.fit2cloud.metal.sdk.model.request;

public class IPMIRequest extends BaseRequest {

    public IPMIRequest() {
    }

    public IPMIRequest(String host, String userName, String pwd) {
        super(host, userName, pwd);
    }
}
