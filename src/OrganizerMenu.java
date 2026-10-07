import java.util.Scanner;
import exceptions.ItemNotFoundException;
import exceptions.InvalidItemException;
import exceptions.DuplicateItemException;

public class OrganizerMenu 
{
    private Organizer <Contact> contactOrganizer;
    private Organizer <Appointment> appointmentOrganizer;
    private Scanner scanner;

    public OrganizerMenu(Organizer <Contact> contactOrganizer, Organizer <Appointment> appointmentOrganizer)
    {
        this.contactOrganizer = contactOrganizer;
        this.appointmentOrganizer = appointmentOrganizer;
        this.scanner = new Scanner(System.in);
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
            System.out.println("6 - Remove contact");
            System.out.println("0 - Quit");
            System.out.println("=============================");
    
            System.out.print("Choose an option: ");

            
            if (input.hasNextInt())
            {
                option = input.nextInt();
                input.nextLine();
                if (option < 0 || option > 6)
                {
                    System.out.println("Invalid option");
                }
            }
            else
            {
                System.out.println("Enter a number from 0 to 6!");
                input.nextLine();
                option = -1;
            }
        }
        while (option < 0 || option > 6);

        return option;
    }

    public void start()
    {
        int option = 0;
        do
        {
            System.out.println("\n=== ORGANIZER MAIN MENU ===");
            System.out.println("1. Manage Contacts");
            System.out.println("2. Manage Appointments");
            System.out.println("3. Exit");
            System.out.print("Choose an option");

            try 
            {
                option = Integer.parseInt(scanner.nextLine());

                switch (option)
                {
                    case 1:
                        manageContacts();
                        break;
                    case 2:
                        manageAppointments();
                        break;
                    case 3:
                        System.out.println("Closing organizer...");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                } 
            }

            catch (NumberFormatException e)
            {
                System.out.println("Error. Please enter a valid number.");
            }
        } while (option != 3);
    }

    private void manageContacts()
    {
        System.out.println("\n --- CONTACTS MENU ---");
        System.out.println("1. Add Contact");
        System.out.println("2. List all Contacts");
        System.out.println("3. Return to Main Menu");
    }

    private void manageAppointments()
    {
        System.out.println("\n --- APPOINTMENT MENU ---");
        System.out.println("1. Add Appointment");
        System.out.println("2. List all Appointments");
        System.out.println("3. Return to Main Menu");
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
        
        catch (InvalidItemException | DuplicateContactException error) 
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
        
        catch (ItemNotFoundException error) 
        {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private void printContactMenu(Scanner input) {

        System.out.print("Enter contact number: ");

        if (!input.hasNextInt())
        {
            System.out.println("Invalid input!");
            input.nextLine();
            return;
        }

        int idx = input.nextInt();
        input.nextLine();

        idx--;

        try 
        {
            phonebook.printContact(idx);
        } 

        catch (ItemNotFoundException error) 
        {
            System.out.println("Error: " + error.getMessage());
        }
    }


    private void updateContactMenu(Scanner input) 
    {
        System.out.print("Enter contact number: ");

        if (!input.hasNextInt())
        {
            System.out.println("Invalid input!");
            input.nextLine();
            return;
        }

        int idx = input.nextInt();
        input.nextLine();

        idx--;

        System.out.print("Enter the new name: ");
        String newName = input.nextLine();

        System.out.print("Enter the new phone number: ");
        String newPhone = input.nextLine();

        try 
        {
            phonebook.updateContact(idx,newName,newPhone);
            System.out.println("Contact updated succesfully!");

        }

        catch (ItemNotFoundException | InvalidItemException | DuplicateContactException error)
        {
            System.out.println("Error: " + error.getMessage());
        }
    }

    
    private void removeContactMenu(Scanner input) 
    {
        System.out.print("Enter contact number: ");

        if (!input.hasNextInt()) 
        {
            System.out.println("Invalid input!");
            input.nextLine();

            return;
        }

        int idx = input.nextInt();
        input.nextLine();

        idx--;

        try 
        {
            phonebook.removeContact(idx);
            System.out.println("Contact removed succesfully!");
        } 

        catch (ItemNotFoundException error) 
        {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
