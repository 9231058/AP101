/*** In The Name of GOD ***/
package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is the server program.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class ServerProgram implements Runnable {

	private int port;
	private volatile boolean shutdown;
	private ServerSocket serverSocket = null;

	public ServerProgram(int listenPort) {
		// Server port to listen;
		this.port = listenPort;

		// Creating the server socket;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException ex) {
			serverSocket = null;
			System.err.println(ex);
		}

		// Shutdown condition;
		shutdown = false;
	}

	@Override
	public void run() {

		if (serverSocket == null)
			return;

		Socket connection;
		ClientHandler handler;

		while (!shutdown) {

			try {

				// Accepting a connection ...
				System.out.println("Waiting for connection ...");
				connection = serverSocket.accept();
				System.out.println("connection accepted.\n");

				// Processing accepted connectoin ...
				handler = new ClientHandler(connection);
				new Thread(handler).start();

			} catch (IOException ex) {
				System.err.println(ex);
			}

		}
	}

	/**
	 * Shutdown this server program.
	 */
	public synchronized void shutdownServer() {
		shutdown = true;
		try {
			serverSocket.close();
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
