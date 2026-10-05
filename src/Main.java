import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        Phonebook phonebook = new Phonebook();
        PhonebookMenu menu = new PhonebookMenu(phonebook);

        menu.run(input);

        input.close();
    }
}