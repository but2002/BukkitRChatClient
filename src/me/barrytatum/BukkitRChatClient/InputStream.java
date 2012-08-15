package me.barrytatum.BukkitRChatClient;

/**
 * The data listener for the Bukkit Remote Chat plugin.
 * 
 * Authors: Blake Renton, Barry Tatum
 * Website: http://www.blakerenton.net
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

public class InputStream implements Runnable {

	private BufferedReader in;

	/**
	 * Constructor to provide the class with the output stream of the provided
	 * Socket.
	 * 
	 * @param connection
	 * @throws IOException
	 */

	InputStream(Socket connection) throws IOException {
		this.in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
	}

	/**
	 * Listen for incoming data.
	 */

	public void run() {

		String message;

		// Always listen for messages.
		
		while (true) {
			try {
				while ((message = this.in.readLine()) != null) {
					RChatClient.chatBox.append(String.format("%s\n", message));
					System.out.println(message);
				}
				
			} catch (IOException e) {
				RChatClient.chatBox
						.append("[Notice] Connection to server interrupted.\n");
				return;
			}

		} // end while()

	} // end run()
}
