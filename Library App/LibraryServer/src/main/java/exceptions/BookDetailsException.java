package main.java.exceptions;

public class BookDetailsException extends Exception {
	private static final long serialVersionUID = 1492243119375094300L;

	public BookDetailsException() {
		super();
	}
	
	public BookDetailsException(String message) {
		super(message);
	}

}
