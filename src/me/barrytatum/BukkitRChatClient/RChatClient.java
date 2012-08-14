package me.barrytatum.BukkitRChatClient;

import java.awt.*;

import javax.swing.*;

public class RChatClient extends JFrame {

	private static final long serialVersionUID = 1L;
	TextArea chatBox = new TextArea("", 0, 0, 1);
	TextField sendBox = new TextField();

	public RChatClient() {
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		chatBox.setVisible(true);
		chatBox.setBounds(5, 5, 385, 330);
		chatBox.setEditable(false);
		chatBox.setEnabled(true);
		add(chatBox);

		sendBox.setVisible(true);
		sendBox.setBounds(5, 340, 385, 25);
		add(sendBox);

		setTitle("BukkitRChatClient");
		this.setResizable(false);
		setVisible(true);

	}

	public static void main(String[] args) {
		new RChatClient();

	}

}