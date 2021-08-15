package com.hit.server;

import com.hit.services.CacheUnitController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server implements Runnable, PropertyChangeListener {
    private enum Status {
        on,
        off
    }

    private ServerSocket server;
    private Executor executor;
    private CacheUnitController<String> controller;
    private Status status;
    private final int port = 12345;
    private Thread thread;


    public Server() {
        this.server = null;
        this.executor = null;
        this.controller = new CacheUnitController <String>();
        this.status = Status.off;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object event = evt.getNewValue();
        String eventCastToSring = (String) event;
        if (eventCastToSring.equals("START")) {
            if (this.status.equals(Status.off)) {
                this.thread =  new Thread(this);
                this.thread.start();//open new thread
                this.status = Status.on;
            } else {
                System.out.println("The server is already running");
            }
        } else if (eventCastToSring.equals("SHOTDOWN")) {
            try {
                this.controller.shotDown();
                System.out.println("success to shotdown");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (this.status.equals(Status.on)) {
                this.status = Status.off;
                this.thread.stop();

                try {
                    this.server.close();
                    System.out.println("server closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("The server is already off");
            }
        } else {
            System.out.println("Not valid command");
        }

    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket(12345);
            System.out.println("Server startes");
            System.out.println("Waiting for a client...");
            this.executor = Executors.newFixedThreadPool(3);
            while (this.status.equals(Status.on)) {
                Socket specificSocket = server.accept();
                HandleRequest<String> handleRequest = new HandleRequest<String>(specificSocket, this.controller);
                this.executor.execute(handleRequest);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (this.server != null) {
                    this.server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
