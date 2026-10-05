import java.util.ArrayList;
import java.util.List;
import exceptions.InvalidItemException;
import exceptions.DuplicateItemException;
import exceptions.ItemNotFoundException;

public class Organizer<T extends Schedulable>
{
    private List<T> items;

    public Organizer()
    {
        this.items = new ArrayList<>();
    }

    public void add(T item)
        throws DuplicateItemException{

        if (items.contains(item))
        {
            throw new DuplicateItemException("Error: this item already exists in the agenda.");
        }

        items.add(item);
    }

    public void remove(String identifier)
        throws ItemNotFoundException{

            // lambda expression. removeIf iterates the list using a loop
            boolean removed = items.removeIf(item -> item.getIdentifier().equalsIgnoreCase(identifier));

            if (!removed)
            {
                throw new ItemNotFoundException("Error: Item not found in agenda.");
            }
    }

    // It acts like the printPhonebook, but instead of printing, returns the list
    public List<T> getAllItems()
    {
        return this.items;
    }
}
