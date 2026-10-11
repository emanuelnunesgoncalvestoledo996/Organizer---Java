package com.emanuel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController 
{

    private static List<Contact> contactsList = new ArrayList<>();
    private static List<Appointment> appointmentsList = new ArrayList<>();

    @FXML
    public void onContactsButtonClick(ActionEvent event) {
        openManagementView(event, "Contacts", contactsList);
    }

    @FXML
    public void onAppointmentsButtonClick(ActionEvent event) {
        openManagementView(event, "Appointments", appointmentsList);
    }

    @FXML
    public void onExitButtonClick() {
        System.out.println("Saving data (simulated) and exiting...");
        System.exit(0);
    }

    private void openManagementView(ActionEvent event, String type, List<?> list) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagementView.fxml"));
            Parent root = loader.load();

            ManagementController controller = loader.getController();
            controller.initData(type, list);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}