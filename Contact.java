class Contact
{
    private String name;
    private String phone;

    public Contact(String name, String phone)
        throws InvalidContactException{

        if (!validateName(name)) 
        {
            throw new InvalidContactException("Invalid name!");
        }

        if (!validatePhone(phone)) 
        {
            throw new InvalidContactException("Invalid phone number!");
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
                System.out.println("Numbers are not allowed in names");
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
        throws InvalidContactException{
        
        if (!validateName(name))
        {
            throw new InvalidContactException("Invalid name!");
        }

        this.name = name;
    }

    public void setPhone(String phone)
        throws InvalidContactException{

        if (!validatePhone(phone))
        {
            throw new InvalidContactException("Invalid phone!");
        }

        this.phone = phone;
    }
}