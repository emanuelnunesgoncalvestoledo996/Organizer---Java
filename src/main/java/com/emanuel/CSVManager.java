package com.emanuel;

import java.io.File;

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
}
