package cs1181_project4_brown;

import java.util.ArrayDeque;

/**
 * Project4
 * @author Trenton Brown
 * @LabInstructor: Andrew Holmes
 * @LectureInstructor: Travis Doom
 * 
 * This class allows the instantiation of the Express Lanes
 * 
 * https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html Was used
 * to determine the best type of queue to use for the line
 */
public class ExpressLane extends RegularLane{
    
    private ArrayDeque<Customer> line;
    
    /**
     * The constructor instantiates an empty PriorityQueue to serve as the line
     * for the register.
     */
    public ExpressLane(){
        line = new ArrayDeque<>();
    }//End Constructor
    
    /**
     * This method takes the number of items the customer has and returns the time
     * it would take for the customer to get through
     * @param customer
     * @return the time spent in checkout
     */
    @Override
    public double checkout(Customer customer){
        double time = (0.1 * customer.getItems()) + 1.0;
        return time;
    }//End checkout
    
}
