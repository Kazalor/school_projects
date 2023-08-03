
package cs1181.lab07.brown;

/**
 * Lab07
 * @author Trenton Brown
 * Lab Instructor: Andrew Holmes
 * Lecture Instructor: Travis Doom
 * 
 * @param <E> 
 * This class is used by the Chain class to create and assign values in a linked
 * list
 */
public class Link <E>{
    
    private E weight;
    private Link next;
    
    /**
     * Creates an empty link
     */
    public Link(){
        weight = null;
        next = null;
    }
    
    /**
     * Creates a link with the desired value
     * @param weight 
     */
    public Link(E weight){
        this.weight = weight;
    }
    
    /**
     * Creates a link with the desired value and points to the specified link
     * @param weight
     * @param next 
     */
    public Link(E weight, Link next){
        this.weight = weight;
        this.next.setNext(next);
    }
    
    /**
     * @return weight
     */
    public E getWeight(){
        return weight;
    }
    
    /**
     * @return the next Link
     */
    public Link getNext(){
        return next;
    }
    
    /**
     * Changes the next value so that "this" points to the specified link
     * @param next 
     */
    public void setNext(Link next){
        this.next = next;
    }
    
}
