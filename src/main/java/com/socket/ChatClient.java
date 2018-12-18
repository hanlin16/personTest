package com.socket;

/**
 * Created by Liuxd on 2018-09-15.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 注意用到的输入输出流DataInputStream和DataOutputStream，成对出现，最好用字节流
 */
// 客户端类
public class ChatClient {
    private static String host = "127.0.0.1";// 默认连接到本机
    private static int port = 8189;// 默认连接到端口8189

    private Socket socket;

    public ChatClient() {

    }

    public ChatClient(Socket socket) {
        this.socket = socket;
    }

    // 连接到指定的主机和端口
    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void chat() {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        // 装饰标准输入流，用于从控制台输入
                        Scanner scanner = new Scanner(System.in);
                        String send = scanner.nextLine();
                        System.out.println("客户端：" + send);
                        // 把从控制台得到的信息传送给服务器
                        try {
                            // 向服务器端发送信息的DataOutputStream
                            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                            outputStream.writeUTF("客户端：" + send);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                            String accept = inputStream.readUTF();
                            System.out.println(accept);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        // 连接到服务器
        Socket socket = new Socket(host, port);
        new ChatClient(socket).chat();
    }
}