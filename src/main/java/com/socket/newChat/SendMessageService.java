package com.socket.newChat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Liuxd on 2018-09-15.
 */
public class SendMessageService implements Runnable {
    private Socket socket;
    private String name;

    public SendMessageService(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    public void run() {
        PrintWriter pWriter = null;
        Scanner scanner = null;
        try {
            pWriter = new PrintWriter(socket.getOutputStream());
            scanner = new Scanner(System.in);
            while (true) {
                String s = scanner.nextLine();
                pWriter.write(name + s + "\n");
                pWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            pWriter.close();
        }

    }
}
