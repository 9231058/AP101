/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golabi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parham Alvani
 */
public class Golabi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1373);
            while (true) {
                Socket client = ss.accept();
                (new Thread(new Handler(client))).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Golabi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
