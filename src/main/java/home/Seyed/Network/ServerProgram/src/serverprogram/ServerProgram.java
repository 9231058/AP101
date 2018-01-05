package serverprogram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 *@author Seyed Mohammad Mehdi Ahmadpanah/9031806
 * 15 تیر 1391
 * 5/7/2012
 */
public class ServerProgram extends JFrame{
    private JTextField enterField;
    private JTextArea displayArea;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private int counter = 1;   
    
//    private 
    
    public ServerProgram()
    {    
        super("Server Program");//the title of JFrame
        
        Container container = getContentPane();
        
//        server = new ServerSocket
        
        
        enterField = new JTextField();
        enterField.setEnabled(false);
        //event handling
        enterField.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        sendData(event.getActionCommand());
                    }
                }
        );
        displayArea = new JTextArea();
        
        container.add(enterField,BorderLayout.NORTH);
        container.add(new JScrollPane(displayArea),BorderLayout.CENTER);
        //JFrame properties
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(50,50);
        setSize(350,200);
        setVisible(true);
    }
    //the main server method
    public void runServer(){        
        try{
            server = new ServerSocket(4000,3);//new ServerSocket in port:4000 with 3 client simultaneously 
            while (true){
                waitForConnection();
                getStreams();
                processConnection();
                closeConnection();
                ++counter;
            }
        }
        catch(EOFException eOFException){
            System.out.println("Client terminated connection");
        }
        catch(IOException iOException){   
            System.out.println("");
        }
    }
    //method for waiting connection
    private void waitForConnection() throws IOException
    {        
        displayArea.setText("Waiting for connection...\n");
        connection = server.accept();
        displayArea.append("Connection" + counter + "recevied from : " + connection.getInetAddress().getHostName());
    }
    //method for make a stream
    private void getStreams() throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input =new ObjectInputStream(connection.getInputStream());
        displayArea.append("\nConnected\n");
    }
    //method for do functions in connection
    private void processConnection() throws IOException
    {
        String message = "SERVER>>> Connection successful";
        output.writeObject(message);
        output.flush();   
        enterField.setEnabled(true);
        do{
            try{
                message = (String) input.readObject();
                displayArea.setCaretPosition(displayArea.getText().length());     
                if (message.equals("CLIENT>>> red") || message.equals("CLIENT>>> RED") ) 
                        displayArea.setBackground(Color.RED);
                else if (message.equals("CLIENT>>> yellow") || message.equals("CLIENT>>> YELLOW") ) 
                        displayArea.setBackground(Color.YELLOW);
                else if (message.equals("CLIENT>>> orange") || message.equals("CLIENT>>> ORANGE") ) 
                        displayArea.setBackground(Color.ORANGE);
                else if (message.equals("CLIENT>>> magenta") || message.equals("CLIENT>>> MAGENTA") ) 
                        displayArea.setBackground(Color.MAGENTA);
                else if (message.equals("CLIENT>>> green") ||message.equals("CLIENT>>> GREEN")) 
                        displayArea.setBackground(Color.GREEN);
                else if(message.equals("CLIENT>>> blue") || message.equals("CLIENT>>> BLUE"))
                        displayArea.setBackground(Color.BLUE);
            }
            catch(ClassNotFoundException classNotFoundException){
                displayArea.append("\n Try Again!");
            }   
         }while(!message.equals("CLIENT>>> TERMINATE"));
     }
    //method for close the connection
    private void closeConnection() throws IOException
    { 
        displayArea.append("\nUser terminated connection");
        enterField.setEnabled(false);
        output.close();
        input.close();
        connection.close();
        
    }
    //method for send Data to Client
    private void sendData(String message)
    {
        try{
            output.writeObject("SERVER>>> "+message);
            output.flush();
        }
        catch(IOException iOException){
            displayArea.append("\n Try Again");
        }
    }
    //MAIN method
    public static void main (String args[]){
        ServerProgram application = new ServerProgram();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.runServer();
    }
}