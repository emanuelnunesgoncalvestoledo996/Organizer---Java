package com.emanuel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Personal Organizer");
        stage.setScene(scene);
        stage.show();
}
        
        // Label label = new Label("Hello, JavaFX! A interface gráfica está viva!");

        // StackPane root = new StackPane();
        // root.getChildren().add(label);

        // Scene scene = new Scene(root, 400, 300);

        // primaryStage.setTitle("Personal Organizer");
        // primaryStage.setScene(scene);
        // primaryStage.show();

    public static void main(String[] args) 
    {
        launch(args);
    }
}