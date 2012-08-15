package me.barrytatum.BukkitRChatClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import biz.source_code.base64Coder.Base64Coder;

public class ChatHandler {

	private Socket connection;
	private PrintWriter out;
	private InputStream in;

	ChatHandler(String host, int port) throws IOException {

		this.connection = new Socket(host, port);
		this.out = new PrintWriter(this.connection.getOutputStream(), true);
		
		this.in = new InputStream(this.connection);
		Thread inputStreamListener = new Thread(this.in);
		inputStreamListener.start();
	}

	public void sendChat(String name, String message) {

		String encodedMessage = String.format("%s,%s",
				Base64Coder.encodeString(name),
				Base64Coder.encodeString(message));

		this.out.println(encodedMessage);
	}
}
