/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golabi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parham Alvani
 */
public class Handler implements Runnable {

    private Socket client;

    public Handler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = new PrintWriter(client.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            pw.println("Hello world and what is your name");
            pw.flush();
            System.out.println(br.readLine());
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
