package com.hit.util;

import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class CLI implements Runnable  {

    private Scanner in;
    private PrintWriter out;
    private final PropertyChangeSupport listeners;
    private String value = null;

    /**
     *
     * @param in is the path that we read the listeners from
     * @param out is the path that we write to
     */
    public CLI(InputStream in, OutputStream out) {
        this.in = new Scanner(in);
        this.out = new PrintWriter(out);
        this.listeners = new PropertyChangeSupport(this);
    }

    /**
     *
     * @param pcl is new listener
     */
    public void addPropertyChangeListener(java.beans.PropertyChangeListener pcl) {
        this.listeners.addPropertyChangeListener(pcl);
    }

    /**
     *
     * @param pcl is the listener thar we want to remove
     */
    public void removePropertyChangeListener(java.beans.PropertyChangeListener pcl) {
        this.listeners.removePropertyChangeListener(pcl);
    }

    /**
     *
     * @return the client input
     */
    public String getGetValue() {
        return this.value;
    }

    /**
     *
     * @param value is the input from the client
     */
    public void setValue(String value) {
        this.value = value;
        this.listeners.firePropertyChange("value", null, value);
    }

    /**
     *
     * @param text is text that we write to the client
     */
    public void write(String text) {
        this.out.println(text);
        this.out.flush();
    }


    @Override
    public void run() {
        String inputFromClient = null;

        while (true) {
            write("Pleas enter your command: (START/SHOTDOWN)");
            inputFromClient = in.nextLine().toUpperCase();

            if (inputFromClient.equals("START")) {
                write("Starting server...");
                setValue(inputFromClient);
            }
            else if(inputFromClient.equals("SHOTDOWN")) {
                write("Shutdown Server");
                setValue(inputFromClient);
            }
            else {
                write("Not valid command");
            }
        }
    }
}
