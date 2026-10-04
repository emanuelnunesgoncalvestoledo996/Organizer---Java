public class Phonebook 
{
    private Contact [] contacts;
    private int size;
    
    public Phonebook(int capacity)
    {
        if (validadeCapacity(capacity))
        {
            contacts = new Contact[capacity];
        }
        else
        {
            throw new IllegalArgumentException("It's impossible creact a phonebook without capacity!");
        }
    }

    public Phonebook()
    {
        contacts = new Contact[1];
    }

    private boolean validadeCapacity(int capacity)
    {
        return capacity > 0;
    }

    
}
