
/* Source: http://download.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html */

BufferStrategy myStrategy;

while (!done) {
    Graphics g;
    try {
        g = myStrategy.getDrawGraphics();
        render(g);
    } finally {
        g.dispose();
    }
    myStrategy.show();
}

//==========================================================================================//

/* Source: http://gpwiki.org/index.php/Java:Tutorials:Double_Buffering */

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
 
public class Game extends JFrame {
 
	/**
	 * Main Method.
	 */
	public static void main(String[] args) {
		new Game();
	}
 
	
	/**
	 * Constructor.
	 */
	public Game() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setSize(800, 600);
		this.setVisible(true);
 
		this.createBufferStrategy(2);
 
		gameLoop();
	}
	
 
	/**
	 * Main loop of the game.
	 */
	private void gameLoop() {
		
		BufferStrategy bfStrategy = this.getBufferStrategy();
		
		boolean gameOver = false;
		
		while (!gameOver) {
		
			Graphics2D g2d = null;
			
			try {
			
				g2d = (Graphics2D) bfStrategy.getDrawGraphics();
				
				// The method to perform all graphical operations;
				render(g2d);
				
			} finally {
    			// It is best to dispose() a Graphics object when done with it.
				g2d.dispose();
			}
			
    		// Shows the contents of the backbuffer on the screen.
			bfStrategy.show();
			
	        // Tell the System to do the Drawing now, otherwise it can take a few extra ms until 
	        // Drawing is done which looks very jerky
	        Toolkit.getDefaultToolkit().sync();	
		}
	} 
}

