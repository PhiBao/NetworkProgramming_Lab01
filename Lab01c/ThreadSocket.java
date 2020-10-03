package Lab01c;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JLabel;

public class ThreadSocket extends Thread {

	Socket serverSocket = null;
	ChatRoom chatRoom;
	DataOutputStream outToClient;
	DataInputStream inFromClient;

	public ThreadSocket(Socket socket) {
		this.serverSocket = socket;
		chatRoom = new ChatRoom("Server");
		chatRoom.btnSendMessage.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					// Gui tin nhan ve cho Client
					String messege = chatRoom.textMessage.getText();
					outToClient = new DataOutputStream(serverSocket.getOutputStream());
					outToClient.writeUTF(messege);
					chatRoom.textMessage.setText("");
					JLabel labelMessege = new JLabel(messege, JLabel.LEFT);
					labelMessege.setForeground(Color.BLACK);
					chatRoom.boardChat.add(labelMessege, chatRoom.cons);
					chatRoom.boardChat.validate();
					chatRoom.boardChat.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void run() {
		try {
			inFromClient = new DataInputStream(serverSocket.getInputStream());
			while (true) {
				// Nhan du lieu tu Client
				String messege = inFromClient.readUTF();
				JLabel labelMessege = new JLabel(messege, JLabel.RIGHT);
				labelMessege.setForeground(Color.BLUE);
				chatRoom.boardChat.add(labelMessege, chatRoom.cons);
				chatRoom.boardChat.validate();
				chatRoom.boardChat.repaint();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
