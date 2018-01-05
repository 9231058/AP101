package airfighter;

import java.net.Socket;
import java.io.DataOutputStream;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.io.DataInputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.net.*;
import java.io.*;

public class Client extends JFrame implements ActionListener,Runnable{

    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    private JTextArea text;
    private JButton cancel;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private Game game;

    public Client() {
        super();
        setSize(300,150);
        setLocation((width - 300)/2, (height -150)/2);
        setTitle("Connection Status");
        text = new JTextArea(" \n Attempting To Connect ...");
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
            client = new Socket("localhost", 9000);
        } catch (UnknownHostException ex) {
            System.err.println(" Host Error");
        } catch (IOException ex) {
            System.err.println(" IO 3");
        }
        try {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException ex1) {
        }
    }

    public void run() {
    }

    public void actionPerformed(ActionEvent e) {
        try {
            client.close();
        } catch (IOException ex) {
        }
        dispose();
        GameMenu gm = new GameMenu();
    }
}
