package me.barrytatum.BukkitRChatClient;

/**
 * File:		ChatServer.java
 * Created:		8/12/2012
 * Modified:	8/16/2012
 * Author:		Blake Renton
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

					String name, message;
					String[] container = encodedString.split(",");

					name = Base64Coder.decodeString(container[0]);
					message = Base64Coder.decodeString(container[1]);

					ChatWindow.sendMessage(String
							.format("%s:%s", name, message));
				}

			} catch (IOException e) {
				ChatWindow.logError("Disconnected from server.");
				return;
			}

		} // end while()

	} // end run()
}
