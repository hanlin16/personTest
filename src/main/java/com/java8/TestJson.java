package com.java8;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liuxd on 2018-08-10.
 */
public class TestJson {

    public static void main(String[] args) {


        List<User> list = new ArrayList<User>();
        User user1 = new User("Jack",10);
        User user2 = new User("John",17);
        list.add(user1);
        list.add(user2);

        Gson gson = new Gson();
        String json = gson.toJson(list);
        System.out.println(json);

        List<User> newList = null;

        try {
            Type objectType = new TypeToken<List<User>>() {
            }.getType();

            newList = gson.fromJson(json, objectType);
        } catch (Exception e) {
           e.printStackTrace();
        }

        System.out.println("over");


        UserList userList = new UserList();
        userList.setList(list);

        String jsonStr = gson.toJson(userList);
        System.out.println(jsonStr);

        UserList users = gson.fromJson(jsonStr,UserList.class);

        System.out.println("结束");


    }
}
