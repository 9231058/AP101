package jdm;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * JDM 1.0 
 *        This is The Main Class
 * @author SMA74
 * @version May 2012/1390 خرداد
 */
public class JDM{

    static Frame frame;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
        System.err.println(ex.getMessage());
        }
        frame = new Frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(JDM.class.getResource("Icon.png")));
        frame.setLocation(300, 150);
        frame.setVisible(true);
    }
}
