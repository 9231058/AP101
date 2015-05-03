package airfighter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

import javax.swing.*;

public class Game extends JFrame implements ActionListener {

    private int scrHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int scrWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private JLabel jl1, jl2, jl3, jl4, jl5;
    private JTextField shotNum, lostNum;
    private JSlider fuel;
    private JButton pause, exit;
    private Thread runner;
    private GamePanel gamePanel;

    public Game(int difficulty, int fighter) {
        super("Air Fighter");
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        setSize(950, scrHeight - 55);
        setLocation((scrWidth - getWidth()) / 2, (scrHeight - getHeight()) / 2);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setFocusable(true);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.BLUE);
        
        jl1 = new JLabel("Empty");
        jl1.setSize(100, 20);
        jl1.setLocation(708, 110);
        jl1.setForeground(Color.BLACK);
        
        jl2 = new JLabel("Half Full");
        jl2.setSize(100, 20);
        jl2.setLocation(795, 110);
        jl2.setForeground(Color.BLACK);
        
        jl3 = new JLabel("Full");
        jl3.setSize(100, 20);
        jl3.setLocation(895, 110);
        jl3.setForeground(Color.BLACK);
        
        jl4 = new JLabel("Enemy Down :");
        jl4.setSize(120, 30);
        jl4.setLocation(740, 300);
        jl4.setForeground(Color.BLACK);
        
        jl5 = new JLabel("Enemy Missed :");
        jl5.setSize(120, 30);
        jl5.setLocation(740, 350);
        jl5.setForeground(Color.BLACK);
        
        shotNum = new JTextField("0");
        shotNum.setSize(50, 30);
        shotNum.setLocation(870, 300);
        shotNum.setEditable(false);
        shotNum.setForeground(Color.BLACK);
        
        lostNum = new JTextField("0");
        lostNum.setSize(50, 30);
        lostNum.setLocation(870, 350);
        lostNum.setEditable(false);
        lostNum.setForeground(Color.RED);
        
        fuel = new JSlider(0, 2000, 1800);
        fuel.setSize(200, 30);
        fuel.setEnabled(false);
        fuel.setLocation(708, 80);
        fuel.setBackground(Color.BLUE);
        fuel.setForeground(Color.BLACK);
        
        pause = new JButton("Pause");
        pause.setSize(120, 30);
        pause.setLocation(770, scrHeight - 200);
        pause.setForeground(Color.RED);
        pause.setBackground(Color.BLACK);
        pause.addActionListener(this);
        
        exit = new JButton("Exit");
        exit.setSize(120, 30);
        exit.setLocation(770, scrHeight - 150);
        exit.setForeground(Color.RED);
        exit.setBackground(Color.BLACK);
        exit.addActionListener(this);
        
        gamePanel = new GamePanel(difficulty, fighter, this, 11 * 64, getHeight());
        gamePanel.setLocation(0, 0);
        
        getContentPane().add(pause);
        getContentPane().add(exit);
        getContentPane().add(fuel);
        getContentPane().add(jl1);
        getContentPane().add(jl2);
        getContentPane().add(jl3);
        getContentPane().add(jl4);
        getContentPane().add(jl5);
        getContentPane().add(shotNum);
        getContentPane().add(lostNum);
        getContentPane().add(gamePanel);
                
        setVisible(true);
        
        gamePanel.requestFocusInWindow();
        
        runner = new Thread(gamePanel);
        runner.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pause) {
            if (pause.getText().equals("Pause")) {
                gamePanel.pause();
                pause.setText("Resume");
            } else {
                gamePanel.resume();
                gamePanel.requestFocusInWindow();
                pause.setText("Pause");
            }
        }
        if (e.getSource() == exit) {
            gamePanel.myExit();
        }
    }
    
    public void setFuel(int val) {
        fuel.setValue(val);
    }
    
    public void setLostNum(int val) {
        lostNum.setText(Integer.toString(val));
    }
    
    public void setShotNum(int val) {
        shotNum.setText(Integer.toString(val));
    }
    
    public void myExit() {
        //while (runner.isAlive()) ;
        dispose();
        new GameMenu();
    }

    public void win() {
        JOptionPane.showMessageDialog(this, " You Won ! \n Enemy Down : " + shotNum.getText() + 
                "\n Enemy Missed : " + lostNum.getText(), "CONGRATULATION", JOptionPane.PLAIN_MESSAGE);
        dispose();
        new WinLose(true, scrWidth, scrHeight);
    }

    public void lose() {
        dispose();
        new WinLose(false, scrWidth, scrHeight);
    }
}


class GamePanel extends JPanel implements Runnable {

    private int consumption, x, y, rows, ydis, scroll, fuelLeft, xRoc1, xRoc2, yRoc1, yRoc2, xF1, xF2, yF1, yF2, exN, eX, eY, shot, lost;
    private int scrHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int scrWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private boolean flag, alive, f1, f2, roc1, roc2;
    private volatile boolean isPaused, left, right;
    private ImageIcon fighterImage, rocket, F22, exp;
    private int map[][] = new int[11][333];
    private BufferedImage bufferedScreen;
    private Graphics2D bufferedGraphics;
    private Game game;
    
    public GamePanel(int difficulty, int fighter, Game g, int width, int height) {
        
        this.game = g;
        
        setSize(width, height);
        
        if (difficulty == 0) {
            consumption = 2;
        }
        if (difficulty == 1) {
            consumption = 4;
        }
        if (difficulty == 2) {
            consumption = 6;
        }
        left = right = false;
        f1 = f2 = roc1 = roc2 = flag = alive = true;
        xF1 = 64;
        xF2 = 8 * 64;
        yF1 = yF2 = -100;
        rows = (scrHeight - 65) / 64 + 2;
        scroll = exN = ydis = shot = lost = 0;
        x = 4 * 64 + 32;
        fuelLeft = 1800;
        if (fighter == 1) {
            fighterImage = new ImageIcon(getClass().getResource("/images/MIG25.png"));
            y = scrHeight - 200;
        }
        if (fighter == 2) {
            fighterImage = new ImageIcon(getClass().getResource("/images/MIG29.png"));
            y = scrHeight - 230;
        }
        if (fighter == 3) {
            fighterImage = new ImageIcon(getClass().getResource("/images/Su27.png"));
            y = scrHeight - 225;
        }
        rocket = new ImageIcon(getClass().getResource("/images/Rocket.png"));
        F22 = new ImageIcon(getClass().getResource("/images/F22.png"));
        exp = new ImageIcon(getClass().getResource("/images/exp.png"));

        // Create the offscreen buffer for offline drawing;
        bufferedScreen = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        // Get the graphics of the offscreen buffer;
        bufferedGraphics = (Graphics2D) bufferedScreen.createGraphics();

        loadMap();

    }

    @Override
    public void paint(Graphics g) {
        // Call super class's paint
        super.paint(g);
        // Do all drawing stuff on the offscreen image;
        render(bufferedGraphics);
        // Now, draw the offscreen image to the screen like a normal image.
        g.drawImage(bufferedScreen, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    
    /**
     * The method to perform all graphical operations of the game.
     */
    private void render(Graphics2D g2d) {
        if (left && x >= 12) {
            x -= 16;
        }
        if (right && (x < 11 * 64 - 140)) {
            x += 16;
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < rows; j++) {
                if (j + ydis < 300) {
                    g2d.drawImage(new ImageIcon(getClass().getResource("/images/" + map[i][j + ydis] + ".png")).getImage(), i * 64, (rows - j - 1) * 64 + scroll - 30, null);
                    if ((map[i][j + ydis] == 4) && (i * 64 > x) && (i * 64 < x + 128) && ((rows - j - 1) * 64 > y) && ((rows - j - 1) * 64 < scrHeight)) {
                        if (fuelLeft < 1968) {
                            fuelLeft += 28;
                        } else {
                            fuelLeft = 2000;
                        }
                    }
                }
            }
        }
        if ((ydis > 1) && roc1) {
            g2d.drawImage(rocket.getImage(), xRoc1, yRoc1, null);
        }
        if (roc2) {
            g2d.drawImage(rocket.getImage(), xRoc2, yRoc2, null);
        }
        if ((ydis > 3) && f1) {
            g2d.drawImage(F22.getImage(), xF1, yF1, null);
        }
        if ((ydis > 8) && f2) {
            g2d.drawImage(F22.getImage(), xF2, yF2, null);
        }
        if (exN > 0) {
            g2d.drawImage(exp.getImage(), eX, eY, null);
            exN--;
        }
        g2d.drawImage(fighterImage.getImage(), x, y, null);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = true;
            }
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = false;
            }
        }
    }

    @Override
    public void run() {
        while (alive) {
            scroll += 8;
            scroll = scroll % 64;
            if (ydis > 1) {
                yRoc1 -= 22;
            }
            if (scroll % 64 == 0) {
                ydis++;
            }
            setF22();
            setRocket();
            destroy();
            die();
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println(" sleep error");
            }
            fuelLeft -= consumption;
            game.setFuel(fuelLeft);
            yRoc2 -= 22;
            if (ydis > 3) {
                yF1 += 12;
            }
            if (ydis > 8) {
                yF2 += 12;
            }
            if (isPaused) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException ex1) {
                    }
                }
            }
            setFocusable(true);
            if (fuelLeft <= 0) {
                alive = false;
                JOptionPane.showMessageDialog(this, " You Ran Out Of Fuel !"
                        + "\n Enemy Down : " + shot
                        + "\n Enemy Missed : " + lost, "Game Over", JOptionPane.PLAIN_MESSAGE);
                lose();
            }
            if (ydis > 200) {
                alive = false;
                win();
            }
        }
    }
    
    public void pause() {
        isPaused = true;
    }
    
    public void resume() {
        isPaused = false;
        synchronized (this) {
            notifyAll();
        }
    }

    private void setRocket() {
        if (yRoc1 < 0) {
            xRoc1 = x + 10;
            yRoc1 = y - 16;
            roc1 = true;
        }
        if (yRoc2 < 20) {
            xRoc2 = x + 100;
            yRoc2 = y - 16;
            roc2 = true;
        }
    }

    private void setF22() {
        if (yF1 >= scrHeight + 150) {
            xF1 = ((int) Math.random() * 5) * 64;
            yF1 = -100;
            if (f1) {
                lost++;
                game.setLostNum(lost);
            }
            f1 = true;
        }
        if (yF2 >= scrHeight + 150) {
            xF2 = ((int) Math.random() * 5 + 5) * 64;
            yF2 = -100;
            if (f2) {
                lost++;
                game.setLostNum(lost);
            }
            f2 = true;
        }
    }

    private void destroy() {
        if (roc1 && f1 && (xRoc1 + 6 >= xF1) && (xRoc1 + 6 < xF1 + 128) && (yRoc1 <= yF1 + 175) && (yRoc1 > yF1)) {
            f1 = roc1 = false;
            exN = 4;
            eX = xF1 + 32;
            eY = yRoc1 - 140;
            shot++;
            game.setShotNum(shot);
        }
        if (roc2 && f1 && (xRoc2 + 6 >= xF1) && (xRoc2 + 6 < xF1 + 128) && (yRoc2 <= yF1 + 175) && (yRoc2 > yF1)) {
            f1 = roc2 = false;
            exN = 4;
            eX = xF1 + 32;
            eY = yRoc2 - 140;
            shot++;
            game.setShotNum(shot);
        }
        if (roc1 && f2 && (xRoc1 + 6 >= xF2) && (xRoc1 + 6 < xF2 + 128) && (yRoc1 <= yF2 + 175) && (yRoc1 > yF2)) {
            f2 = roc1 = false;
            exN = 4;
            eX = xF2 + 32;
            eY = yRoc1 - 140;
            shot++;
            game.setShotNum(shot);
        }
        if (roc2 && f2 && (xRoc2 + 6 >= xF2) && (xRoc2 + 6 < xF2 + 128) && (yRoc2 <= yF2 + 175) && (yRoc2 > yF2)) {
            f2 = roc2 = false;
            exN = 4;
            eX = xF2 + 32;
            eY = yRoc2 - 140;
            shot++;
            game.setShotNum(shot);
        }
    }

    private void die() {
        if (f1 && (x + 20 > xF1 + 10) && (x + 90 < xF1 + 120) && (y + 150 > yF1 + 10) && (y + 40 < yF1 + 170)) {
            alive = false;
            JOptionPane.showMessageDialog(this, " You Crashed ! \n Enemy Down : " + shot + 
                    "\n Enemy Missed : " + lost, "Game Over", JOptionPane.PLAIN_MESSAGE);
            lose();
        }
        if (f2 && (x + 20 > xF2 + 10) && (x + 90 < xF2 + 120) && (y + 150 > yF2 + 10) && (y + 40 < yF2 + 170)) {
            alive = false;
            JOptionPane.showMessageDialog(this, " You Crashed ! \n Enemy Down : " + shot + 
                    "\n Enemy Missed : " + lost, "Game Over", JOptionPane.PLAIN_MESSAGE);
            lose();
        }
    }
    
    private void loadMap() {
        File f = new File("RiverMap.txt");
        DataInputStream din = null;
        try {
            din = new DataInputStream(new FileInputStream(f));
        } catch (FileNotFoundException ex) {
            System.err.println(" File Not Found");
        }
        try {
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 333; j++) {
                    map[i][j] = din.readInt();
                    if ((i == 2) && (j % 30 == 16)) {
                        map[i][j] = 4;
                    }
                    if ((i == 10) && (j % 30 == 29)) {
                        map[i][j] = 4;
                    }
                }
            }
        } catch (IOException ex1) {
            System.err.println(" ERROR 1: " + ex1.toString());
        }
        try {
            din.close();
        } catch (IOException ex2) {
            System.err.println(" ERROR 2: " + ex2.toString());
        }
    }

    public void myExit() {
        alive = false;
        synchronized (this) {
            notifyAll();
        }
        game.myExit();
    }

    private void win() {
        alive = false;
        synchronized (this) {
            notifyAll();
        }
        game.win();
    }

    private void lose() {
        alive = false;
        synchronized (this) {
            notifyAll();
        }
        game.lose();
    }
}
