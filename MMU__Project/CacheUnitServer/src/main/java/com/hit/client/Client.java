package com.hit.client;

import com.hit.dm.DataModel;
import com.hit.server.Request;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void StartClient() {
        try {
            InetAddress localaddr = InetAddress.getLocalHost();
            Socket myServer = new Socket(localaddr.getHostAddress(), 12345);
            ObjectOutputStream output = new ObjectOutputStream(myServer.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(myServer.getInputStream());
            Map<String,String> headers=new HashMap<>();
            headers.put("action","GET");
            DataModel<String>[] dataModelsArray=new DataModel[4];
            dataModelsArray[0]= new DataModel<String>(1L,"data1");
            dataModelsArray[1]= new DataModel<String>(2L,"data2");
            Request<DataModel<String>> req = new Request(headers,dataModelsArray);
//            Process socket;
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(myServer.getOutputStream()));
            output.writeObject(req);
            output.flush();

            final Object o = input.readObject();
            System.out.println("Got from server:" + o);
            output.close();
            input.close();
            myServer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
