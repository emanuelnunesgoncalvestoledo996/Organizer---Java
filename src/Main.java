import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        Organizer phonebook = new Organizer();
        PhonebookMenu menu = new PhonebookMenu(phonebook);

        menu.run(input);

        input.close();
    }
}