package main.java.persistence.dto;

import java.io.Serializable;

import main.java.persistence.entities.Review;

public class ReviewDto implements Serializable {

	private static final long serialVersionUID = 8250302071736941099L;
	
	private Long id;
	private Integer stars;
	private String author;
	private String content;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getStars() {
		return stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public ReviewDto(Review review) {
		this.author = review.getAuthor();
		this.content = review.getContent();
		this.id = review.getId();
		this.stars = review.getStars();
	}

}
