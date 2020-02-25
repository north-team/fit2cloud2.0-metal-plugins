package com.fit2cloud.metal.sdk.util;

import org.apache.commons.net.util.SubnetUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class IpUtil {
    private static final int TIME_OUT = 3000;

    public static List<String> getIpRange(String startIp, String endIp, String gateway, String mask) {
        List<String> result = new ArrayList<>();
        SubnetUtils utils = new SubnetUtils(gateway, mask);
        SubnetUtils.SubnetInfo info = utils.getInfo();
        String[] allIps = info.getAllAddresses();
        for (String allIp : allIps) {
            if (ipInRange(allIp, startIp, endIp)) {
                result.add(allIp);
            }
        }
        return result;
    }

    private static Boolean ipInRange(String ip, String startIp, String endIp) {
        String ipSection = startIp + "-" + endIp;
        ip = ip.trim();
        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
        if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP))
            return false;
        int idx = ipSection.indexOf('-');
        String[] sips = ipSection.substring(0, idx).split("\\.");
        String[] sipe = ipSection.substring(idx + 1).split("\\.");
        String[] sipt = ip.split("\\.");
        long ips = 0L, ipe = 0L, ipt = 0L;
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(sips[i]);
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
        }
        if (ips > ipe) {
            long t = ips;
            ips = ipe;
            ipe = t;
        }
        return ips <= ipt && ipt <= ipe;
    }

    static Pattern pattern = Pattern
            .compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]"
                    + "|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");

    public static boolean checkIP(String str) {
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) throws IOException {
//        Process p = Runtime.getRuntime().exec("pwd");

//        Process p = new ProcessBuilder("").start();
//        Process p = new ProcessBuilder("ipmitool -I lanplus -H 47.107.47.38 -U admin -P admin power status").start();
        Process p = Runtime.getRuntime().exec("ipmitool -I lanplus -H 47.107.47.38 -U admin -P admin power status");
        InputStreamReader re = new InputStreamReader(p.getInputStream(), "utf-8");

        BufferedReader b = new BufferedReader(re);
        String line = null;
        while ((line = b.readLine()) != null) {
            System.out.println(line);
        }
        p.getInputStream().close();
        re.close();
        b.close();
    }

}
