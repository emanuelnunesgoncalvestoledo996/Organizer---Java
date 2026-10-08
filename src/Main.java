public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("Starting Personal Organizer System!");

        Organizer <Contact> contactOrganizer = new Organizer<>();
        Organizer <Appointment> appointmentOrganizer = new Organizer<>();

        OrganizerMenu menu = new OrganizerMenu(contactOrganizer, appointmentOrganizer);

        menu.start();

        System.out.println("System shut down.\n");
    }
}