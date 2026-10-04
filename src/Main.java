import java.util.InputMismatchException;
import java.util.Scanner;


public class Main
{
    public static void main (String [] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Type the phonebook size: ");

        try
        { 
            input.nextLine();
            
            Phonebook phonebook = new Phonebook();

            PhonebookMenu menu = new PhonebookMenu(phonebook);
            menu.menuCycle(input);
        }

        catch (InputMismatchException error)
        {
            System.out.println("You must type an integer number!");
        }

        catch (IllegalArgumentException error)
        {
            System.out.println(error.getMessage());
        }

        input.close();
    }
}