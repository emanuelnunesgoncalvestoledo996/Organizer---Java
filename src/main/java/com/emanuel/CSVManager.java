package com.emanuel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVManager 
{
    private static final String FOLDER_PATH = System.getProperty("user.home") + File.separator + ".organizer";
    private static final String CONTACTS_FILE = FOLDER_PATH + File.separator + "contacts.csv";
    private static final String APPOINTMENTS_FILE = FOLDER_PATH + File.separator + "appointments.csv";

    public CSVManager()
    {
        File folder = new File(FOLDER_PATH);

        if (!folder.exists())
        {
            folder.mkdirs();
        }
    }

    public void saveContacts(List<Contact> contacts) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTACTS_FILE))) 
        {
            for (Contact c : contacts) 
            {
                writer.write(c.getName() + ";" + c.getPhone());
                writer.newLine();
            }
        } 
        
        catch (IOException e) 
        {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    public void saveAppointments(List<Appointment> appointments) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENTS_FILE))) 
        {
            for (Appointment a : appointments) 
            {
                writer.write(a.getTitle() + ";" + a.getDate() + ";" + a.getTime());
                writer.newLine();
            }
        } 
        
        catch (IOException e) 
        {
            System.out.println("Error saving appointments: " + e.getMessage());
        }
    }
}
