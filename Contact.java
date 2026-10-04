class Contact
{
    private String name;
    private String phone;

    public Contact(String name, String phone)
    {
        if (validateName(name) && validatePhone(phone))
        {
            setName(name);;
            setPhone(phone);;
        }
        else
        {
            throw new IllegalArgumentException("Invalid name or phone number!");
        }
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
                System.out.println("Numbers are not allowed in names");
                return false;
            }
        }

        return true;
    }

    private boolean validatePhone(String phone)
    {
        if (phone == null)
        {
                return false;
        }
            
        if (phone.length() < 9)
        {
                System.out.println("A number must have at least 9 digits.");
                return false;
        }

        return true;
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
    {
        this.name = name;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}