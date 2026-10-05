public class Appointment implements Schedulable
{
    private String title;
    private String date;
    private String time;
    
    public Appointment(String title, String date, String time)
    {
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
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

}
