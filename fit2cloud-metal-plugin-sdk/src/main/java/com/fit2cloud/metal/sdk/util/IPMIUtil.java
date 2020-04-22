package com.fit2cloud.metal.sdk.util;

import com.alibaba.fastjson.JSONObject;
import com.fit2cloud.metal.sdk.MetalPluginException;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IPMIUtil {
    private static ExecutorService executorService;
    private static final int waitTimes = 10;

    static {
        executorService = Executors.newFixedThreadPool(20);
    }

    public static String exeCommand(Account account, String commands) throws Exception {
        if (StringUtils.isAnyBlank(account.getHost(), account.getUserName(), account.getPwd()) || StringUtils.isAnyBlank(commands)) {
            throw new RuntimeException("运行ipmitool命令失败！参数非法！");
        }

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                List<String> cmds = new LinkedList<String>();
                cmds.add("ipmitool");
                cmds.add("-I");
                cmds.add("-lanplus");
                cmds.add("-H");
                cmds.add(account.getHost());
                cmds.add("-U");
                cmds.add(account.getUserName());
                cmds.add("-P");
                cmds.add(account.getPwd());
                for (String c : commands.split(" ")) {
                    cmds.add(c);
                }
                ProcessBuilder builder = new ProcessBuilder();
                builder.redirectErrorStream(true);
                Process p = builder.start();
                InputStreamReader re = new InputStreamReader(p.getInputStream(), "utf-8");
                BufferedReader b = new BufferedReader(re);
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = b.readLine()) != null) {
                    sb.append(line + "\n");
                }
                p.getInputStream().close();
                re.close();
                b.close();
                p.destroy();
                return sb.toString();
            }
        };

        Future<String> future = executorService.submit(callable);

        String r = future.get(waitTimes, TimeUnit.MINUTES);
        if (StringUtils.isBlank(r) && !commands.contains("set password") || r.contains("Error:") && !commands.equalsIgnoreCase("sdr")) {
            throw new MetalPluginException("物理机：" + account.getHost() + "带外连接失败！");
        }
        return r;
    }

    public static String exeCommandForUserIndex(String brand, Account account) throws Exception {
        if (StringUtils.isAnyBlank(account.getHost(), account.getUserName(), account.getPwd()) || StringUtils.isAnyBlank(brand)) {
            throw new RuntimeException("运行ipmitool命令失败！参数非法！");
        }
        String commands = "user list";
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                List<String> cmds = new LinkedList<String>();
                cmds.add("ipmitool");
                cmds.add("-I");
                cmds.add("-lanplus");
                cmds.add("-H");
                cmds.add(account.getHost());
                cmds.add("-U");
                cmds.add(account.getUserName());
                cmds.add("-P");
                cmds.add(account.getPwd());
                for (String c : commands.split(" ")) {
                    cmds.add(c);
                }
                ProcessBuilder builder = new ProcessBuilder();
                builder.redirectErrorStream(true);
                Process p = builder.start();
                InputStreamReader re = new InputStreamReader(p.getInputStream(), "utf-8");
                BufferedReader b = new BufferedReader(re);
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = b.readLine()) != null) {
                    sb.append(line + "\n");

                    if (line.contains(account.getUserName()) || (brand.equalsIgnoreCase("HP") && line.toLowerCase().contains(account.getUserName()))) {
                        p.getInputStream().close();
                        re.close();
                        b.close();
                        p.destroy();
                        return line.split(" ")[0];
                    }
                }
                p.getInputStream().close();
                re.close();
                b.close();
                p.destroy();
                return "";
            }
        };

        Future<String> future = executorService.submit(callable);
        String r = future.get(waitTimes, TimeUnit.MINUTES);
        if (StringUtils.isBlank(r)) {
            throw new MetalPluginException("物理机：" + account.getHost() + "带外连接失败！没有找到该ipmi账号");
        }
        return r;
    }

    public static String execCommandForBrand(Account account) throws Exception {
        String commandResult = IPMIUtil.exeCommand(account, "fru");
        JSONObject fruObj = IPMIUtil.transform(commandResult);
        return fruObj.getString("Product Manufacturer");
    }

    public static String grep(String string, String sign) throws Exception {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(sign);
        if (StringUtils.isAnyBlank(string, sign)) return null;
        Arrays.asList(Arrays.stream(string.split("\n")).filter(s -> p.matcher(s).find()).collect(Collectors.toList())).forEach(s -> sb.append(s + "\n"));
        return sb.toString();
    }

    static Pattern pNumber = Pattern.compile("\\d");

    public static int extractStringNumber(String s) {
        if (StringUtils.isBlank(s))
            return 0;
        Matcher m = pNumber.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            sb.append(m.group());
        }
        if (sb.toString().startsWith("0")) {
            sb.replace(0, 1, "");
        }
        return Integer.valueOf(sb.toString());
    }

    public static int ipmiComparator(String s1, String s2) {
        int length = s1.split(" ")[0].length() - s2.split(" ")[0].length();
        if (length < 0) {
            return -1;
        } else if (length > 0) {
            return 1;
        } else {
            length = extractStringNumber(s1) - extractStringNumber(s2);
            return length > 0 ? 1 : length == 0 ? 0 : -1;
        }
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
