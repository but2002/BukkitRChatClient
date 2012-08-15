package me.barrytatum.BukkitRChatClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

public class InputStream implements Runnable {

	private BufferedReader in;

	InputStream(Socket connection) throws IOException {
		this.in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
	}

	public void run() {

		String message;

		for (;;) {
			try {
				while ((message = this.in.readLine()) != null) {
					RChatClient.chatBox.append(message + "\n");
					System.out.println(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

	}

}
