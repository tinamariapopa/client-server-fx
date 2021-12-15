package application.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LibraryClient {
	
	private static LibraryClient instance;
	private Socket clientSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private LibraryClient() {}
	
	public static LibraryClient getInstance() {
		if(instance == null) {
			instance = new LibraryClient();
		}
		return instance;
	}
	
	public void start(String ipAddress, int port) throws IOException {
		if(clientSocket == null) {
			clientSocket = new Socket(ipAddress, port);
			System.out.println("Library client started! [IP ADDRESS: " + ipAddress + " | PORT: " + port + "]");
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
		}
	}
	
	public void stop() throws IOException {
		out.close();
		in.close();
		clientSocket.close();
	}
	
	public String sendRequest(String request) throws IOException, ClassNotFoundException {
		out.writeObject(request);
		String response = (String) in.readObject();
		return response;
	}

	
}
