package me.barrytatum.BukkitRChatClient;

/**
 * File:		ChatServer.java
 * Created:		8/12/2012
 * Modified:	8/15/2012
 * Author:		Blake Renton
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import biz.source_code.base64Coder.Base64Coder;

public class ChatHandler {

	private PrintWriter out;
	private InputStream in;

	/**
	 * Constructor of the ChatHandler class. Connects to a specified socket and
	 * creates a thread to handle data.
	 * 
	 * @param host
	 * @param port
	 * @throws IOException
	 */

	ChatHandler(String host, int port) throws IOException {

		Socket connection;
		Thread inputStreamListener;

		connection = new Socket(host, port);

		this.out = new PrintWriter(connection.getOutputStream(), true);
		this.in = new InputStream(connection);

		inputStreamListener = new Thread(this.in);
		inputStreamListener.start();

	}
	
	/**
	 * Sends a base-64 encoded message to the server.
	 * 
	 * @param name
	 * @param message
	 */

	public void sendChat(String name, String message) {

		String encodedMessage = String.format("%s,%s",
				Base64Coder.encodeString(name),
				Base64Coder.encodeString(message));

		this.out.println(encodedMessage);
	}
}
