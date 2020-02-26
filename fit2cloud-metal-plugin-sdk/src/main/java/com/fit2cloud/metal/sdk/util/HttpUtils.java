package com.fit2cloud.metal.sdk.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String post(String url, String payload) {
        return post(url, payload, null);
    }

    public static String post(String url, Map<String, String> header) {
        return post(url, null, header);
    }

    public static String post(String url, String payload, Map<String, String> header) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if (payload != null) {
                HttpEntity httpEntity = new StringEntity(payload, ContentType.APPLICATION_JSON);
                httpPost.setEntity(httpEntity);
            }
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

    public static String get(String url, Map<String, String> header) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Content-Type", "application/json");
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }

            return EntityUtils.toString(httpClient.execute(httpGet).getEntity());
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

    public static void main(String[] args) {
        System.out.println(post("http://149.129.105.194:4000/rpc/WEBSES/create.asp", "WEBVAR_USERNAME=admin&WEBVAR_PASSWORD=Fit2cloud@2019d"));
    }
}
