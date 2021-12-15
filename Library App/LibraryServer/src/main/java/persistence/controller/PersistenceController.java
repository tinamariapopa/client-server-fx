package main.java.persistence.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.java.config.ServerConfiguration;
import main.java.exceptions.BookDetailsException;
import main.java.persistence.entities.Author;
import main.java.persistence.entities.Book;

public class PersistenceController {
	
	private static PersistenceController instance;
	private SessionFactory sessionFactory;
	private Session session;
	
	private PersistenceController() {        
        try {
        	Configuration config = ServerConfiguration.get();
			sessionFactory = config.buildSessionFactory();
			session = sessionFactory.openSession();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	public static PersistenceController getInstance() {
		if(instance == null) {
			instance =  new PersistenceController();
		}
		return instance;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		return session;
	}
	
	public String saveBook(String[] requestDetails) throws BookDetailsException {
		if(requestDetails.length == 6) {
			Book book = new Book();
			book.setTitle(requestDetails[1]);
			book.setAuthors(Arrays.asList(checkIfAuthorExist(requestDetails[2], requestDetails[1])));
			book.setPublisher(requestDetails[3]);
			book.setPublicationYear(Integer.parseInt(requestDetails[4]));
			book.setNumberOfPages(Integer.parseInt(requestDetails[5]));
			try {
				session.beginTransaction();
				session.save(book);
				session.getTransaction().commit();
				return book.toString();
			} catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		} else {
			throw new BookDetailsException("Book couldn't be created. Some fileds are missing.");
		}
	}
	
	public boolean saveAuthor(Author author) {
		try {
			session.beginTransaction();
			session.save(author);
			session.getTransaction().commit();
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean saveBook(Book book) {
		try {
			session.beginTransaction();
			session.save(book);
			session.getTransaction().commit();
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public String getAllBooks(String[] requestDetails) throws BookDetailsException {
		if(requestDetails.length >= 1) {
			String query = requestDetails.length == 2 ? "SELECT b FROM Book b ORDER BY ".concat(requestDetails[1]).concat(" ASC") : "SELECT b FROM Book b";
			List<String> responseList =  session.createQuery(query, Book.class).getResultList().stream().map(book -> book.toString()).toList();
			return String.join("~", responseList);
		} else {
			throw new BookDetailsException("Couldn't get all books. BAD REQUEST!");
		}
	}
	
	private Author checkIfAuthorExist(String authorName, String title) {
		String query = "SELECT a FROM Author a WHERE name=".concat("'").concat(authorName).concat("'");
		Author author = null;
		try {
			author = session.createQuery(query, Author.class).getSingleResult();
		} catch (NoResultException e) {
			author = new Author();
			author.setName(authorName);
			saveAuthor(author);
			return author;
		}
		return author;
	}
	
}
