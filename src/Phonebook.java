import java.util.ArrayList;
import java.util.List;
import exceptions.InvalidContactException;
import exceptions.DuplicateContactException;
import exceptions.ContactNotFoundException;

public class Phonebook 
{
    private List<Contact> contacts;

    public Phonebook()
    {
        contacts = new ArrayList<>();
    }

    public int getSize()
    {
        return contacts.size();
    }

    public boolean isEmpty()
    {
        return contacts.isEmpty();
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
        if (contacts.isEmpty()) 
        {
            System.out.println("A agenda está vazia.");
            return;
        }

        for (int i = 0; i < contacts.size(); i++) 
        {
            System.out.println((i + 1) + " - " + contacts.get(i));
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
