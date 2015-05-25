package clientprogram;

import com.sun.corba.se.spi.activation.Server;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Seyed Mohammad Mehdi Ahmadpanah/9031806
 * 15 تیر 1391
 * 5/7/2012
 */
public class ClientProgram extends JFrame {
    private JTextField enterField;
    private JTextArea displayArea;
    
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    private String message = "";
    private String chatServer;
    
    private Socket client;
    
    private JList colors;
    
    private int counter = 0;
    
    private final String colorNames[] = {"RED","GREEN","BLUE","YELLOW","ORANGE","MAGENTA"};
    
    public ClientProgram (String host)
    {
        super("Client Program"); //the title of JFrame
        
        chatServer = host;
        
        Container container = getContentPane();
        
        enterField = new JTextField();
        enterField.setEnabled(false);
        //event handling
        enterField.addActionListener(
                new ActionListener() {
            @Override
                    public void actionPerformed (ActionEvent event)
                    {
                        sendData(event.getActionCommand());
                        displayArea.append("\nColor changed!");
                    }
                }              
         );
        enterField.setBackground(Color.LIGHT_GRAY);
        
        colors = new JList(colorNames);
        //event handling
        colors.addListSelectionListener(
                new ListSelectionListener() {
                        @Override
                    public void valueChanged(ListSelectionEvent event)
                    {
                        counter = counter + 1;
                       sendData(colors.getSelectedIndex());
                       if(counter % 2 == 0)
                       displayArea.append("\nColor changed!");
                    }
                }
         );
        colors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colors.setVisibleRowCount(5);
        
        displayArea = new JTextArea();
        
        container.add(enterField,BorderLayout.NORTH);
        container.add(colors,BorderLayout.WEST);
        container.add(new JScrollPane(displayArea),BorderLayout.CENTER);
        
        setSize(350,200);
        setLocation(50,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    //the main client method
    public void runClient(){    
        try{
            connectToServer();
            getStreams();
            processConnection();
            closeConnection();
        }
        catch(EOFException eofException){
            System.out.println("Server terminated connection");
        }
        catch(Exception exception){
            displayArea.append("\nConnection failed...\n");
        }
    }
    //method for make a stream
    private void getStreams() throws IOException{
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();
        input = new ObjectInputStream(client.getInputStream());
        displayArea.append("\nConnected\n");
    }
    //method for connect to server
    private void connectToServer() throws Exception
    {   
        displayArea.setText("Attempting connection\n");
        client = new Socket(InetAddress.getByName(chatServer),4000);
        displayArea.append("Connected to : " + client.getInetAddress().getHostName());
        displayArea.append("\nFor change server background color,");
        displayArea.append("\nyou can select from list or ");
        displayArea.append("\ntype the name of the color in textfield");
    }
    //method for do functions in connection
    private void processConnection() throws IOException
    {
        enterField.setEnabled(true);
        do{
            try{
                message = (String) input.readObject();
                if(message.equals("RED") || message.equals("red") || message.equals("GREEN") || message.equals("green") || message.equals("BLUE") || message.equals("blue") || message.equals("YELLOW") || message.equals("yellow") || message.equals("orange") || message.equals("ORANGE") || message.equals("magenta") || message.equals("MAGENTA"))
                    displayArea.append("Color changed!");
                else 
                    displayArea.append("");
            }
            catch(Exception Exception) {
                displayArea.append("\nTry Again");
            }
        }while (!message.equals("SERVER>>> TERMINATE"));    
     }
    //method for close the connection
    private void closeConnection() throws IOException
    {
        displayArea.append("\nClosing connection");
        output.close();
        input.close();
        client.close();
        
    }
    //method for send Data to server with textfield
    private void sendData(String message)
    {
        try{        
            output.writeObject("CLIENT>>> "+message);
            output.flush();
        }
        catch(IOException ioException){
            displayArea.append("\nTry Again!");
        }
    }
    //method for send Data to server with list
    private void sendData(int index){
        try{         
            if(index == 0)  message = "RED";
            if(index == 1)  message = "GREEN";
            if(index == 2)  message = "BLUE";
            if(index == 3)  message = "YELLOW";
            if(index == 4)  message = "ORANGE";
            if(index == 5)  message = "MAGENTA";
            
            output.writeObject("CLIENT>>> "+message);
            output.flush();
        }
        catch(IOException ioException){
            displayArea.append("\nTry Again!");
        }
    }
    //MAIN method
//    Server s = new Server
    public static void main(String args[])
    {
        ClientProgram client;
        if(args.length == 0){
            client = new ClientProgram("127.0.0.1");}
        else{
            client = new ClientProgram(args[0]);}
        client.runClient();
        
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }
}