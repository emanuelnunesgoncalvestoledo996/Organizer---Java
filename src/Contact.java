import exceptions.InvalidItemException;

public class Contact implements Schedulable
{
    private String name;
    private String phone;

    public Contact(String name, String phone)
        throws InvalidItemException{

        if (!validateName(name)) 
        {
            throw new InvalidItemException("Invalid name!");
        }

        if (!validatePhone(phone)) 
        {
            throw new InvalidItemException("Invalid phone number!");
        }

        this.name = name.trim();
        this.phone = phone.trim();
    }

    private boolean validateName(String name)
    {
        if (name == null)
        {
            return false;
        }
        for (int i = 0; i < name.length(); i++)
        {
            if (Character.isDigit(name.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    private boolean validatePhone(String phone)
    {
        if (phone == null || phone.trim().isEmpty()) 
        {
            return false;
        }

        String cleanPhone = phone.replaceAll("[\\s()-]", "");

        if (!cleanPhone.matches("\\d+")) 
        {
            return false;
        }

        return cleanPhone.length() >= 9;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setName(String name)
        throws InvalidItemException{
        
        if (!validateName(name))
        {
            throw new InvalidItemException("Error: Invalid name format!");
        }

        this.name = name;
    }

    public void setPhone(String phone)
        throws InvalidItemException{

        if (!validatePhone(phone))
        {
            throw new InvalidItemException("Error: Invalid phone format!");
        }

        this.phone = phone;
    }

    @Override
    public String toString() 
    {
        return "Name: " + this.name + " - Phone number: " + this.phone;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) 
        {
            return true;
        }

        if (!(obj instanceof Contact)) 
        {
            return false;
        }

        Contact other = (Contact) obj;

        return name.equalsIgnoreCase(other.name) && phone.equals(other.phone);
    }

    @Override
    public int hashCode() 
    {
        return 31 * name.toLowerCase().hashCode() + phone.hashCode();
    }

    @Override 
    public String getIdentifier()
    {
        return this.getName();
    }

    @Override 
    public String getDetails()
    {
        return this.toString();
    }
}