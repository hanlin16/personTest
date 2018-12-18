package com.socket.newChat;

/**
 * Created by Liuxd on 2018-09-15.
 */

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class TestClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8081);

            new Thread(new SendMessageService(socket,"客户端：")).start();
            new Thread(new ReceiveMessageService(socket)).start();

            System.out.println("请输入...");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
