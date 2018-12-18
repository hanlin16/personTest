package com.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Liuxd on 2018-09-15.
 */
public class SocketService {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8081);
        BufferedReader reader;
        PrintWriter writer;
        while(true) {
            Socket socket = serverSocket.accept();
            writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner input =new Scanner(System.in);
            String instr = input.nextLine();
            writer.println("client:"+instr);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            String line = reader.readLine();
            System.out.println(line);
        }

    }


}
