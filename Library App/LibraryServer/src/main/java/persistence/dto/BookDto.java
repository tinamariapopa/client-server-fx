package main.java.persistence.dto;

import java.io.Serializable;
import java.util.List;

import main.java.persistence.entities.Book;

public class BookDto implements Serializable {

	private static final long serialVersionUID = -324281448489565184L;
	
	private Long id;
	private String title;
	private List<AuthorDto> authors;
	private String publisher;
	private int publicationYear;
	private int numberOfPages;
	private List<ReviewDto> reviews;
	
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
	public List<AuthorDto> getAuthors() {
		return authors;
	}
	public void setAuthors(List<AuthorDto> authors) {
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
	public List<ReviewDto> getReviews() {
		return reviews;
	}
	public void setReviews(List<ReviewDto> reviews) {
		this.reviews = reviews;
	}
	
	public BookDto(Book book) {
		this.authors = book.getAuthors().stream().map(author -> new AuthorDto(author)).toList();
		this.id = book.getId();
		this.numberOfPages = book.getNumberOfPages();
		this.publicationYear = book.getPublicationYear();
		this.publisher = book.getPublisher();
		this.title = book.getTitle();
		this.reviews = book.getReviews().stream().map(review -> new ReviewDto(review)).toList();
	}

}
