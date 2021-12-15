package main.java;

import java.io.IOException;

import main.java.config.ServerConfiguration;
import main.java.exceptions.BookDetailsException;
import main.java.server.LibraryServerSocket;

public class ServerMain {
	public static void main(String[] args) throws ClassNotFoundException, IOException, BookDetailsException {
		LibraryServerSocket serverSocket = new LibraryServerSocket(ServerConfiguration.PORT);
		Thread server = new Thread(serverSocket);
		server.run();
	}
}
