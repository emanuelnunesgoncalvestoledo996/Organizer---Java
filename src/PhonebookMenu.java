import java.util.Scanner;
import exceptions.ContactNotFoundException;
import exceptions.InvalidContactException;
import exceptions.DuplicateContactException;

public class PhonebookMenu 
{
    private Phonebook phonebook;

    public PhonebookMenu(Phonebook phonebook)
    {
        this.phonebook = phonebook;
    }

    private static int menu(Scanner input)
    {
        int option;

        do
        {
            System.out.println("\n========== PHONEBOOK ==========");
            System.out.println("1 - Add contact");
            System.out.println("2 - Print phonebook");
            System.out.println("3 - Search contact");
            System.out.println("4 - Print single contact");
            System.out.println("5 - Update contact");
            System.out.println("6 - Remove contact")
            System.out.println("0 - Quit");
            System.out.println("=============================");
    
            System.out.print("Choose an option: ");

            
            if (input.hasNextInt())
            {
                option = input.nextInt();
                input.nextLine();
                if (option < 0 || option > 5)
                {
                    System.out.println("Opção inválida");
                }
            }
            else
            {
                System.out.println("Digite um numero de 0 a 5!");
                input.nextLine();
                option = -1;
            }
        }
        while (option < 0 || option > 5);

        return option;
    }

    public void run(Scanner input)
    {
        int option;

        do
        {
            option = menu(input);

            switch(option)
            {
                case 1: addContactMenu(input); 
                    break;
                case 2: phonebook.printPhonebook(); 
                    break;
                case 3: searchContactMenu(input);
                    break;
                case 4: printContactMenu(input);
                    break;
                case 5: updateContactMenu(input);
                    break;
                case 6: removeContactMenu(input);
                    break;
                case 0: System.out.println("Closing Phonebook");
                    break;
            }
        } while (option != 0);

    }

    private void addContactMenu(Scanner input) 
    {
        System.out.print("Enter contact name: ");
        String name = input.nextLine();

        System.out.print("Enter contact phone: ");
        String phone = input.nextLine();

        try 
        {
            phonebook.addContact(name, phone);
            System.out.println("Contact added successfully!");
        } 
        
        catch (InvalidContactException | DuplicateContactException error) 
        {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private void searchContactMenu(Scanner input) 
    {
        System.out.print("Enter contact name: ");
        String name = input.nextLine();

        try 
        {
            Contact contact = phonebook.searchContact(name);
            System.out.println(contact);
        } 
        
        catch (ContactNotFoundException error) 
        {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
