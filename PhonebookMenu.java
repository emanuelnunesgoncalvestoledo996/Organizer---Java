import java.util.Scanner;

public class PhonebookMenu 
{
    Phonebook phonebook;

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
            System.out.println("3 - Print a contact");
            System.out.println("4 - Modify a contact");
            System.out.println("5 - Remove a contact");
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

    public void menuCycle(Scanner input)
    {
        int option;

        do
        {
            option = menu(input);

            switch(option)
            {
                case 1: 
                {
                    System.out.println("Input the contact name: ");
                    String name = input.nextLine();

                    System.out.println("Input the contact phone number: ");
                    String phone = input.nextLine();

                    phonebook.addContact(name, phone);
                    break;
                }
            
                case 2:
                {
                    phonebook.printPhonebook();;
                    break;
                }

                case 3:
                {
                    System.out.print("Input the contact that you wish to print: ");
                    if (input.hasNextInt())
                    {
                        int idx = input.nextInt();
                        input.nextLine();
                        phonebook.printContact(idx);;
                    }
                    else
                    {
                        System.out.println("Invalid value.");
                        input.nextLine();
                    }
                    break;
                }

                case 4:
                {
                    System.out.print("Input the contact number you wish to modify: ");

                    if (input.hasNextInt())
                    {
                        int idx = input.nextInt();
                        input.nextLine();
                
                        System.out.print("Input the new contact name: ");
                        String name = input.nextLine();
                
                        System.out.print("Input the new contact phone number: ");
                        String phone = input.nextLine();
                
                        phonebook.updateContact(name,phone,idx);
                    }
                    else
                    {
                        System.out.println("Invalid value!");
                        input.nextLine();
                    }
                
                    break;
                
                }

                case 5:
                {
                    System.out.print("Input the contact you wish to remove: ");
                    if (input.hasNextInt())
                    {
                        int idx = input.nextInt();
                        input.nextLine();
                        phonebook.removeContact(idx);
                    }
                    else
                    {
                        System.out.println("Invalid value!");
                        input.nextLine();
                    }
                    break;
                }

            }
        }
        while (option != 0);
    }
}
