
package cs1181_project4_brown;


import java.util.ArrayDeque;
/**
 * Project4
 * 
 * This class allows the instantiation of RegularLanes and the manipulation of 
 * them and the Express Lanes
 * 
 * https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html Was used
 * to determine the best type of queue to use for the line
 */
public class RegularLane {
    private ArrayDeque<Customer> line;
    
    /**
     * The constructor instantiates the PriorityQueue that serves as the line as an
     * empty list
     */
    public RegularLane(){
        line = new ArrayDeque<Customer>();
    }//End Constructor
    
    /**
     * The add method will add customers to the end of the line
     * @param customer 
     */
    public void add(Customer customer){
        for(Customer i: line){
            customer.setTimeInCheckout(this.checkout(i));
        }
        customer.setTimeInCheckout(this.checkout(customer));
        line.add(customer);
    }//End add
    
    /**
     * Removes the customer at the front of the line and then prints their data
     * for review.
     */
    public void remove(){
        Customer customer = line.remove();
        System.out.println(customer.toString());
    }//End remove
    
    /**
     * This method takes the number of items the customer has and returns the time
     * it would take for the customer to get through
     * @param customer
     * @return the time spent in checkout
     */
    public double checkout(Customer customer){
        double time = (0.05 * customer.getItems()) + 2.0;
        return time;
    }//End checkout
    
    /**
     * @return The size of the line in the lane
     */
    public int getLineSize(){
        return this.line.size();
    }//End getLineSize
    
}
