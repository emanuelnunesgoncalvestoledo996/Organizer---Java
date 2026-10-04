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

    private boolean isValidIndex(int idx)
    {
        if (idx < 0 || idx >= this.size)
        {
            return false;
        }
        return true;
    }
    
    public void updateContact(String name, String phone, int idx)
    {
        if (isValidIndex(idx))
        {
            contacts[idx].setName(name);
            contacts[idx].setPhone(phone);
        }
        else
        {
            System.out.println("It's impossible to modify a contact that does not exist.");
        }
    } 

    public void removeContact(int idx)
    {
        if (isValidIndex(idx))
        {
            for (int i = idx; i < this.size - 1; i++)
            {
                contacts[i] = contacts[i+1];
            }
            contacts[size - 1] = null;
            size--;
        }

        else
        {
            System.out.println("It's impossible to remove a contact that does not exist.");
        }
    }

    public void printPhonebook()
    {
        for (int i = 0; i < this.size; i++)
        {
            System.out.println((i+1) + " - Nome: " + contacts[i].getName() + " - Telefone: " + contacts[i].getPhone());
        }
    }

    public void printContact(int idx)
    {
        if (isValidIndex(idx))
        {
            System.out.println("Nome: " + contacts[idx].getName() + " - Telefone: " + contacts[idx].getPhone());
        }
    }
}
