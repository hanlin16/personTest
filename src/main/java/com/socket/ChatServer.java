package com.socket;

/**
 * Created by Liuxd on 2018-09-15.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 模拟qq聊天功能： 实现客户端与服务器（一对一）的聊天功能，客户端首先发起聊天，输入的内容在服务器端和客户端显示，
 * 然后服务器端也可以输入信息，同样信息也在客户端和服务器端显示
 */

// 服务器类
public class ChatServer {
    private static int port = 8189;// 默认服务器端口

    ServerSocket serverSocket;

    public ChatServer() {
    }

    public ChatServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    // 创建指定端口的服务器
    public ChatServer(int port) {
        this.port = port;
    }

    // 提供服务
    public void service() throws Exception {

        Socket socket = serverSocket.accept();

        // 等待客户连接
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 获取控制台输入的Scanner
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        String send = scanner.nextLine();
                        System.out.println("服务器：" + send);
                        // 把服务器端的输入发给客户端
                        try {
                            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                            outputStream.writeUTF("服务器：" + send);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 读取来自客户端的信息
                    String accpet = null;
                    try {
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                        accpet = inputStream.readUTF();
                        System.out.println(accpet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(port);
        new ChatServer(server).service();
    }
}