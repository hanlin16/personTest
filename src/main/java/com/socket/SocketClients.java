package com.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Liuxd on 2018-09-15.
 */
public class SocketClients {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8081);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        while (true) {
            Scanner input = new Scanner(System.in);
            String instr = input.nextLine();
            writer.println("client:" + instr);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

            String line = reader.readLine();
            System.out.println(line);
        }


    }

}
