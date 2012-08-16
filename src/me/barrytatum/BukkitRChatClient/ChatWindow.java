package me.barrytatum.BukkitRChatClient;

/**
 * File:		RChatClient.java
 * Created:		8/12/2012
 * Modified:	8/16/2012
 * Author:		Barry Tatum, Blake Renton
 */

import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class ChatWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String TITLE = "Remote Chat Client for Bukkit";
	private final int WINDOW_HEIGHT = 400;
	private final int WINDOW_WIDTH = 400;

	private static TextField input;
	private static TextArea history;

	/**
	 * Constructor for Bukkit Remote Chat GUI.
	 */

	ChatWindow() {

		ChatWindow.input = new TextField();
		ChatWindow.history = new TextArea(null, 0, 0, 1);

		this.setTitle(TITLE);
		this.setResizable(false);

		// Main window configuration.
		// ----------------------------------------

		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);

		// Chat history.
		// ----------------------------------------

		history.setVisible(true);
		history.setBounds(5, 5, 385, 330);
		history.setEditable(false);
		history.setEnabled(true);
		this.add(history);

		// Input field.
		// ----------------------------------------

		input.setVisible(true);
		input.setBounds(5, 340, 385, 25);
		this.add(ChatWindow.input);

		// Final operations.
		// ----------------------------------------

		this.setVisible(true);

		// Determine what happens on key presses.
		// ----------------------------------------

		KeyListener eListen = new KeyListener() {

			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					if (input.getText().length() > 0) {
						sendChat("Blake", input.getText());
					}
				} // end if()
				
			} // end keyPressed()

			public void keyReleased(KeyEvent e) {}

			public void keyTyped(KeyEvent e) {}

		};

		input.addKeyListener(eListen);
	}

	/**
	 * Output a generic message to the chat history.
	 * 
	 * @param message
	 */

	public static void sendMessage(String message) {
		ChatWindow.history.append(String.format("%s\n", message));
	}

	/**
	 * Call the chat handler to send a chat message to the sever.
	 * 
	 * @param name
	 * @param message
	 */

	public static void sendChat(String name, String message) {
		ChatWindow.history.append(String.format("%s: %s\n", name, message));
		RChatClient.chInstance.sendChat(name, message);
		ChatWindow.input.setText(null);
	}

	/**
	 * Output a notice to the chat history.
	 * 
	 * @param message
	 */

	public static void logError(String message) {
		ChatWindow.history.append(String.format("Notice: %s\n", message));
	}
}
