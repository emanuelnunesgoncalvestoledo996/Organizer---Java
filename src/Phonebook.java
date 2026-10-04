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

    private void checkIndex(int idx)
        throws ContactNotFoundException{

        if (idx < 0 || idx >= contacts.size())
        {
            throw new ContactNotFoundException("Contact not found!");
        }
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

        checkIndex(idx);

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

    public void addContact(String name, String phone) 
        throws InvalidContactException, DuplicateContactException {

        Contact newContact = new Contact(name, phone);

        if (contacts.contains(newContact)) 
        {
            throw new DuplicateContactException("This contact already exists!");
        }
        
        contacts.add(newContact);
    }

    public void updateContact(int idx, String newName, String newPhone) 
        throws ContactNotFoundException, InvalidContactException, DuplicateContactException {
        
        checkIndex(idx);

        Contact newContact = new Contact(newName, newPhone);

        for (int i = 0; i < contacts.size(); i++) 
        {
            if (i != idx && contacts.get(i).equals(newContact))
            {
                throw new DuplicateContactException("This contact already exists!");
            }
        }

        contacts.set(idx, newContact);
    }

    public void removeContact(int index) 
        throws ContactNotFoundException {

        checkIndex(index);
        contacts.remove(index);
    }
}
