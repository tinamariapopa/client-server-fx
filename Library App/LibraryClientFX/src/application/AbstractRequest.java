package application;

import application.client.LibraryClient;

public abstract class AbstractRequest {
	
	protected LibraryClient client;
	
	public AbstractRequest() {
		client = LibraryClient.getInstance();
	}
	
}
