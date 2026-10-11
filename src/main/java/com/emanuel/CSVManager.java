package com.emanuel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.emanuel.exceptions.InvalidItemException;

public class CSVManager 
{
    private static final String FOLDER_PATH = System.getProperty("user.home") + File.separator + ".organizer";
    private static final String CONTACTS_FILE = FOLDER_PATH + File.separator + "contacts.csv";
    private static final String APPOINTMENTS_FILE = FOLDER_PATH + File.separator + "appointments.csv";

    private List<String> loadWarnings;

    public CSVManager()
    {
        this.loadWarnings = new ArrayList<>();
        File folder = new File(FOLDER_PATH);

        if (!folder.exists())
        {
            folder.mkdirs();
        }
    }

    public List<String> getAndClearWarnings()
    {
        List<String> warningsToReturn = new ArrayList<>(this.loadWarnings);
        this.loadWarnings.clear();
        return warningsToReturn;
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

    public List<Contact> loadContacts() 
    {
        List<Contact> loadedContacts = new ArrayList<>();
        File file = new File(CONTACTS_FILE);
        
        if (!file.exists()) return loadedContacts; 

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(";"); 
                if (parts.length == 2) 
                {
                    try 
                    {
                        loadedContacts.add(new Contact(parts[0], parts[1]));
                    } 
                    
                    catch (InvalidItemException e) 
                    {
                        loadWarnings.add("Contact skipped: " + parts[0] + e.getMessage());
                    }
                }
            }
        } 
        
        catch (IOException e) 
        {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
        
        return loadedContacts;
    }

    public List<Appointment> loadAppointments() 
    {
        List<Appointment> loadedAppointments = new ArrayList<>();
        File file = new File(APPOINTMENTS_FILE);

        if (!file.exists()) return loadedAppointments;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(";");
                if (parts.length == 3) 
                {
                    try
                    {
                        loadedAppointments.add(new Appointment(parts[0], parts[1], parts[2]));
                    } 
                    
                    catch (InvalidItemException e)
                    {
                        loadWarnings.add("Appointment skipped: " + parts[0] + e.getMessage());
                    }
                }
            }
        } 
        
        catch (IOException e) 
        {
            System.out.println("Error loading appointments: " + e.getMessage());
        }
        
        return loadedAppointments;
    }
}
