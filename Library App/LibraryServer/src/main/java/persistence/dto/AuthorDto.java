package main.java.persistence.dto;

import java.io.Serializable;

import main.java.persistence.entities.Author;

public class AuthorDto implements Serializable {

	private static final long serialVersionUID = 4407140504524683431L;
	
	private Long id;
	private String name;
	
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
	
	public AuthorDto(Author author) {
		this.id = author.getId();
		this.name = author.getName();
	}

}
