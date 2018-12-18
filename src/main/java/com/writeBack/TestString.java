package com.writeBack;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Base64;

/**
 * Created by Liuxd on 2018-10-31.
 */
public class TestString {


    public static void main(String[] args) throws Exception {
//        String str = "qingfengjian,yanyuedao";
        String str = "青锋剑，偃月刀";

        jdks(str);

        base64(str);

        String ss = new String(new byte[]{-23, -99, -110});
        System.out.println(ss);

        enCode(str);

    }

    /**
     * jdk
     */
    public static void jdks(String str) {
        //1、字符串转字符数组
        char[] chars = str.toCharArray();
        System.out.println("字符长度：" + chars.length);
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i] + "  ");
        }
        System.out.println();

        //2、字符数组转字符串
        String str2 = new String(chars);
        System.out.println("字符数组转字符串：" + str2);

        //3、字符串转字节数组
        byte[] bytes = str.getBytes();

        System.out.println("字节数组长度：" + bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i] + "  ");
        }
        System.out.println();

        //4、字节数组转字符串
        String result = new String(bytes);

        System.out.println("字节数组转字符串：" + result);

        System.out.println();
    }

    /**
     * Base 64
     *
     * @param str
     */
    public static void base64(String str) {
        byte[] bytes = str.getBytes();

        //Base64 加密
        String encoded = Base64.getEncoder().encodeToString(bytes);
        System.out.println("JDK Base 64 加密后：" + encoded);

        //Base64 解密
        byte[] decoded = Base64.getDecoder().decode(encoded);

        String decodeStr = new String(decoded);
        System.out.println("JDK Base 64 解密后：" + decodeStr);

        System.out.println();


    }

    /**
     * BASE64加密解密
     *
     * @param str
     * @throws Exception
     */
    public static void enCode(String str) throws Exception {
        String data = encryptBASE64(str.getBytes());
        System.out.println("加密后：" + data);

        byte[] byteArray = decryptBASE64(data);
        System.out.println("解密后：" + new String(byteArray));
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
}
