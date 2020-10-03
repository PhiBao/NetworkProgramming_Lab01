package Lab01c;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Client extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	Socket clientSocket = null;
	ChatRoom chatRoom;
	DataOutputStream outToServer;
	DataInputStream inFromServer;

	public static void main(String[] args) {
		new Client();
	}

	public Client() {
		// Connect to server
		try {
			clientSocket = new Socket("localhost", 8039);
			System.out.println("Client is connected to socket server!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		chatRoom = new ChatRoom("Client");
		chatRoom.btnSendMessage.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					// Gui tin nhan len cho Server
					String messege = chatRoom.textMessage.getText();
					outToServer = new DataOutputStream(clientSocket.getOutputStream());
					outToServer.writeUTF(messege);
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
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			inFromServer = new DataInputStream(clientSocket.getInputStream());
			while (true) {
				// Nhan du lieu tra ve tu server
				String messege = inFromServer.readUTF();
				JLabel labelMessege = new JLabel(messege, JLabel.RIGHT);
				labelMessege.setForeground(Color.BLUE);
				chatRoom.boardChat.add(labelMessege, chatRoom.cons);
				chatRoom.boardChat.validate();
				chatRoom.boardChat.repaint();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
