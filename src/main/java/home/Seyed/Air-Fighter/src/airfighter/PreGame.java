package airfighter;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.Graphics;

public class PreGame extends JFrame implements ActionListener, FocusListener {

    private int height, width;
    private boolean ismulti;
    private JRadioButton mig25, mig29, su27;
    private JSlider diff;
    private ImageIcon icon1, icon2, icon3;
    private JButton start, back;
    private JLabel jl1, jl2, jl3, jl4, jl5;

    public PreGame(int w, int h, boolean b) {
        width = w;
        height = h;
        ismulti = b;
        setSize(800, 550);
        setLocation((width - 800) / 2, (height - 550) / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Air Fighter");
        setLayout(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.ORANGE);

        jl1 = new JLabel("Select Your Fighter :");
        jl1.setSize(150, 25);
        jl1.setLocation(50, 20);
        jl1.setBackground(Color.ORANGE);

        jl2 = new JLabel("Easy");
        jl2.setSize(100, 25);
        jl2.setLocation(45, 440);
        jl2.setBackground(Color.ORANGE);
        jl2.setForeground(Color.GREEN);

        jl3 = new JLabel("Normal");
        jl3.setSize(100, 25);
        jl3.setLocation(165, 440);
        jl3.setBackground(Color.ORANGE);
        jl3.setForeground(Color.YELLOW);

        jl4 = new JLabel("Hard");
        jl4.setSize(100, 25);
        jl4.setLocation(300, 440);
        jl4.setBackground(Color.ORANGE);
        jl4.setForeground(Color.RED);

        jl5 = new JLabel(" Set Difficulty :");
        jl5.setSize(150, 25);
        jl5.setLocation(50, 375);
        jl5.setBackground(Color.ORANGE);

        icon1 = new ImageIcon(getClass().getResource("/images/icon1.jpg"));

        icon2 = new ImageIcon(getClass().getResource("/images/icon2.jpg"));

        icon3 = new ImageIcon(getClass().getResource("/images/icon3.jpg"));

        mig25 = new JRadioButton("MIG 25", true);
        mig25.setSize(100, 30);
        mig25.setLocation(50, 50);
        mig25.setBackground(Color.ORANGE);
        mig25.addActionListener(this);
        mig25.addFocusListener(this);

        mig29 = new JRadioButton("MIG 29", false);
        mig29.setSize(100, 30);
        mig29.setLocation(300, 50);
        mig29.setBackground(Color.ORANGE);
        mig29.addActionListener(this);
        mig29.addFocusListener(this);

        su27 = new JRadioButton("Su 27", false);
        su27.setSize(100, 30);
        su27.setLocation(550, 50);
        su27.setBackground(Color.ORANGE);
        su27.addActionListener(this);
        su27.addFocusListener(this);

        diff = new JSlider(0, 2, 1);
        diff.setSize(270, 40);
        diff.setLocation(50, 400);
        diff.setSnapToTicks(true);
        diff.setBackground(Color.ORANGE);
        diff.addFocusListener(this);

        start = new JButton(" Start ");

        if (ismulti) {
            start.setText("Accept Connention");
        }

        start.setSize(150, 30);
        start.setLocation(550, 390);
        start.setBackground(Color.BLACK);
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        start.addFocusListener(this);

        back = new JButton(" Back ");
        back.setSize(150, 30);
        back.setLocation(550, 440);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.addFocusListener(this);

        getContentPane().add(mig25);
        getContentPane().add(mig29);
        getContentPane().add(su27);
        getContentPane().add(jl1);
        getContentPane().add(jl2);
        getContentPane().add(jl3);
        getContentPane().add(jl4);
        getContentPane().add(jl5);
        getContentPane().add(diff);
        getContentPane().add(start);
        getContentPane().add(back);

        setVisible(true);
        
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(icon1.getImage(), 10, 120, null);
        g.drawImage(icon2.getImage(), 280, 120, null);
        g.drawImage(icon3.getImage(), 510, 120, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == mig25) {
            mig25.setSelected(true);
            mig29.setSelected(false);
            su27.setSelected(false);
        }
        if (e.getSource() == mig29) {
            mig25.setSelected(false);
            mig29.setSelected(true);
            su27.setSelected(false);
        }
        if (e.getSource() == su27) {
            mig25.setSelected(false);
            mig29.setSelected(false);
            su27.setSelected(true);
        }

        if (e.getSource() == start) {
            int f = 1;
            int d = 1;
            Server s;
            if (mig29.isSelected()) {
                f = 2;
            }
            if (su27.isSelected()) {
                f = 3;
            }
            d = diff.getValue();
            if (!ismulti) {
                dispose();
                Game g = new Game(d, f);
            } else {
                s = new Server(d, f);
            }
        }

        if (e.getSource() == back) {
            dispose();
            GameMenu gm = new GameMenu();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == mig25) {
            mig25.setForeground(Color.RED);
        }
        if (e.getSource() == mig29) {
            mig29.setForeground(Color.RED);
        }
        if (e.getSource() == su27) {
            su27.setForeground(Color.RED);
        }
        if (e.getSource() == start) {
            start.setForeground(Color.RED);
        }
        if (e.getSource() == back) {
            back.setForeground(Color.RED);
        }
        if (e.getSource() == diff) {
            jl5.setForeground(Color.RED);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == mig25) {
            mig25.setForeground(Color.BLACK);
        }
        if (e.getSource() == mig29) {
            mig29.setForeground(Color.BLACK);
        }
        if (e.getSource() == su27) {
            su27.setForeground(Color.BLACK);
        }
        if (e.getSource() == start) {
            start.setForeground(Color.WHITE);
        }
        if (e.getSource() == back) {
            back.setForeground(Color.WHITE);
        }
        if (e.getSource() == diff) {
            jl5.setForeground(Color.BLACK);
        }
    }
}
