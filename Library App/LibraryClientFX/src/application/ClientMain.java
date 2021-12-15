package application;

import java.io.IOException;

import application.config.ClientConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ClientMain extends Application {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Image appIcon = new Image("./resources/icon.png");
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        Scene scene = new Scene(root);
        
		primaryStage.setTitle(ClientConfiguration.APP_NAME);
        primaryStage.getIcons().add(appIcon);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
}
