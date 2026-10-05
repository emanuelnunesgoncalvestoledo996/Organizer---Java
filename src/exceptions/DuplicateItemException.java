package exceptions;

public class DuplicateItemException extends Exception
{
    public DuplicateItemException(String message) 
    {
        super(message);
    }
}