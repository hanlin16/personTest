package com.verifyCode;

import org.apache.commons.lang.RandomStringUtils;

import javax.imageio.stream.FileImageOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Liuxd on 2018-09-07.
 */
public class GenerateCode {

    /**
     * 去除 l、o、I、O、0、1
     * 目的避免混淆
     */
    private static final char[] CHAR_ARRAY = "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789".toCharArray();

    public static void main(String[] args) {
        String checkCode = RandomStringUtils.random(4, CHAR_ARRAY);

        ByteArrayOutputStream os = (ByteArrayOutputStream) ImageUtil.generator(102, 25, checkCode);
        byte[] bytes = os.toByteArray();

        byte2image(bytes, "D:/911.jpg");
    }

    public static void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals("")) return;
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("图片已生成，地址：" + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }
}
