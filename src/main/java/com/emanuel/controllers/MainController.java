package com.emanuel.controllers;

import com.emanuel.Contact; 
import com.emanuel.Appointment;
import com.emanuel.Organizer; 
import com.emanuel.CSVManager;
import com.emanuel.exceptions.DuplicateItemException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.List;

public class MainController 
{
    private static Organizer<Contact> contactOrganizer = new Organizer<>();
    private static Organizer<Appointment> appointmentOrganizer = new Organizer<>();

    private static CSVManager csvManager = new CSVManager();
    private static boolean isDataLoaded = false;

    @FXML
    public void initialize() {
        if (!isDataLoaded) {
            try 
            {
                for (Contact c : csvManager.loadContacts()) 
                {
                    contactOrganizer.add(c);
                }

                for (Appointment a : csvManager.loadAppointments()) 
                {
                    appointmentOrganizer.add(a);
                }
                
                isDataLoaded = true; 

                List<String> warnings = csvManager.getAndClearWarnings();
                if (!warnings.isEmpty())
                {
                    StringBuilder warningMessage = new StringBuilder("The following corrupted items were skipped and removed:\n\n");
                    for (String w : warnings)
                    {
                        warningMessage.append(" - ").append(w).append("\n");
                    }
                    showAlert("Warning: Corrupted Data Detected ", warningMessage.toString());
                }
                System.out.println("Data loaded successfully.");

            } 
            
            catch (DuplicateItemException e) 
            {
                System.out.println("Notice: Skipped duplicate item during load - " + e.getMessage());
                isDataLoaded = true;
            }
        }
    }

    @FXML
    public void onContactsButtonClick(ActionEvent event) 
    {
        openManagementView(event, "Contacts", contactOrganizer);
    }

    @FXML
    public void onAppointmentsButtonClick(ActionEvent event) 
    {
        openManagementView(event, "Appointments", appointmentOrganizer);
    }

    @FXML
    public void onExitButtonClick() 
    {
        System.out.println("Saving data.");

        csvManager.saveContacts(contactOrganizer.getAllItems());
        csvManager.saveAppointments(appointmentOrganizer.getAllItems());

        System.out.println("Data saved successfully. Exiting.");
        System.exit(0);
    }

    // Agora passamos o Organizer<?>
    private void openManagementView(ActionEvent event, String type, Organizer<?> organizer) 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/emanuel/ManagementView.fxml"));
            Parent root = loader.load();

            ManagementController controller = loader.getController();
            controller.initData(type, organizer);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}