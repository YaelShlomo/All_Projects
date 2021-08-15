package com.hit.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.hit.dm.DataModel;
import com.hit.server.Request;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.*;

public class testPartC {

    private BufferedReader br;

    @Test
    public void testUpdate() {

        Map<String, String> headerReq = new HashMap<>();
        headerReq.put("action", "UPDATE");

        DataModel<String>[] dmArray = new DataModel[]{new DataModel<String>(1L, "a"), new DataModel<String>(2L, "b")};

        Request<DataModel<String>[]> req = new Request<>(headerReq, dmArray);
        req.getHeaders();
        Gson gson = new Gson();
        try {
            Socket socket = new Socket("127.0.0.1", 12666);
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            writer.writeUTF(gson.toJson(req));
            writer.flush();


            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String content = "";

            do {
                content = in.readUTF();
                sb.append(content);
            } while (in.available() != 0);

            content = sb.toString();
            Boolean response=true;
            response = new Gson().fromJson(content, response.getClass());
            System.out.println("message from server: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testGet(){
        Map<String, String> headerReq = new HashMap<>();
        headerReq.put("action", "GET");

        DataModel<String>[] dmArray = new DataModel[]{new DataModel<String>(1L, "bb"), new DataModel<String>(2L, "aa")};
        Request<DataModel<String>[]> req = new Request<>(headerReq, dmArray);

        Gson gson = new Gson();
        try {
            Socket socket = new Socket("127.0.0.1", 12666);
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            writer.writeUTF(gson.toJson(req));
            writer.flush();

            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String content = "";

            do {
                content = in.readUTF();
                sb.append(content);
            } while (in.available() != 0);

            content = sb.toString();
            Type requestType = new TypeToken<DataModel<String>[]>() {}.getType();
            DataModel<String>[] response = new Gson().fromJson(content, requestType);
            System.out.println("message from server: " + Arrays.toString(response));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testDelete(){
        Map<String, String> headerReq = new HashMap<>();
        headerReq.put("action", "DELETE");

        DataModel<String>[] dmArray = new DataModel[]{new DataModel<String>(1L, "b")};
        Request<DataModel<String>[]> req = new Request<>(headerReq, dmArray);

        Gson gson = new Gson();
        try {
            Socket socket = new Socket("127.0.0.1", 12666);
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            writer.writeUTF(gson.toJson(req));
            writer.flush();

            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String content = "";

            do {
                content = in.readUTF();
                sb.append(content);
            } while (in.available() != 0);

            content = sb.toString();
            Boolean response=true;
            response = new Gson().fromJson(content, response.getClass());
            System.out.println("message from server: " + response);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


