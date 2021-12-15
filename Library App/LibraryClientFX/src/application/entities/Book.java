package application.entities;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
	
	private static final long serialVersionUID = -1699596749815957383L;
	
	private Long id;
	private String title;
	private List<Author> authors;
	private String publisher;
	private int publicationYear;
	private int numberOfPages;
	private List<Review> reviews;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public String getDetails() {
		String endOfLine = "\n";
		String authors = "";
		for(Author author: this.authors) {
			authors = authors.concat(author.getName()).concat(" ");
		}
		String details = "Title: " + this.title + endOfLine 
					   + (this.authors.size() > 1 ? "Authors: " : "Author: ") + authors + endOfLine
					   + "Publisher: " + this.publisher + endOfLine
					   + "Publication year: " + Integer.toString(this.publicationYear) + endOfLine
					   + "Number of pages: " + Integer.toString(this.numberOfPages) + endOfLine
					   + "Number of reviews: " + this.reviews.size() + endOfLine;
		return details;
	}
	
	@Override
	public String toString() {
		String bookAsString = this.title.concat(", ");
		for(Author author: authors) {
			bookAsString = bookAsString.concat(author.getName()).concat(" ");
		}
		bookAsString = bookAsString.concat("(").concat(Integer.toString(publicationYear)).concat(")");
		return bookAsString;
	}
	
}
