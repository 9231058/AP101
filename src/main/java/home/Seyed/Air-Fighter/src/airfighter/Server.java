package airfighter;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class Server extends JFrame implements ActionListener,Runnable{
    private ServerSocket server;
    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    private JTextArea text;
    private JButton cancel;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private Game game;


    public Server(int d, int f) {
        super();
        setSize(300,150);
        setLocation((width - 300)/2, (height -150)/2);
        setTitle("Connection Status");
        text = new JTextArea(" \n Waiting For Connection ...");
        text.setSize(300,100);
        text.setLocation(0,0);
        cancel = new JButton("Cancel");
        cancel.setSize(80,20);
        cancel.setLocation(100,110);
        cancel.addActionListener(this);
        getContentPane().setLayout(null);
        getContentPane().add(text);
        getContentPane().add(cancel);
        setVisible(true);

        try {
            server = new ServerSocket(9000);
            client = server.accept();
            game = new Game(d,f);
        } catch (IOException ex) {
            System.err.println("IO 1");
        }
        try {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException ex1) {
            System.err.println("IO 2");
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            server.close();
        } catch (IOException ex) {
        }
        dispose();
        GameMenu gm = new GameMenu();
    }

    public void run() {

    }
}
