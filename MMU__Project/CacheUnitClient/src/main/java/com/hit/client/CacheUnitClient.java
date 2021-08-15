package com.hit.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CacheUnitClient extends Object {

    private Socket  socket;
    private int port = 12345;//the port of the server

    public CacheUnitClient()  {}

    public String send(String request) throws ClassNotFoundException
    {
        String res = "empty";
        try {
            socket = new Socket("localhost", port);
            Scanner reader = new Scanner(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.println(request);
            writer.flush();

            res = (String) reader.nextLine();
            writer.close();
            reader.close();
            socket.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}