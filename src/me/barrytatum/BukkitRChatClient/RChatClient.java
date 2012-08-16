package me.barrytatum.BukkitRChatClient;

import java.io.IOException;

/**
 * File: RChatClient.java
 * Created: 8/12/2012
 * Modified: 8/16/2012 
 * Author: Barry Tatum, Blake Renton
 */

public class RChatClient {

	public static ChatHandler chInstance;

	/**
	 * Constructor for RChatClient. Instantiates the user interface and
	 * establishes the network connections.
	 */

	public static void main(String[] args) {

		new ChatWindow();

		String host = "localhost";
		int port = 5956;

		ChatWindow.sendMessage(String.format("Connecting to %s:%d...", host,
				port));

		do {

			try {
				chInstance = new ChatHandler(host, port);

			} catch (IOException e) {
				ChatWindow
						.logError("Unable to connect.  Attempting to retry in 10 seconds.");

				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					return;
				}

			} // end try()

		} // end do()

		while (chInstance == null);

		ChatWindow.sendMessage("Connected.\n");
	}
}
