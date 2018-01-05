/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gloabiclient;

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
public class GloabiClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 1373);
            PrintWriter pw = new PrintWriter(client.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(br.readLine());
            pw.println("Parham Alvani");
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(GloabiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
