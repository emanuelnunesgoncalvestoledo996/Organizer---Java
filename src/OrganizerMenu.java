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
        int option = 0;

        do
        {
            System.out.println("\n --- CONTACTS MENU ---");
            System.out.println("1. Add Contact");
            System.out.println("2. List all Contacts");
            System.out.println("3. Remove Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Return to Main Menu");
            System.out.print("Choose an option");
            
            try
            {
                option = Integer.parseInt(scanner.nextLine());

                switch (option)
                {
                    case 1:
                        addContactFlow();
                        break;
                    case 2:
                        listContactFlow();
                        break;
                    case 3:
                        removeContactFlow();
                        break;
                    case 4:
                        updateContactFlow();
                        break;
                    case 5:
                        System.out.println("Returning to Main Menu");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }

            catch (NumberFormatException e)
            {
                System.out.println("Error: Please enter a valid number.");
            }
        } while (option != 5);
    }

    private void addContactFlow()
    {
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine();

        System.out.print("Enter contact phone number: ");
        String phone = scanner.nextLine();

        try 
        {
            Contact newContact = new Contact(name, phone);

            contactOrganizer.add(newContact);

            System.out.println("Success: Contact added");
        }

        catch (InvalidItemException | DuplicateItemException e)
        {
           System.out.println(e.getMessage());
        }
    }

    private void listContactFlow()
    {
        System.out.println("\n --- Contact List ---");

        if (contactOrganizer.getAllItems().isEmpty())
        {
            System.out.println("The contact list is empty.");
            return;
        }

        for (Contact contact : contactOrganizer.getAllItems())
        {
            System.out.println(contact.getDetails());
        }
    }

    private void removeContactFlow()
    {
        System.out.print("Enter the name of the contact you wish to remove: ");
        String name = scanner.nextLine();

        try
        {
            contactOrganizer.remove(name);
            System.out.println("Success: Contact removed");
        }

        catch (ItemNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void updateContactFlow()
    {
        System.out.print("Enter the current name of the contact you wish to update: ");
        String currentName = scanner.nextLine();

        System.out.print("Enter the new contact name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter the new phone number contact: ");
        String newPhone = scanner.nextLine();

        try
        {
            Contact updatedContact = new Contact(newName, newPhone);

            contactOrganizer.update(currentName, updatedContact);

            System.out.println("Success: Contact updated");
        }

        catch (ItemNotFoundException | InvalidItemException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void manageAppointments()
    {
        int option = 0;
        do
        {
            System.out.println("1. Add Appointment");
            System.out.println("2. List all Appointments");
            System.out.println("3. Remove Appointment");
            System.out.println("4. Update Appointment");
            System.out.println("5. Return to Main Menu");
            System.out.print("Choose an option: ");

            try
            {
                option = Integer.parseInt(scanner.nextLine());

                switch (option)
                {
                    case 1: 
                        addAppointmentFlow();
                        break;
                    case 2:
                        listAppointmentFlow();
                        break;
                    case 3:
                        removeAppointmentFlow();
                        break;
                    case 4:
                        updateAppointmentFlow();
                        break;
                    case 5:
                        System.out.println("Returning to Main Menu");
                        break;
                    default:
                        System.out.println("Invalid option. Try again");
                }
            }

            catch (NumberFormatException e)
            {
                System.out.println("Error: Please enter a valid number.");
            }
        } while (option != 5);
    }

    private void addAppointmentFlow()
    {
        System.out.print("Enter appointment title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter appointment date (DD/MM/YYYY): ");
        String newDate = scanner.nextLine();

        System.out.print("Enter appointment time (HH:MM): ");
        String newTime = scanner.nextLine();

        try
        {
            Appointment newAppointment = new Appointment(newTitle, newDate, newTime);

            appointmentOrganizer.add(newAppointment);

            System.out.println("Success: Appointment added");
        }

        catch (InvalidItemException | DuplicateItemException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void listAppointmentFlow()
    {
        System.out.println("\n --- Appointment List ---");
        if (appointmentOrganizer.getAllItems().isEmpty())
        {
            System.out.println("The appointment list is empty.");
            return;
        }

        for (Appointment appointment : appointmentOrganizer.getAllItems())
        {
            System.out.println(appointment.getDetails());
        }
    }

    private void removeAppointmentFlow()
    {
        System.out.print("Enter the title of the appointment you wish to remove: ");
        String title = scanner.nextLine();

        try
        {
            appointmentOrganizer.remove(title);
            System.out.println("Success: Appointment removed");
        }

        catch (ItemNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void updateAppointmentFlow()
    {
        System.out.print("Enter the current title of the appointment you wish to update: ");
        String currentTitle = scanner.nextLine();

        System.out.print("Enter new appointment title: ");
        String updatedTitle = scanner.nextLine();

        System.out.print("Enter new appointment date (DD/MM/YYYY): ");
        String updatedDate = scanner.nextLine();

        System.out.print("Enter new appointment time (HH:MM): ");
        String updatedTime = scanner.nextLine();

        try
        {
            Appointment updatedAppointment = new Appointment(updatedTitle, updatedDate, updatedTime);

            appointmentOrganizer.update(currentTitle, updatedAppointment);

            System.out.println("Success: Appointment updated");
        }

        catch (InvalidItemException | ItemNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

}

