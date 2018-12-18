package com.jdbc;

/**
 * Created by Liuxd on 2018-08-29.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liuxd on 2018/8/19.
 */
public class UserDao {

    public List<Object[]> query() {

        MyJdbc myJdbc = new MyJdbc();
        Connection connection = myJdbc.getConnection();
        if (null == connection) {
            System.out.println("获取数据库连接异常");
            return null;

        }

        Statement statement = myJdbc.getStatement(connection);
        if (null == statement) {
            System.out.println("创建事务失败");
            myJdbc.closeConnection();
            return null;
        }

        List<Object[]> list = new ArrayList<Object[]>();
        ResultSet resultSet = null;
        try {

            String sql = "select t.* from user t";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Long id = resultSet.getLong("uid");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");
                String passwd = resultSet.getString("passwd");

                Object[] objects = new Object[]{id, name, age, phone, passwd};

                list.add(objects);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            myJdbc.closerStatement();
            myJdbc.closeConnection();
        }

        return list;
    }

    public void update(long id, String name, int age, String phone, String passwd) {
        MyJdbc myJdbc = new MyJdbc();
        Connection connection = myJdbc.getConnection();
        if (null == connection) {
            System.out.println("修改操作获取数据库连接异常");
            return;

        }


        PreparedStatement preparedStatement = null;

        String sql = "UPDATE USER t SET t.name=? ,t.age=?,t.phone=?,t.passwd=? WHERE t.uid=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, passwd);
            preparedStatement.setLong(5, id);
//          执行sql
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            myJdbc.closeConnection();

        }
    }

    public void delete(int id) {
        MyJdbc myJdbc = new MyJdbc();
        Connection connection = myJdbc.getConnection();
        if (null == connection) {
            System.out.println("删操作获取数据库连接异常");
            return;

        }


        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM USER WHERE uid=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            myJdbc.closeConnection();

        }
    }

    public void add(String name, int age, String phone, String passwd) {
        MyJdbc myJdbc = new MyJdbc();
        Connection connection = myJdbc.getConnection();
        if (null == connection) {
            System.out.println("新增操作获取数据库连接异常");
            return;

        }


        PreparedStatement preparedStatement = null;

        String sql = "insert into `user` (`name`, `age`, `phone`, `passwd`) values(?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, passwd);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            myJdbc.closeConnection();

        }
    }
}

