package airfighter;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameMenu extends JFrame implements ActionListener,FocusListener{

    private JButton singleP , multiP , exit, ok, cancel;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private JRadioButton server, client;
    private JFrame jf;

    public GameMenu() {
        setSize(800,552);
        setLocation((width-800)/2,(height-552)/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Air Fighter");
        setLayout(null);
        getContentPane().setLayout(null);
        
        singleP = new JButton("Single Player");
        singleP.setSize(130,25);
        singleP.setLocation(50,370);
        singleP.setBackground(Color.BLACK);
        singleP.setForeground(Color.WHITE);
        singleP.addActionListener(this);
        singleP.addFocusListener(this);
        
        multiP = new JButton("Multi Player");
        multiP.setSize(130,25);
        multiP.setLocation(50,410);
        multiP.setBackground(Color.BLACK);
        multiP.setForeground(Color.WHITE);
        multiP.addActionListener(this);
        multiP.addFocusListener(this);
        
        exit = new JButton("Quit Game");
        exit.setSize(130,25);
        exit.setLocation(50,450);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.addActionListener(this);
        exit.addFocusListener(this);
        getContentPane().add(singleP);
        getContentPane().add(multiP);
        getContentPane().add(exit);
        setVisible(true);
    }

    public static void main(String[] args) {
        GameMenu gamemenu = new GameMenu();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(new ImageIcon(getClass().getResource("/images/F16.jpg")).getImage(),0,27,null);
        repaintComponents(g);
    }
 

    private  void repaintComponents(Graphics g){
        singleP.repaint();
        multiP.repaint();
        exit.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==singleP){
            dispose();
            PreGame p = new PreGame(width,height,false);
        }
        if(e.getSource()==multiP)
            Ask();
        if(e.getSource()==exit)
            System.exit(0);
        if(e.getSource()==server){
            server.setSelected(true);
            client.setSelected(false);
        }
        if(e.getSource()==client){
            client.setSelected(true);
            server.setSelected(false);
        }
        if(e.getSource()==ok){
            /*PreGame pg;
            Client c;
            if(server.isSelected())
                pg = new PreGame(width,height,true);
            if(client.isSelected())
                c = new Client();*/
        }
        if(e.getSource()==cancel)
            jf.dispose();
    }

    private void Ask() {
        jf = new JFrame();
        jf.setSize(270,135);
        jf.setLocation(this.getLocation().x + 250,this.getLocation().y + 250);
        jf.getContentPane().setLayout(null);
        jf.setTitle("Choose Mode");
        server = new JRadioButton("Server",true);
        server.setSize(80,20);
        server.setLocation(40,35);
        server.addActionListener(this);
        client = new JRadioButton("Client",false);
        client.setSize(80,20);
        client.setLocation(130,35);
        client.addActionListener(this);
        ok = new JButton("OK");
        ok.setSize(90,25);
        ok.setLocation(40,70);
        ok.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.setSize(90,25);
        cancel.setLocation(140,70);
        cancel.addActionListener(this);
        JLabel jl = new JLabel("Which one do you choose to be ?");
        jl.setSize(240,20);
        jl.setLocation(10,10);
        jf.getContentPane().add(server);
        jf.getContentPane().add(client);
        jf.getContentPane().add(ok);
        jf.getContentPane().add(cancel);
        jf.getContentPane().add(jl);
        jf.setVisible(true);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==singleP)
            singleP.setForeground(Color.RED);
        if(e.getSource()==multiP)
            multiP.setForeground(Color.RED);
        if(e.getSource()==exit)
            exit.setForeground(Color.RED);
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource()==singleP)
            singleP.setForeground(Color.WHITE);
        if(e.getSource()==multiP)
            multiP.setForeground(Color.WHITE);
        if(e.getSource()==exit)
            exit.setForeground(Color.WHITE);
    }
}
