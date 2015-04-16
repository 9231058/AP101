package windowscalculator;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Windows Calculator
 * @version 27.04.2012/08.02.1391
 * @author SMA74
 */
public class WindowsCalculator extends JFrame {

public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        Frame frame = new Frame();
        frame.setLocation(500, 100);
        frame.setSize(200,180);
        frame.setResizable(false);
        /**/frame.setIconImage(Toolkit.getDefaultToolkit().getImage(WindowsCalculator.class.getResource("Icon.png")));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
