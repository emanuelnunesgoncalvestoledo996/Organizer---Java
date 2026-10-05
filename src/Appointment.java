import java.util.Objects;
import exceptions.InvalidItemException;


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

        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
        throws InvalidItemException{

        if (date == null || !time.matches("\\d{2}:\\d{2}"))
        {
            throw new InvalidItemException("Error: Invalid time format. Use HH:MM");
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
