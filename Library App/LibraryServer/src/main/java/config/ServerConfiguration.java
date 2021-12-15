package main.java.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.hibernate.cfg.Configuration;

import main.java.persistence.entities.Author;
import main.java.persistence.entities.Book;
import main.java.persistence.entities.Review;

public class ServerConfiguration {
	public static final int PORT = 8080;
	
	public static Configuration get() {
		Configuration config = new Configuration();
    	config.addProperties(getServerProperties());
    	config.addAnnotatedClass(Book.class);
    	config.addAnnotatedClass(Author.class);
    	config.addAnnotatedClass(Review.class);
		return config;
	}
	
	private static Properties getServerProperties() {
		Properties properties = new Properties();
		Map.Entry<String, String> currentProperty;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(new File("./src/main/resources/config.txt")));
			String line = reader.readLine();
			while (line != null) {
				currentProperty = getProperty(line);
				properties.setProperty(currentProperty.getKey().trim(), currentProperty.getValue().trim());
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	private static Map.Entry<String, String> getProperty(String line) {
		int separatorIndex = line.indexOf('=');
		String key = line.substring(0, separatorIndex);
		String value = line.substring(separatorIndex+1, line.length());
		return Map.entry(key, value);
	}
}
