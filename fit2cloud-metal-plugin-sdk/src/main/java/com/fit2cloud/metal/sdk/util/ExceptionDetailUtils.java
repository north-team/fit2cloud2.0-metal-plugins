package com.fit2cloud.metal.sdk.util;

import java.util.Arrays;


public class ExceptionDetailUtils {
    /**
     * 打印详细的堆栈异常
     *
     * @param e
     * @return
     */
    public static String getStackTrace(Throwable e) {
        if (e == null) {
            return "没有异常信息！";
        }
        StackTraceElement[] elements = e.getStackTrace();
        StringBuffer sb = new StringBuffer();
        Arrays.stream(elements).forEach(el -> {
            sb.append(String.format("\nFileName:%s", el.getFileName()));
            sb.append(String.format("\nClasssName:%s", el.getClassName()));
            sb.append(String.format("\nMethodName:%s", el.getMethodName()));
            sb.append(String.format("\nLineNumber:%s", el.getLineNumber()));
        });
        return sb.toString();
    }

}
