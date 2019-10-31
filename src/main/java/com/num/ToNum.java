package com.num;

import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-10-24 10:25
 */
public class ToNum {

    /**
     * 字符串提取数值
     *
     * @param str
     * @return
     */
    public static double toNum(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        char[] bytes = str.toCharArray();
        String data = "";
        for (int i = 0; i < bytes.length; i++) {
            if ((".0123456789").indexOf(bytes[i] + "") != -1) {
                data += bytes[i];
            }
        }

        if (StringUtils.isBlank(data)) {
            return 0;
        }

        return Double.parseDouble(data);
    }

    public static void main(String[] args) {
        String premiumStr = "645.45元起";
        double premium = toNum(premiumStr);
        DecimalFormat df = new DecimalFormat("#.00");
        String totalPremium = df.format(premium) + "元";
        System.out.println(totalPremium);
    }


}
