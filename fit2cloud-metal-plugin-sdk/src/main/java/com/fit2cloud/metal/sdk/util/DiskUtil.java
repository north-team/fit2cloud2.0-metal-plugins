/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.fit2cloud.metal.sdk.util;

import org.apache.commons.lang3.StringUtils;

public class DiskUtil {

    public static String getStandSize(String originSize) {
        if (StringUtils.isBlank(originSize)) {
            return "";
        }

        String unit = originSize.contains("GB") ? "GB" : originSize.contains("TB") ? "TB" : "MB";
        double d = 0;
        originSize = originSize.replace("GB", "").replace("TB", "").replace("MB", "").replace(" ", "");
        d = Double.parseDouble(originSize);
        int standardValue = (int) (d / 0.931);
        if (standardValue % 10 != 0) {
            standardValue += 10 - standardValue % 10;
        }
        if (standardValue % 100 != 0) {
            standardValue -= standardValue % 100;
        }

        return standardValue + " " + unit;
    }

    public static void main(String[] args) {
        System.out.println(getStandSize("465 GB"));
    }
}
