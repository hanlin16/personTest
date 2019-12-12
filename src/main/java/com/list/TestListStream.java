package com.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuxd
 * @version 1.0
 * @date 2019-12-12 14:29
 */
public class TestListStream {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user1 = new User("张三", 22, "男");
        User user2 = new User("李四", 16, "男");
        User user3 = new User("莉莉", 17, "女");
        User user4 = new User("美美", 19, "女");
        User user5 = new User("张聪", 24, "男");
        User user6 = new User("李玲", 22, "男");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);

        List<User> newList = new ArrayList<>(6);

        /**
         * 第一、传统方式过滤排序
         */
        //遍历集合,提取大于等于18岁用户存放至newList中
        for (User s : list) {
            if (s.getAge() >= 18) {
                newList.add(s);
            }
        }
        //对newList按照年龄进行降序排序
        newList.sort(new Comparator<User>() {
            @Override
            public int compare(User s1, User s2) {
                //降序排序
                return Integer.compare(s2.getAge(), s1.getAge());
            }
        });

        System.out.println(newList);

        /*
         *第二、使用stream的写法
         * 1、获取List的stream对象
         * 2、使用filter方法进行过滤
         * 3、使用sorted方法进行排序
         * 4、使用collect方法将处理好的stream对象转换为集合对象
         */
        newList = list.stream()
                .filter(s -> s.getAge() >= 18)
                .sorted(Comparator.comparing(User::getAge).reversed())
                .collect(Collectors.toList());
        System.out.println(newList);

    }

}
