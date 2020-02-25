package com.fit2cloud.sdk.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class IPMIUtil {

    public static String exeCommand(Account account, String... commmands) throws Exception {
        if (StringUtils.isAnyBlank(account.getHost(), account.getUserName(), account.getPwd()) || StringUtils.isAnyBlank(commmands)) {
            throw new RuntimeException("运行ipmitool命令失败！参数非法！");
        }
        String command = String.format("ipmitool -I lanplus -H %s -U %s -P %s", account.getHost(), account.getUserName(), account.getPwd()) + " " + String.join(" ", commmands);
        Process p = Runtime.getRuntime().exec(command);
        InputStreamReader re = new InputStreamReader(p.getInputStream(), "utf-8");

        BufferedReader b = new BufferedReader(re);
        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = b.readLine()) != null && StringUtils.isNotBlank(line)) {
            sb.append(line + "\n");
        }
        p.getInputStream().close();
        re.close();
        b.close();
        //特殊的排除设置IP地址的获取方式，这个命令即使成功了 也不会有任何返回
        if (sb.length() == 0) {
            throw new RuntimeException("物理机：" + account.getHost() + "带外连接失败！");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Account account = new Account("47.107.47.38", "admin", "Calong@2015");
        try {
//            System.out.println(exeCommand(account, "fru"));
            System.out.println(exeCommand(account, "power on"));
            System.out.println(exeCommand(account, "power reset"));
//            String bmcResult = IPMIUtil.exeCommand(account, "lan print 1");
//            JSONObject re = new JSONObject();
//            Arrays.asList(exeCommand(account, "lan print 1").split("\n")).forEach(r -> {
//                int index = r.indexOf(":");
//                if (index == -1) {
//                    return;
//                }
//                if (r.substring(0, index + 1).replace(":", "").trim().length() == 0) {
//                    return;
//                }
//                re.put(r.substring(0, index + 1).replace(":", "").trim(), r.substring(index + 1, r.length()).trim());
//            });
//
//            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JSONObject transform(String commandResult) {
        JSONObject re = new JSONObject();
        Arrays.asList(commandResult.split("\n")).forEach(r -> {
            int index = r.indexOf(":");
            if (index == -1) {
                return;
            }
            if (r.substring(0, index + 1).replace(":", "").trim().length() == 0) {
                return;
            }
            re.put(r.substring(0, index + 1).replace(":", "").trim(), r.substring(index + 1, r.length()).trim());
        });
        return re;
    }

    public static class Account {
        String host;
        String userName;
        String pwd;
        String newIp;
        String newPwd;

        public Account(String host, String userName, String pwd) {
            this.host = host;
            this.userName = userName;
            this.pwd = pwd;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getNewPwd() {
            return newPwd;
        }

        public void setNewPwd(String newPwd) {
            this.newPwd = newPwd;
        }

        public String getNewIp() {
            return newIp;
        }

        public void setNewIp(String newIp) {
            this.newIp = newIp;
        }
    }

}
