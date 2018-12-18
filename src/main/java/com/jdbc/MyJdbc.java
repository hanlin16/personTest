package com.jdbc;


import java.sql.*;

/**
 * Created by Liuxd on 2018/8/19.
 */
public class MyJdbc {
    private final static String driver = "com.mysql.cj.jdbc.Driver";
    private final static String url = "jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8&useSSL=false";
    private final static String user = "root";
    private final static String password = "1234QWERasdf";

    //  声明 Connection 对象
    private Connection connection = null;
    //  声明 Statement 对象
    private Statement statement = null;

    public Connection getConnection() {
        try {
//          加载驱动
            Class.forName(driver);
//          创建连接
            connection = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();

            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        }

        return connection;
    }

    /**
     * 关闭连接
     */
    public void closeConnection() {
        if (null == connection) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建 Statement 对象
     *
     * @param connection
     * @return
     */
    public Statement getStatement(Connection connection) {
        if (null == connection) {
            return null;
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    /**
     * 关闭Statement 对象
     */
    public void closerStatement() {
        if (null == statement) {
            return;
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        MyJdbc myJdbc = new MyJdbc();
        myJdbc.getConnection();
        myJdbc.getStatement(myJdbc.connection);

        ResultSet resultSet = myJdbc.statement.executeQuery("select * from employee");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String role = resultSet.getString("role");
            System.out.println(id + " " + name + " " + role);
        }
        resultSet.close();
        myJdbc.closerStatement();
        myJdbc.closeConnection();


        System.out.println("**********");
    }


}
