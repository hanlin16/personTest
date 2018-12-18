package com.c3p0;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liuxd on 2018/8/19.
 */
public class TestC3p0 {

    private static Connection conn;
    private static ComboPooledDataSource dataSource;

    static {
        try {
            //获得c3p0连接池对象
            dataSource = new ComboPooledDataSource();

            dataSource.setUser("root");
            dataSource.setPassword("Chrdw@2016*");
            dataSource.setJdbcUrl("jdbc:mysql://192.168.250.91:3306/foo?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8&useSSL=false");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setInitialPoolSize(2);//初始化池大小
            dataSource.setMaxIdleTime(30);//最大空闲时间
            dataSource.setMaxPoolSize(20);//最多连接数
            dataSource.setMinPoolSize(2);//最少连接数
            dataSource.setMaxStatements(50);//每次最多可以执行多少个批处理语句

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询
     */
    private static List<Object[]> query() {

        List<Object[]> list = new ArrayList<Object[]>();
        try {

            // 获取数据库连接
            conn = dataSource.getConnection();
            // 查询sql
            String sql = "select * from b_user limit 10";


            // 读取数据
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //结果集
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int uid = resultSet.getInt("uid");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");
                String passwd = resultSet.getString("passwd");

                Object[] objects = new Object[]{uid, name, age, phone, passwd};

                list.add(objects);
            }

            resultSet.close();
            preparedStatement.close();
            //Connection连接对象归还数据库连接池
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    /**
     * 新增
     */
    private static void add(String name, int age, String phone, String passwd) {

        try {

            // 获取数据库连接
            conn = dataSource.getConnection();

            String insertSql = "insert into `user` (`name`, `age`, `phone`, `passwd`) values(?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(insertSql);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, phone);
            ps.setString(4, passwd);
            int row = ps.executeUpdate();

            System.out.println("新增结果： " + row);


            ps.close();
            //Connection连接对象归还数据库连接池
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改
     */
    private static void update(int uid, String name, int age, String phone, String passwd) {

        try {

            // 获取数据库连接
            conn = dataSource.getConnection();

            String updateSql = "UPDATE USER t SET t.name=? ,t.age=?,t.phone=?,t.passwd=? WHERE t.uid=?";

            PreparedStatement preparedStatement = conn.prepareStatement(updateSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, passwd);
            preparedStatement.setLong(5, uid);
//          执行sql
            preparedStatement.executeUpdate();

            int row = preparedStatement.executeUpdate();

            System.out.println("修改结果： " + row);

            //Connection连接对象归还数据库连接池
            conn.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 删除
     */
    private static void deleteById(int uid) {

        try {

            // 获取数据库连接
            conn = dataSource.getConnection();

            String sql = "delete from USER where uid=?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, uid);

            int row = preparedStatement.executeUpdate();

            System.out.println("删除结果： " + row);

            preparedStatement.close();
            //Connection连接对象归还数据库连接池
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

        /**
         * 2、查询
         */
        List<Object[]> list = query();

        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] objects = list.get(i);
                for (int j = 0; j < objects.length; j++) {
                    System.out.print(objects[j] + "    ");
                }
                System.out.println();
            }
        }


    }

}
