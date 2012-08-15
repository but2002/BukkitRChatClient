package me.barrytatum.BukkitRChatClient;

import java.io.IOException;
import java.net.Socket;

import biz.source_code.base64Coder.Base64Coder;

public class ChatClient {

	private Socket connection;
	private DataStream dataStream;

	ChatClient(String host, int port) throws IOException {

		this.connection = new Socket(host, port);
		this.dataStream = new DataStream(this.connection);
		Thread dataStreamThread = new Thread(this.dataStream);
		dataStreamThread.start();
	}

	public void sendChat(String name, String message) {

		String encodedMessage = String.format("%s,%s",
				Base64Coder.encodeString(name),
				Base64Coder.encodeString(message));
		this.dataStream.send(encodedMessage);
	}

}
