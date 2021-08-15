package com.hit.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;
import org.junit.platform.engine.TestDescriptor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class HandleRequest<T> implements Runnable {

    private Socket socket;
    private CacheUnitController<T> controller;
    private Map<String, String> header;//The requests from the client side

    public HandleRequest(Socket socket, CacheUnitController<T> controller) {
        this.socket = socket;
        this.controller = controller;
    }

    @Override
    public void run() {
        InputStreamReader streamReader = null;
        OutputStreamWriter streamWriter = null;
        try {
            streamReader = new InputStreamReader(this.socket.getInputStream());//try to open new socket to read from
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            streamWriter = new OutputStreamWriter(this.socket.getOutputStream());//try to open new socket to write to
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner reader = new Scanner(streamReader);
        PrintWriter printWriter = new PrintWriter(streamWriter);
        String res = "";
        String req = reader.nextLine();
        Type ref = new TypeToken<Request<DataModel<T>[]>>() {}.getType();
        Request<DataModel<T>[]> request = new Gson().fromJson(req, ref);//the request in JSON format
        this.header = request.getHeaders();//the header of the request
        boolean isSuccess = false;
        String action = this.header.get("action");//ans is the type of the action
        DataModel<T>[] reqBody = request.getBody();
        switch (action) {
            case "UPDATE":
                try {
                   //in case action is UPDATE, update the req from the controller
                    isSuccess = this.controller.update(reqBody);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GET":
                try {
                    this.controller.get(reqBody);//in case action is GET, get the req from the controller
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (controller != null) ;
                isSuccess = true;
                break;
            case "DELETE":
                //in case the action is DELETE, delete the req from the controller
                isSuccess = this.controller.delete(reqBody);
                break;

            case "STATS":
                //in case the action is STATS, play the showStats function
                res = controller.showStats();
            default:
                break;
        }
        if (!this.header.get("action").equals("STATS")) {
            if ( isSuccess ) {
                res="true";
            }
            else {
                res="false";
            }
        }
        printWriter.println(res);
        printWriter.flush();

    }

}
