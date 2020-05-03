/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		URL resource = getClass().getResource("MatchGUI.fxml");
		Parent root = FXMLLoader.load(resource);	
		Scene scene = new Scene(root, 608, 464);
		
		primaryStage.setTitle("Song đấu 2");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
