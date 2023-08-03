
package cs1181_project4_brown;

/**
 * Project4
 * 
 * This class allows the creation of events to be used to determine what is happening
 * in the store to the customers
 */
public class Event implements Comparable<Event>{
    private String eventType;
    private Customer customer;
    private double time;
    private RegularLane lane = null;
    
    /**
     * Creates the event with the passed parameters
     * @param eventType
     * @param customer
     * @param arrivalTime 
     */
    public Event(String eventType, Customer customer, double arrivalTime){
        this.eventType = eventType;
        this.customer = customer;
        this.time = arrivalTime;
    }//End event
    
    /**
     * Marks the lane the customer is in so that it can be accessed when the
     * checkout event is reached
     * @param lane 
     */
    public void setLane(RegularLane lane) {
        this.lane = lane;
    }//End setLane

    /**
     * Allows the changing of the event type so the customer can be progressed 
     * through the store
     * @param eventType 
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }//End setEventType

    /**
     * Changes the time so that the event can be used when the time occurs
     * @param time 
     */
    public void setTime(double time) {
        this.time = time;
    }//End setTime
    
    /**
     * @return The lane the customer is in
     */
     public RegularLane getLane() {
        return lane;
    }//End getLane
    
    /**
     * @return The type of even that occurs
     */
    public String getEventType() {
        return eventType;
    }//End getEventType

    /**
     * @return The customer that corresponds to the event
     */
    public Customer getCustomer() {
        return customer;
    }//End getCustomer

    /**
     * @return The time the event occurs
     */
    public double getTime() {
        return time;
    }//End getTime

    /**
     * @param event
     * @return Which event occurs before the other
     */
    public int compareTo(Event event) {
        if(this.time < event.time) return -1;
        if(this.time == event.time) return 0;
        if(this.time > event.time) return 1;
        throw new RuntimeException("Should not occur");
    }//End compareTo
    
}
