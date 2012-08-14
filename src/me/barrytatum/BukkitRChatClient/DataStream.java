package me.barrytatum.BukkitRChatClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class DataStream implements Runnable {

	private Socket connection;
	private PrintWriter out;
	private String message;

	DataStream(Socket connection) throws IOException {
		
		this.connection = connection;
		this.out = new PrintWriter(this.connection.getOutputStream());
	}

	public void run() {
		
		for (;;) {
			while (this.message != null) {
				this.out.println(message);
				this.message = null;
			}
		}
	}
	
	public void send(String message) {
		this.message = message;
	}
}
