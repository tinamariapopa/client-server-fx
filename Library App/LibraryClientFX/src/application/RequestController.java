package application;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import application.client.LibraryClient;
import application.config.ClientConfiguration;
import application.enums.RequestType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class RequestController implements Initializable {
	
	private LibraryClient client;
	private String currentBook;
	private final String[] sortingOptions = {"default", "title", "publisher", "publication year"};
	
	@FXML
	private ListView<String> allBooksList;
	
	@FXML
	private ChoiceBox<String> sortingDropdown;
	
	@FXML
	private TextArea currentBookDetails;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		client = LibraryClient.getInstance();
		try {
			client.start(ClientConfiguration.IP_ADDRESS, ClientConfiguration.PORT);
			getAllBooks();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		sortingDropdown.getItems().addAll(sortingOptions);
		sortingDropdown.setValue(sortingOptions[0]);
		sortingDropdown.setOnAction(arg01 -> {
			try {
				sortBooks(arg01);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		});
		
		allBooksList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				currentBook = allBooksList.getSelectionModel().getSelectedItem();
				currentBookDetails.setText(currentBook);
			}
			
		});
	}
	
	public void getAllBooks() throws ClassNotFoundException, IOException {
		List<String> response = Arrays.asList(((String)client.sendRequest(RequestType.VIEW_ALL_BOOKS.getType())).split("~", -1));
		if(response.size() > 0) {
			currentBook = response.get(0);
			currentBookDetails.setText(currentBook);
		}
		for(String book: response) {
			allBooksList.getItems().add(book);
		}
	}
	
	public void sortBooks(ActionEvent event) throws ClassNotFoundException, IOException {
		String sort = sortingDropdown.getValue().replaceAll("\\s+","");
		if(sort != "default") {
			String separator = "~";
			List<String> response = Arrays.asList(((String)client.sendRequest(RequestType.VIEW_ALL_BOOKS_SORTED.getType() + separator + sort)).split("~", -1));
			if(response.size() > 0) {
				currentBook = response.get(0);
				currentBookDetails.setText(currentBook);
				allBooksList.getItems().clear();
			}
			for(String book: response) {
				allBooksList.getItems().add(book);
			}
		}
	}
	
	public void loadPreviousScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ReaderScene.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
}
