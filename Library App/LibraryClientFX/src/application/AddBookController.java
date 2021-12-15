package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.client.LibraryClient;
import application.config.ClientConfiguration;
import application.enums.RequestType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController implements Initializable {
	
	private LibraryClient client;
	
	@FXML
	private TextField titleTextField;
	
	@FXML
	private TextField authorsTextField;
	
	@FXML
	private TextField publicationYearTextField;
	
	@FXML
	private TextField publisherTextField;
	
	@FXML
	private TextField numberOfPagesTextField;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		client = LibraryClient.getInstance();
		try {
			client.start(ClientConfiguration.IP_ADDRESS, ClientConfiguration.PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadPreviousScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LibrarianScene.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void addBook(ActionEvent event) throws ClassNotFoundException, IOException {
		String separator = "~";
		String request =  RequestType.ADD_BOOK.getType() + separator;
		request = request 
				+ this.titleTextField.getText() + separator
				+ this.authorsTextField.getText() + separator
				+ this.publisherTextField.getText() + separator
				+ this.publicationYearTextField.getText() + separator
				+ this.numberOfPagesTextField.getText();
		client.sendRequest(request);
		emptyTextFields();
	}
	
	private void emptyTextFields() {
		this.authorsTextField.setText("");
		this.numberOfPagesTextField.setText("");
		this.publicationYearTextField.setText("");
		this.publisherTextField.setText("");
		this.titleTextField.setText("");
	}
	
}
