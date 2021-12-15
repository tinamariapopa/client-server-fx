package main.java.server;

import java.io.Serializable;

import main.java.enums.RequestType;

public class ServerRequest implements Serializable {
	
	private static final long serialVersionUID = 5897622705323770332L;
	
	private RequestType type;
	private Object payload;
	
	public RequestType getType() {
		return type;
	}
	public Object getPayload() {
		return payload;
	}
	
	public ServerRequest(Object payload) {
		this.payload = payload;
	}
	
}
