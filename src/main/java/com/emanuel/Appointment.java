package com.emanuel;
import java.util.Objects;
import com.emanuel.exceptions.InvalidItemException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Appointment implements Schedulable
{
    private String title;
    private String date;
    private String time;
    
    public Appointment(String title, String date, String time)
        throws InvalidItemException{
        
        setTitle(title);
        setDate(date);
        setTime(time);
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
        throws InvalidItemException{

        if (title == null || title.trim().isEmpty())
        {
            throw new InvalidItemException("Error: Title cannot be empty");
        }
        
        this.title = title;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date) 
        throws InvalidItemException {
        
        if (date == null || !date.matches("\\d{2}/\\d{2}/\\d{4}")) 
        {
            throw new InvalidItemException("Error: Invalid date format. Use DD/MM/YYYY.");
        }

        try 
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            LocalDate appointmentDate = LocalDate.parse(date, formatter);
            
            LocalDate today = LocalDate.now();
            
            if (appointmentDate.isBefore(today)) 
            {
                throw new InvalidItemException("Error: The appointment date cannot be in the past.");
            }
            
        } 
        
        catch (DateTimeParseException e) 
        {
            throw new InvalidItemException("Error: Invalid date values (e.g., day or month out of range).");
        }

        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
        throws InvalidItemException{

        if (time == null || !time.matches("([01]\\d|2[0-3]):[0-5]\\d"))
        {
            throw new InvalidItemException("Error: Invalid time format or out of bounds. Use HH:MM (00:00 to 23:59).");
        }

        this.time = time;        
    }

    @Override 
    public String getIdentifier()
    {
        return this.title;
    }

    @Override 
    public String getDetails()
    {
        return this.toString();
    }

    @Override 
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof Appointment))
        {
            return true;
        }

        Appointment other = (Appointment) obj;

        return this.date.equals(other.date) && this.time.equals(other.time);
    }

    @Override 
    public int hashCode()
    {
        return Objects.hash(date,time);
    }

    @Override 
    public String toString()
    {
        return "Appointment: " + this.title + " | Date: " + this.date + " | Time: " + this.time;
    }
}
