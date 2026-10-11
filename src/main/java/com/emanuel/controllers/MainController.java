package com.emanuel.controllers;

import com.emanuel.Contact; 
import com.emanuel.Appointment;
import com.emanuel.Organizer; // Importe o Organizer!

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController 
{
    private static Organizer<Contact> contactOrganizer = new Organizer<>();
    private static Organizer<Appointment> appointmentOrganizer = new Organizer<>();

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
        System.out.println("Saving data (simulated) and exiting...");
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
}