package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
	
	private Stage stage;
	private Parent root;
	
	public void loadMainScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void loadReaderScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ReaderScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void loadLibrarianScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LibrarianScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void loadAddBookScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("AddBookScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
		new RequestController();
	}
	
	public void loadAllBooksScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("AllBooksScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
		new RequestController();
	}
	
}
