package jdm;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;

/**
 *This is NewURL class for give url address from user...
 * @author SMA74/9031806
 */
public class NewURL extends JFrame{
    private JTextField address;
    JButton enter;
    static int downloadLimit = -1;
    public NewURL(){
      super("Enter Address");
      JLabel text=new JLabel("Address:");
      address=new JTextField("http://",17);
      JPanel panel=new JPanel();
      enter=new JButton("Enter");
      class enterActionListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent event) {
                WindowEvent wev = new WindowEvent(NewURL.this, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);          
                try {
                    if((Frame.model.getDownloadingCount() < downloadLimit)||(downloadLimit==-1))
                        new Downloading( new URL(address.getText()) );
                    else
                        JOptionPane.showMessageDialog(rootPane, "The Number of Simultaneous Downloads Limited!","Error",JOptionPane.ERROR_MESSAGE);
                }
                catch (MalformedURLException ex)
                {
                    JOptionPane.showMessageDialog(rootPane,"Insert Correct Address,PLZ!","Error",JOptionPane.ERROR_MESSAGE);
                }   
            }
        }
      class textFieldActionListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                enter.doClick();
            }
      }
      enter.addActionListener(new enterActionListener());
      address.addActionListener(new textFieldActionListener());
      panel.add(text);
      panel.add(address);
      panel.add(enter);
      add(panel);
      pack();
      setResizable(false);
      setLocation(300,150);
      setVisible(true);
    }
}
