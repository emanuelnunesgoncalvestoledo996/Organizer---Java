package com.emanuel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ManagementController {

    @FXML 
    private Label sectionTitleLabel;
    
    @FXML 
    private ListView<Object> recordsListView;
    private List<?> currentList;
    private String currentType;

    public void initData(String type, List<?> list) {
        this.currentType = type;
        this.currentList = list;
        this.sectionTitleLabel.setText(type);
        
        System.out.println("Loaded " + type + " management. Total records: " + list.size());
        
    }

    @FXML
    public void onBackToMenuClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}