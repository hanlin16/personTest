package com.syn;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Liuxd on 2018-08-10.
 */
public class TestPrint {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化

    public void print(String str) {
        synchronized (str.intern()) {
            try {
                Thread.sleep(1000);

                System.out.println(Thread.currentThread().getId() + "打印操作：" + str + "  时间" + sdf.format(new Date()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
