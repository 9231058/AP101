/*** In The Name of GOD ***/
package networking;

/**
 * This is the class with the main method.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class Main extends Thread {

	/**
	 * Application's main method.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		ServerProgram serverProgram = new ServerProgram(7654);

		Thread server = new Thread(serverProgram);
		server.start();

		Thread client1 = new Thread(new ClientProgram("127.0.0.1", 7654));
		client1.start();

		Thread client2 = new Thread(new ClientProgram("127.0.0.1", 7654));
		client2.start();

		Thread client3 = new Thread(new ClientProgram("127.0.0.1", 7654));
		client3.start();
		
		// wait for 5 seconds
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			System.err.println(ex);
		}

		// shutdown the server
		serverProgram.shutdownServer();
	}
}
