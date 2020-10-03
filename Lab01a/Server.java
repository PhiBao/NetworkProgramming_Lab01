package Lab01a;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	public static void main(String[] args) throws IOException {
		int openPort = 8099;

		try {
			// Tao 1 server socket tai cong 8099
			ServerSocket serverSocket = new ServerSocket(openPort);

			try {
				System.out.println("Server Open!");
				Socket socket = serverSocket.accept();
				// Nhan du lieu tu Client
				Scanner scanner = new Scanner(socket.getInputStream());
				String str = scanner.nextLine();
				String strUpper = str.toUpperCase();
				String strLower = str.toLowerCase();
				int count = str.length();
				// Gui ket qua ve cho Client
				PrintStream prStr = new PrintStream(socket.getOutputStream());
				prStr.println(strUpper);
				prStr.flush();
				prStr.println(strLower);
				prStr.flush();
				prStr.println(String.valueOf(count));
				// Dong ket noi
				socket.close();
				serverSocket.close();

			} catch (IOException e) {
				System.err.println(" Connection Error: " + e);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
