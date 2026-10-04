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

    private boolean existsByPhone(String phone)
    {
        for (Contact contact : contacts)
        {
            if (contact.getPhone().equalsIgnoreCase(phone.trim()))
            {
                return true;
            }
        }

        return false;
    }

    private boolean existsByName(String name) 
    {
        for (Contact contact : contacts) 
        {
            if (contact.getName().equalsIgnoreCase(name.trim())) 
            {
                return true;
            }
        }

        return false;
    }
        
    public Contact searchContact(int idx)
        throws ContactNotFoundException {

        isValidIndex(idx);

        return contacts.get(idx);
    }

    public Contact searchContact(String name)
        throws ContactNotFoundException {

        for (Contact contact : contacts) 
        {
            if (contact.getName().equalsIgnoreCase(name.trim())) 
            {
                return contact;
            }
        }

        throw new ContactNotFoundException ("No contact found with the given name!!");
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
        if (idx < 0 || idx >= contacts.size())
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
        throws ContactNotFoundException{

        System.out.println(searchContact(idx));
    }
}
