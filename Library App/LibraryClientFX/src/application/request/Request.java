package application.request;

import java.io.Serializable;

public class Request implements Serializable {
	
	private static final long serialVersionUID = 1598682756211665304L;
	
	private String type;
	private Object payload;
	
	public String getType() {
		return type;
	}
	public Object getPayload() {
		return payload;
	}
	
	public Request(String type, Object payload) {
		this.payload = payload;
		this.type = type;
	}
	
	public Request(String type) {
		this.type = type;
	}
	
}
