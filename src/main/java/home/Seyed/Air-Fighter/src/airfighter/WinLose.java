package airfighter;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

public class WinLose extends JFrame implements ActionListener{

    private JButton ok;
    private ImageIcon win, lose;
    private boolean hasWon;

    public WinLose(boolean b, int w, int h) {
        super();
        hasWon = b;
        win = new ImageIcon(getClass().getResource("/images/win.jpg"));
        lose = new ImageIcon(getClass().getResource("/images/lose.jpg"));
        if(hasWon){
            setSize(win.getIconWidth(), win.getIconHeight());
            setTitle("You Win !");
        }else {
            setSize(lose.getIconWidth(), lose.getIconHeight());
            setTitle("You Lose !");
        }
        setLocation((w - this.getWidth())/2,(h - this.getHeight())/2);
        ok = new JButton("OK");
        ok.setSize(80,30);
        ok.setLocation((this.getWidth() - 80)/2,this.getHeight()-80);
        ok.addActionListener(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(ok);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(hasWon)
            g.drawImage(win.getImage(),0,0,null);
        else g.drawImage(lose.getImage(),0,0,null);
        ok.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        GameMenu gm = new GameMenu();
    }
}
