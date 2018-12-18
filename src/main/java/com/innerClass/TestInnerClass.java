package com.innerClass;

import com.google.gson.Gson;

/**
 * Created by Liuxd on 2018-09-06.
 */
public class TestInnerClass {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        /**
         * 外部类
         */
        User user = new User();
        user.setName("Jack");
        user.setAge(22);

        /**
         * 创建内部类对象
         */
        User.BankInfo bankInfo = user.new BankInfo();
        bankInfo.setBankName("中国工商银行");
        bankInfo.setBankNo("15801122225553669");

        String json = gson.toJson(user);
        System.out.println(json);
        System.out.println("---------------------------------------------------------");

        user.setBankInfo(bankInfo);
        json = gson.toJson(user);
        System.out.println(json);
        System.out.println("---------------------------------------------------------");

        /**
         * 创建静态内部类对象
         */
        User.Org org = new User.Org();
        org.setOrgCode("ceo");
        org.setOrgName("总裁办公室");
        org.print();

        System.out.println("---------------------------------------------------------");

        user.setOrg(org);
        json = gson.toJson(user);
        System.out.println(json);

    }

}
