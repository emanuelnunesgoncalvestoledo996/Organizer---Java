package exceptions;

public class ContactNotFoundException extends Exception
{
    public ContactNotFoundException(String message)
    {
        super(message);
    }    
}
