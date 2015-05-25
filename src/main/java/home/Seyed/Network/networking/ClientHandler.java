/*** In The Name of GOD ***/
package networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This class is a thread to handle server/client I/O.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class ClientHandler implements Runnable {

	private Socket connection;

	/**
	 * Constructor of this class.
	 *
	 * @param connection the client socket.
	 */
	public ClientHandler(Socket connection) {
		this.connection = connection;
	}

	/**
	 * The RUN method of this thread.
	 */
	@Override
	public void run() {

		InputStream inputStream = null;
		OutputStream outputStream = null;

		// Establishing I/O streams ...
		try {
			System.out.println("Establishing I/O streams ...");
			inputStream = connection.getInputStream();
			outputStream = connection.getOutputStream();
			System.out.println("Done.\n");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		// Performing I/O ...
		performDataTransfer(inputStream, outputStream);

		// Close connection after I/O ...
		try {
			System.out.println("Closing connection ...");
			inputStream.close();
			outputStream.close();
			connection.close();
			System.out.println("Done.\n");
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	/**
	 * This method performs data send/receive operations over the network.
	 *
	 * @param inputStream the input stream of this network connection
	 * @param outputStream the output stream of this network connection
	 */
	private void performDataTransfer(InputStream inputStream, OutputStream outputStream) {

		byte[] data = loadData();

		// Sending data to client
		try {
			System.out.println("Sending data to client ...");
			outputStream.write(data);
			System.out.println("Done.\n");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		byte[] response = new byte[128];

		// Receiving client response
		try {
			System.out.println("Receiving data from client ...");
			inputStream.read(response);
			System.out.println("Done.\n");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		// Processing response
		processData(response);
	}

	/**
	 * This method contains code for loading our data as a byte array to be sent over the network.
	 *
	 * @return the byte-array data-buffer
	 */
	private byte[] loadData() {
		//
		// Create your data buffer here ...
		//
		return new byte[100];
	}

	/**
	 * This method processes received data from the client.
	 *
	 * @param response the data received from client
	 */
	private void processData(byte[] response) {
		//
		// Do your data analysis here ...
		//
	}
}
