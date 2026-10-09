package com.emanuel;

import javafx.fxml.FXML;

public class MainController 
{

    @FXML 
    public void onTasksButtonClick()
    {
        System.out.println("Navigating to Tasks view");
    }

    @FXML 
    public void onCalendarButtonClick()
    {
        System.out.println("Navigating to Calendar view");
    }

    @FXML 
    public void onNotesButtonClick()
    {
        System.out.println("Navigating to Notes view");
    }

}
