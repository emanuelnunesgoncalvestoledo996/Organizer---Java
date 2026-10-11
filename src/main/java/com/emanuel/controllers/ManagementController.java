package com.emanuel.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import com.emanuel.Contact;
import com.emanuel.Appointment;
import com.emanuel.Organizer;
import com.emanuel.Schedulable;
import com.emanuel.exceptions.DuplicateItemException;
import com.emanuel.exceptions.InvalidItemException;
import com.emanuel.exceptions.ItemNotFoundException;

public class ManagementController {

    @FXML 
    private Label sectionTitleLabel;
    
    @FXML 
    private ListView<Schedulable> recordsListView; 

    private Organizer<Schedulable> currentOrganizer; 
    private ObservableList<Schedulable> uiList; 
    private String currentType;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void initData(String type, Organizer<?> organizer) 
    {
        this.currentType = type;
        this.currentOrganizer = (Organizer<Schedulable>) (Organizer) organizer; 
        this.sectionTitleLabel.setText(type);
        
        this.uiList = FXCollections.observableArrayList(this.currentOrganizer.getAllItems());
        this.recordsListView.setItems(this.uiList);
    }

    private void refreshUI()
    {
        this.uiList.setAll(this.currentOrganizer.getAllItems());
    }

    private String askInput(String title, String content) 
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(content);
        
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null); 
    }

    @FXML
    public void onAddClick(ActionEvent event) 
    {
        if (currentType.equals("Contacts"))
        {
            String name = askInput("Add Contact", "Name:");
            if (name == null) return; 
            
            String phone = askInput("Add Contact", "Phone:");
            if (phone == null) return; 

            try 
            {
                Contact newContact = new Contact(name, phone);
                currentOrganizer.add(newContact); 
                refreshUI(); 
            } 
            
            catch (InvalidItemException | DuplicateItemException e) { 
                showAlert("Validation Error", e.getMessage());
            }
        } 
        else if (currentType.equals("Appointments"))
        {
            String title = askInput("Add Appointment", "Title:");
            if (title == null) return;

            String date = askInput("Add Appointment", "Date (e.g., DD/MM/YYYY):");
            if (date == null) return;

            String time = askInput("Add Appointment", "Time (e.g., HH:MM):");
            if (time == null) return;

            try 
            {
                Appointment newAppointment = new Appointment(title, date, time);
                currentOrganizer.add(newAppointment);
                refreshUI();
            } 
            
            catch (InvalidItemException | DuplicateItemException e) {
                showAlert("Validation Error", e.getMessage());
            }
        }
    }

    @FXML
    public void onRemoveClick(ActionEvent event) 
    {
        Schedulable selectedItem = recordsListView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) 
        {
            try {
                currentOrganizer.remove(selectedItem.getIdentifier());
                refreshUI();
            } catch (ItemNotFoundException e) {
                showAlert("Error", e.getMessage());
            }
        } 
        else
        {
            showAlert("No Selection", "Please select an item to remove.");
        }
    }

    @FXML
    public void onUpdateClick(ActionEvent event) 
    {
        Schedulable selectedItem = recordsListView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) 
        {
            if (currentType.equals("Contacts")) 
            {
                String name = askInput("Update Contact", "New Name:");
                if (name == null) return;
                
                String phone = askInput("Update Contact", "New Phone:");
                if (phone == null) return;

                try 
                {
                    Contact updatedContact = new Contact(name, phone);
                    currentOrganizer.update(selectedItem.getIdentifier(), updatedContact);
                    refreshUI();
                } 
                
                catch (InvalidItemException | ItemNotFoundException e) {
                    showAlert("Validation Error", e.getMessage());
                }
            } 
            else if (currentType.equals("Appointments")) 
            {
                String title = askInput("Update Appointment", "New Title:");
                if (title == null) return;
                
                String date = askInput("Update Appointment", "New Date:");
                if (date == null) return;
                
                String time = askInput("Update Appointment", "New Time:");
                if (time == null) return;

                try 
                {
                    Appointment updatedAppointment = new Appointment(title, date, time);
                    currentOrganizer.update(selectedItem.getIdentifier(), updatedAppointment);
                    refreshUI();
                } 
                
                catch (InvalidItemException | ItemNotFoundException e) {
                    showAlert("Validation Error", e.getMessage());
                }
            }
        } 
        else 
        {
            showAlert("No Selection", "Please select an item to update.");
        }
    }

    @FXML
    public void onPrintClick(ActionEvent event) 
    {
        if (currentOrganizer.getAllItems().isEmpty()) 
        {
            showAlert("List Empty", "The " + currentType.toLowerCase() + " list is empty.");
            return;
        }

        System.out.println("--- PRINTING " + currentType.toUpperCase() + " ---");

        for (Schedulable item : currentOrganizer.getAllItems()) 
        {
            System.out.println(item.getDetails());
        }

        showAlert("Print Successful", "Check your terminal for the printed list.");
    }

    private void showAlert(String title, String content) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void onBackToMenuClick(ActionEvent event) 
    {
        try 
        {
            Parent root = FXMLLoader.load(getClass().getResource("/com/emanuel/MainView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}