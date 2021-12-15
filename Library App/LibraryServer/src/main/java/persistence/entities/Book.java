package main.java.persistence.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", nullable = false)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	@ManyToMany
	@JoinTable(name="book_authors",
					joinColumns=@JoinColumn(name="book_id"),
					inverseJoinColumns=@JoinColumn(name="author_id"))
	private List<Author> authors;
	@Column(nullable = false)
	private String publisher;
	@Column(nullable = false)
	private int publicationYear;
	@Column(nullable = false)
	private int numberOfPages;
	@OneToMany(mappedBy = "book")
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
		bookAsString = bookAsString.concat(", ").concat(publisher);
		bookAsString = bookAsString.concat(" (").concat(Integer.toString(publicationYear)).concat(")");
		return bookAsString;
	}
	
	
}
