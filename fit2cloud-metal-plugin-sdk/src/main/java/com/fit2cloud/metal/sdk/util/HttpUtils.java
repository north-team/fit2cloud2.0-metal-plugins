package com.fit2cloud.metal.sdk.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String post(String url, String payload) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            HttpEntity httpEntity = new StringEntity(payload, ContentType.APPLICATION_JSON);
            httpPost.setEntity(httpEntity);
            return EntityUtils.toString(httpClient.execute(httpPost).getEntity());
        } catch (Exception e) {
            throw new RuntimeException("url请求失败：url" + url);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("HttpClient链接关闭失败！");
            }
        }
    }
}
