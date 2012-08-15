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

import biz.source_code.base64Coder.Base64Coder;

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

		String encodedString;

		while (true) {
			
			try {
				if ((encodedString = this.in.readLine()) != null) {
					
					System.out.println(encodedString);
					
					String name, message;
					String[] container = encodedString.split(",");
					
					name = Base64Coder.decodeString(container[0]);
					message = Base64Coder.decodeString(container[1]);

					RChatClient.chatBox.append(String.format("%s: %s\n", name, message));
				}
				
			} catch (IOException e) {
				RChatClient.chatBox
						.append("[Notice] Connection to server interrupted.\n");
				return;
			}

		} // end while()

	} // end run()
}
