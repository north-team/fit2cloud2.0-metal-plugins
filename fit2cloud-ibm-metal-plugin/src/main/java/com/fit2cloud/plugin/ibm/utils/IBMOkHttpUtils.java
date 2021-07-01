package com.fit2cloud.plugin.ibm.utils;

import com.fit2cloud.metal.sdk.util.ExceptionUtils;
import com.fit2cloud.metal.sdk.util.LogUtil;
import com.fit2cloud.plugin.ibm.IBMMetalProvider;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class IBMOkHttpUtils {
    private static Logger logger = LoggerFactory.getLogger(IBMOkHttpUtils.class);
    private static OkHttpClient okHttpClient;
    private static SSLSocketFactory sslSocketFactory = createSSLFactory();

    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (IBMOkHttpUtils.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient().newBuilder().sslSocketFactory(sslSocketFactory).hostnameVerifier(new OkHttpClientFactory.TrustAllHostnames()).build();
                }
            }
        }
        return okHttpClient;
    }

    public static String getHttps(String url, Map<String, String> headers) throws IOException {
        Request request = null;
        if (headers == null) {
            request = new Request.Builder().url(url).get().build();
        } else {
            request = new Request.Builder().url(url).get().headers(Headers.of(headers)).build();
        }
        return getResponse(request);
    }

    public static String getHttp(String url, Map<String, String> headers) throws IOException {
        Request request = null;
        if (headers == null) {
            request = new Request.Builder().url(url).get().build();
        } else {
            request = new Request.Builder().url(url).get().headers(Headers.of(headers)).build();
        }
        return getHttpResponse(request);
    }

    private static String getResponse(Request request) {
        Call call = getOkHttpClient().newCall(request);
        Response response = null;
        try {
            response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    private static String getHttpResponse(Request request) {
        Call call = new OkHttpClient().newCall(request);
        Response response = null;
        try {
            response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    public static Response postResponse(String url, String bodyStr) {
        Request request = null;
        Call call = null;
        try {
            String finalUrl = url.replace("https://", "").replace("/data/login", "");
            if (IBMMetalProvider.httpIpList.stream().filter(u -> finalUrl.equalsIgnoreCase(u)).count() > 0) {
                url = url.replace("https", "http");
                RequestBody formBody = FormBody.create(MediaType.parse("application/x-www-form-urlencoded"), bodyStr);
                request = new Request.Builder().url(url).post(formBody).build();
                call = new OkHttpClient().newBuilder().build().newCall(request);
                return call.execute();
            }
            RequestBody formBody = FormBody.create(MediaType.parse("application/x-www-form-urlencoded"), bodyStr);
            request = new Request.Builder().url(url).post(formBody).build();
            call = new OkHttpClient().newBuilder().sslSocketFactory(sslSocketFactory).hostnameVerifier(new OkHttpClientFactory.TrustAllHostnames()).build().newCall(request);
            return call.execute();
        } catch (Exception e) {
            LogUtil.error(String.format("IBMOkHttpUtils 请求失败:%s, e:%s", url, ExceptionUtils.getExceptionDetail(e)));
        }
        return null;
    }

    private static SSLSocketFactory createSSLFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            sslSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {

        }

        return sslSocketFactory;
    }

    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
