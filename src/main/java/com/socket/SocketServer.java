package com.socket;

/**
 * Created by Liuxd on 2018-09-15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private ServerSocket serverSocket;

    public SocketServer() {
        try {
            //设置你服务器监听的端口为10000，用户能使用的端口为1025-65535
            serverSocket = new ServerSocket(10000);
        } catch (IOException e) {
            //捕获异常，不懂的话好好看java，
            e.printStackTrace();
        }
        //创建新的监听主线程，这个线程创建ServerSocket监听
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Socket socket = null;
                    try {
                        socket = serverSocket.accept();
                        //当监听到了客户端连接后，创建新线程传输数据，这样可以实现多个客户端同时访问
                        new Thread(new SocketHandler(socket)).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    class SocketHandler implements Runnable {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;

        SocketHandler(Socket socket) {
            try {
                this.socket = socket;
                reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "GB2312"));
                writer = new PrintWriter(socket.getOutputStream(), true);
                writer.println("hello,I am service");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //这里是覆盖实现接口Runnable里的run（）
        public void run() {
            try {

                //读取数据，这里只能读取一行String
                String line = reader.readLine();
                System.out.println(line);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //最后要关闭Socket
                try {
                    if (socket != null) socket.close();
                    if (reader != null) reader.close();
                    if (writer != null) writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new SocketServer();
    }
}