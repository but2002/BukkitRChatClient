package me.barrytatum.BukkitRChatClient;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;

public class RChatClient extends JFrame {

	private static final long serialVersionUID = 1L;
	ChatHandler chatClient;
	public static TextArea chatBox = new TextArea("", 0, 0, 1);
	TextField sendBox = new TextField();

	public RChatClient() {
		this.setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);

		chatBox.setVisible(true);
		chatBox.setBounds(5, 5, 385, 330);
		chatBox.setEditable(false);
		chatBox.setEnabled(true);
		this.add(chatBox);

		sendBox.setVisible(true);
		sendBox.setBounds(5, 340, 385, 25);
		this.add(sendBox);

		this.setTitle("BukkitRChatClient");
		this.setResizable(false);
		this.setVisible(true);

		try {
			chatClient = new ChatHandler("localhost", 5956);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		KeyListener eListen = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					chatClient.sendChat("TEST", sendBox.getText());
					chatBox.append("TEST: " + sendBox.getText() + "\n");
					sendBox.setText(null);
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		};
		sendBox.addKeyListener(eListen);

	}

	public static void main(String[] args) {
		new RChatClient();

	}

}
