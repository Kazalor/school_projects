package cs1181.lab07.brown;

/**
 * Lab07
 * @param <E> 
 * This class creates and allows the editing of a singly linked list of objects
 */
public class Chain <E>{
    
    public Link<E> head;
    private int size = 0;
    
    /**
     * Creates the collection as a singly-linked list by setting "head" to null.
     */
    public Chain(){
        head = null;
    }
    
    /**
     * Adds the "weight" to the end of the queue
     * @param weight 
     */
    public void add(E weight){
        Link toAdd = new Link(weight);
        toAdd.setNext(null);
        // If the head is null the first node added will be the head
        if(head == null) {
            head = toAdd;
            size++;
            return;
        }
        // If the head is not null, then iterate through the list to find the
        // end of the list
        Link currentNode = head;
        while(currentNode.getNext() != null){
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(toAdd);
        size++;
    }
    
    /**
     * Removes the first occurrence of the "weight" in the linked list.
     * @param weight
     * @return true if the head was found and removed, false otherwise
     */
    public boolean remove(E weight){
        // The first two if statemens check to see if the list is empty, if so
        // then there is no object to remove and it returns false, if not, then 
        // it checks if the head is the one to be removed and removes it and updates
        // the list's size
        if(head == null) return false;
        if(head.getWeight() == weight) {
            head = head.getNext();
            size--;
            return true;
        } 
        // The while loop checks each object after the head until the desired
        // object to rmove is found and then removes it
        Link current = head;
        while(current.getNext() != null){
            if(current.getNext().getWeight() == weight){
                current.setNext(current.getNext().getNext());
                size--;
                break;
            }
            if(current.getNext() == null) return false;
        }
        return true;
    }
    
    /**
     * Checks to see if the "weight" appears in the data "number" times or more
     * and if so, removes "number" occurrences of "weight"
     * @param weight
     * @param number
     * @return true if they are found and removed, false otherwise
     */
    public boolean removeN(E weight, int number){
        int occurrences = 0;
        int removals = 0;
        // If statements check if the list is empty, then check to see if the
        // desired weight is found "number" times, if so it removes the first
        // "number" otherwise it returns false without removing anything
        if(head == null) return false;
        if(head.getWeight() == weight) occurrences++;
        Link current = head;
        while(current.getNext() != null){
            if(current.getNext().getWeight() == weight){
                occurrences++;
            }
            current = current.getNext();
        }
        // This if statement actully checks if the values were found and performs
        // the removal
        if(occurrences >= number && removals < number){
            current = head;
            while(current.getNext() != null){
                if(head.getWeight() == weight){
                    head = current.getNext();
                    current = head;
                    removals++;
                    size--;
                }
                if(current.getNext().getWeight() == weight && removals < number){
                    current.setNext(current.getNext().getNext());
                    size--;
                    removals++;
                }
                if(removals == number){
                    return true;
                }
                current = current.getNext();
            }
        }
        
        return false;
    }
    
    /**
     * Removes the last occurrence of "weight" in the data
     * @param weight
     * @return true if found and removed, otherwise false
     */
    public boolean removeLast(E weight){
        Link beforeNodeToRemove = null;
        Link current = head;
        // Check if the list is empty
        if(head == null) return false;
        // check if the head has the desired value
        if(head.getWeight() == weight){
            beforeNodeToRemove = head;
        }
        // checks if the value exists later in the list
        while(current.getNext() != null){
            if(current.getNext().getWeight() == weight){
                beforeNodeToRemove = current;
            }
            current = current.getNext();
        }
        // if the value wasn't found return false without removing
        if(beforeNodeToRemove == null){
            return false;
        }
        // removes the last occurrrenc of the value
        beforeNodeToRemove.setNext(beforeNodeToRemove.getNext().getNext());
        size--;
        return true;
    }
    
    /**
     * @return the size of the data collection
     */
    public int getSize(){
        return size;
    }
    
    /**
     * @return a string constructed from the data within the collection in order
     * of is creation.
     */
    public String toString(){
        // Uses a StringBuilder to create the string to return and print.
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Link current = head;
        stringBuilder.append(current.getWeight());
        // Read through the list adding the values to the string
        while(current.getNext() != null){
            stringBuilder.append(", " + current.getNext().getWeight());
            current = current.getNext();
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
    
}
