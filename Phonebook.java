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
        size = 0;
    }

    public Phonebook()
    {
        contacts = new Contact[1];
        size = 0;
    }

    private boolean validadeCapacity(int capacity)
    {
        return capacity > 0;
    }

    public void addContact(String name, String phone)
    {
        if (this.size == contacts.length)
        {
            resizeArray();
        }

        contacts[this.size] = new Contact(name, phone);
        size++;
    }

    private void resizeArray()
    {
        Contact [] newArray = new Contact[contacts.length * 2];

        for (int i = 0; i < contacts.length; i++)
        {
            newArray[i] = contacts[i];
        }

        contacts = newArray;
    }
}
