
package cs1181_project4_brown;

import java.util.Comparator;

/**
 * Project4
 * 
 * THis class is used by the PriorityQueue in main to sort the customers
 */
public class EventComparator implements Comparator<Event>{

    @Override
    public int compare(Event e1, Event e2) {
        return -1*e1.compareTo(e2);
    }
    
}
