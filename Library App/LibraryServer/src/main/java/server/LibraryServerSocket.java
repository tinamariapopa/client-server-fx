package main.java.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

import main.java.enums.RequestType;
import main.java.exceptions.BookDetailsException;
import main.java.persistence.controller.PersistenceController;

public class LibraryServerSocket implements Runnable {

	private PersistenceController persistenceController;
	private ServerSocket serverSocket = null;
	private LibraryClientSocket clientSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Hashtable<String, LibraryClientSocket> clients;
	private boolean on = true;

	public LibraryServerSocket(int port) throws IOException {
		persistenceController = PersistenceController.getInstance();
		clients = new Hashtable<String, LibraryClientSocket>();
		serverSocket = new ServerSocket(port);
		System.out.println("Library server listen on port: " + port);
	}

	public void removeClient(LibraryClientSocket clientSocket) {
		if (clientSocket == null)
			return;
		if (clients.get(clientSocket.getId()) != null) {
			clients.remove(clientSocket.getId());
		} else if (this.clientSocket != null && this.clientSocket.getId() == clientSocket.getId()) {
			this.clientSocket = null;
		}
		System.out.println("Client:" + clientSocket.getId() + " was removed.Number of clients:" + clients.size());
	}
	
	public void addClient(LibraryClientSocket clientSocket){
		if(clientSocket==null)
			return;
		clients.put(clientSocket.getId(), clientSocket);
		System.out.println("Add client:"+clientSocket.getId()+".Number of clients:"+clients.size());
	}
	
	public String processRequest(String request) throws BookDetailsException, IOException {
		String[] requestDetails = request.split("~", -1);
		String requestType = requestDetails[0];
		String response;
		System.out.println("Server received: " + requestType);

		switch (RequestType.valueOf(requestType)) {
		case ADD_BOOK:
			response = persistenceController.saveBook(requestDetails);
			break;
		case ADD_BOOK_REVIEW:
			response = "NOT IMPLEMENTED!";
			break;
		case VIEW_ALL_BOOKS:
			response = persistenceController.getAllBooks(requestDetails);
			break;
		case VIEW_ALL_BOOKS_SORTED:
			response = persistenceController.getAllBooks(requestDetails);
			break;
		case VIEW_BOOK_HISTORY:
			response = "NOT IMPLEMENTED!";
			break;
		case STOP_SERVER:
			stop();
			return null;
		default:
			System.out.println("SERVER RECEIVED AN UNKNOWN REQUEST: " + request);
			response = "UNKNOWN REQUEST!";
			break;
		}
		return response;
	}
	
	private void stop() throws IOException {
		out.close();
		in.close();
		serverSocket.close();
		on = false;
	}

	@Override
	public void run() {
		while (on && serverSocket != null && !serverSocket.isClosed()) {
			try {
				Socket socket = serverSocket.accept();
				LibraryClientSocket clientSocket = new LibraryClientSocket(socket, this);
				Thread client = new Thread(clientSocket);
				client.setName(socket.getInetAddress().getHostAddress());
				client.start();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}

}
