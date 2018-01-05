
package jdm;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * @author SMA74/9031806
 * This is About JDM 1.0 window...
 */
public class AboutFrame extends JFrame{
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private BorderLayout aboutLayout;
    //Constructor
    public AboutFrame(){
        super("About JDM 1.0");
        aboutLayout = new BorderLayout(25,25);
        setLayout(aboutLayout);
        label1 = new JLabel("Java Download Manager (JDM) 1.0");
        add(label1,BorderLayout.NORTH);
        label2 = new JLabel("By:Seyed Mohammad Mehdi Ahmadpanah (SMA74) / Email : m@ahmadpanah.net   ");
        add(label2,BorderLayout.CENTER);
        Icon Iran = new ImageIcon(getClass().getResource("iran.png"));
        label3 = new JLabel();
        label3.setText("May 2012/1391 خرداد");
        label3.setIcon(Iran);
        label3.setHorizontalTextPosition(SwingConstants.CENTER);
        label3.setVerticalTextPosition(SwingConstants.BOTTOM);
        add(label3,BorderLayout.SOUTH);
        setResizable(false);
        setLocation(300, 150);
    }
}
