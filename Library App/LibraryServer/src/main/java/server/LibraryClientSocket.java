package main.java.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import main.java.exceptions.BookDetailsException;

public class LibraryClientSocket implements Runnable {

	private Socket socket;
	private ObjectInputStream dataInputStream;
	private ObjectOutputStream dataOutputStream;
	private LibraryServerSocket serverSocket;
	private String id;

	public LibraryClientSocket(Socket socket, LibraryServerSocket serverSocket) {
		this.socket = socket;
		this.serverSocket = serverSocket;
		id = socket.getInetAddress().getHostName() + "_" + socket.getInetAddress().getHostAddress() + "_"
				+ socket.getPort();
		try {
			dataInputStream = new ObjectInputStream(socket.getInputStream());
			dataOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void closeSocket() {
		if (socket == null)
			return;
		try {
			if (!socket.isClosed()) {
				System.out.println("Try to close socket:" + id);
				socket.close();
				System.out.println("Socket was closed successfully!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			serverSocket.removeClient(this);
		}
	}

	public String getId() {
		return id;
	}

	@Override
	public void run() {
		System.out.println("New socketclient: " + id);
		serverSocket.addClient(this);
		while (socket != null && !socket.isClosed()) {
			try {
				String request = (String) dataInputStream.readObject();
				String response = serverSocket.processRequest(request);
				dataOutputStream.writeObject(response);
			} catch (ClassNotFoundException | IOException | BookDetailsException e) {
				e.printStackTrace();
				if (socket != null && !socket.isClosed()) {
					closeSocket();
				}
			}
		}
	}

}
