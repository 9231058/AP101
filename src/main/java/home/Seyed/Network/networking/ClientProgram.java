/*** In The Name of GOD ***/
package networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * This is the client program.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class ClientProgram implements Runnable {

	private Socket connection = null;

	/**
	 * Constructor of this class.
	 * 
	 * @param ip IP-address of the server
	 * @param port port number of the server
	 */
	public ClientProgram(String ip, int port) {

		connection = new Socket();

		InetSocketAddress address = new InetSocketAddress(ip, port);

		try {
			connection.connect(address, 7000);
		} catch (SocketTimeoutException ex) {
			System.err.println(ex);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	/**
	 * The RUN method of this thread.
	 */
	@Override
	public void run() {

		if (connection == null || !connection.isConnected()) {
			System.out.println("Connection to server failed!");
			return;
		}

		InputStream inputStream = null;
		OutputStream outputStream = null;

		// Establishing I/O streams ...
		try {
			inputStream = connection.getInputStream();
			outputStream = connection.getOutputStream();
		} catch (IOException ex) {
			System.err.println(ex);
		}

		// Performing I/O ...
		performDataTransfer(inputStream, outputStream);

		// Closing connection ...
		try {
			inputStream.close();
			outputStream.close();
			connection.close();
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

		byte[] data = new byte[64];

		try {
			System.out.println("Receiving data from server ...");
			inputStream.read(data);
			System.out.println("Done.\n");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		byte[] response = processDataAndReadyResponse(data);

		try {
			System.out.println("Sending response to server ...");
			outputStream.write(response);
			System.out.println("Done.\n");
		} catch (IOException ex) {
			System.err.println(ex);
		}


	}

	/**
	 * This method contains code to process received data and prepare a proper response accordingly.
	 * 
	 * @param data data received from server
	 * @return response for server
	 */
	private byte[] processDataAndReadyResponse(byte[] data) {
		//
		// Do your data analysis here and
		// return a response accordingly ...
		//
		return new byte[300];
	}
}
