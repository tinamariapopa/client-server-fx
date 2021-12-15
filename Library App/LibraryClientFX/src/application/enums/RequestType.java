package application.enums;

public enum RequestType {
	ADD_BOOK("ADD_BOOK"),
	ADD_BOOK_REVIEW("ADD_BOOK_REVIEW"),
	STOP_SERVER("STOP_SERVER"),
	VIEW_ALL_BOOKS("VIEW_ALL_BOOKS"),
	VIEW_ALL_BOOKS_SORTED("VIEW_ALL_BOOKS_SORTED"),
	VIEW_BOOK_HISTORY("VIEW_BOOK_HISTORY");
	
	
	private final String type;
	
	RequestType(final String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}