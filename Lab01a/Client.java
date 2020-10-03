package Lab01a;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException, IOException {
		int openPort = 8099;

		Scanner scanner = new Scanner(System.in);
		System.out.println("This is client side. Code by Tran Mua Phi Bao.");

		try {
			// Connect to server
			Socket clientSocket = new Socket("localhost", openPort);
			System.out.println("Client is connected to socket server!");
			Scanner socketInput = new Scanner(clientSocket.getInputStream());
			System.out.println("Please enter a number: ");
			String myStr;
			myStr = scanner.nextLine();
			// Truyen du lieu len cho server
			PrintStream p = new PrintStream(clientSocket.getOutputStream());
			p.println(myStr);
			// Nhan du lieu tra ve tu server
			while (socketInput.hasNextLine()) {
				String temp = null;
				temp = socketInput.nextLine();
				System.out.println("Output data from Socket Server: " + temp);
			}
		} catch (ConnectException e) {
			e.printStackTrace();
		}
	}
}
