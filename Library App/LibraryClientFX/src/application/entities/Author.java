package application.entities;

import java.io.Serializable;
import java.util.List;

public class Author implements Serializable {
	
	private static final long serialVersionUID = 6116836960267176590L;
	
	private Long id;
	private String name;
	private List<Book> books;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
}
