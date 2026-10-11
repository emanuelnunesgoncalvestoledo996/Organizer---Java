package com.emanuel;
import java.util.ArrayList;
import java.util.List;
import com.emanuel.exceptions.DuplicateItemException;
import com.emanuel.exceptions.ItemNotFoundException;

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
                throw new ItemNotFoundException("Error: Item not found.");
            }
    }

    public T getItem (String identifier)
        throws ItemNotFoundException{
            
            for (T item : items)
            {
                if (item.getIdentifier().equalsIgnoreCase(identifier))
                {
                    return item;
                }
            }

            throw new ItemNotFoundException("Error: Item not found.");
    }

    public void update (String identifier, T uptadeItem)
        throws ItemNotFoundException{

            for (int i = 0; i < items.size(); i++)
            {
                if (items.get(i).getIdentifier().equalsIgnoreCase(identifier))
                {
                    items.set(i,uptadeItem);
                    return;
                }
            }

            throw new ItemNotFoundException("Error: Item not found.");
    }

    public List<T> getAllItems()
    {
        return this.items;
    }

}
