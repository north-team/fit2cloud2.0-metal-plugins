package com.fit2cloud.metal.sdk.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static CookieStore cookieStore = new BasicCookieStore();

    public static String post(String url, String payload) {
        return post(url, payload, null);
    }

    public static String post(String url, Map<String, String> header) {
        return post(url, null, header);
    }

    private static CloseableHttpClient buildHttpClient(String url) {
        try {
            if (url.startsWith("https") || url.startsWith("HTTPS")) {
                // https 增加信任设置
                TrustStrategy trustStrategy = new TrustSelfSignedStrategy();
                SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(trustStrategy).build();
                HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
                return HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(hostnameVerifier).setDefaultCookieStore(cookieStore).build(); //后续可获取响应 cookie
            } else {
                // http
                return HttpClientBuilder.create().build();
            }
        } catch (Exception e) {
            throw new RuntimeException("HttpClient构建失败", e);
        }
    }

    public static String post(String url, String payload, Map<String, String> header) {
        CloseableHttpClient httpClient = buildHttpClient(url);
        try {
            HttpPost httpPost = new HttpPost(url);
            Boolean flag = false;
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                    if(entry.getKey().equalsIgnoreCase("Content-Type")){
                        flag = true;
                    }
                }
            }
            if(!flag){
                httpPost.addHeader("Content-Type", "application/json");
            }
            if (payload != null) {
                HttpEntity httpEntity = new StringEntity(payload, ContentType.APPLICATION_JSON);
                httpPost.setEntity(httpEntity);
            }
            // httpClient对象执行post请求,并返回响应参数对象
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity);
            httpResponse.close();

            return result;
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


    public static String post(String url, String payload, Map<String, String> header, ConcurrentHashMap<String, Map<String, String>> headersMap) {
        // 针对 Dell 插件请求
        CloseableHttpClient httpClient = buildHttpClient(url);
        try {
            HttpPost httpPost = new HttpPost(url);
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if (payload != null) {
                HttpEntity httpEntity = new StringEntity(payload, ContentType.APPLICATION_JSON);
                httpPost.setEntity(httpEntity);
            }
            // httpClient对象执行post请求,并返回响应参数对象
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            //获得Cookies
            String httpSessionCookie = null;
            List<Cookie> cookies = cookieStore.getCookies();
            if(null != cookies){
                for (int i = 0; i < cookies.size(); i++) {
                    if (cookies.get(i).getName().equals("-http-session-")) {
                        httpSessionCookie = cookies.get(i).getValue();
                        Map rstHeader = new HashMap<String, String>();
                        rstHeader.put("Cookie", String.format("-http-session-=%s", httpSessionCookie));
                        headersMap.put("login", rstHeader);
                    }
                }
            }
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity);
            httpResponse.close();

            return result;
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
        CloseableHttpClient httpClient = buildHttpClient(url);
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
//        System.out.println(post("http://149.129.105.194:4000/rpc/WEBSES/create.asp", "WEBVAR_USERNAME=admin&WEBVAR_PASSWORD=Fit2cloud@2019"));
        System.out.println(post("https://dl3809.dahaia123.top/json/login_session", "{\"method\":\"login\",\"user_login\":\"administrator\",\"password\":\"Fit2cloud@2019\"}"));
    }
}
