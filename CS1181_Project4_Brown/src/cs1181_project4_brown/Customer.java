
package cs1181_project4_brown;

import java.text.DecimalFormat;

/**
 * Project4
 * @author Trenton Brown
 * @LabInstructor: Andrew Holmes
 * @LectureInstructor: Travis Doom
 * 
 * This class holds the customer manipulation and allows for the creation of customers
 */
public class Customer {

    private double arrival;
    private int items;
    private double shopping;
    private double timeInCheckout = 0.0;
    
    /**
     * The constructor takes the given information and assigns the values to the
     * appropriate variable for later access
     * @param arrival When the customer arrives at the store
     * @param items How many items the customer has
     * @param timeSpentShopping How long it will take them to get their items
     */
    public Customer(double arrival, int items, double timeToGetItems){
        this.arrival = arrival;
        this.items = items;
        double itemsDouble = (double) items;
        this.shopping = itemsDouble * timeToGetItems;
    }//End constructor

    /**
     * This method determines the time the customer spends in checkout by the number
     * that gets passed in
     * @param timeInCheckout 
     */
    public void setTimeInCheckout(double timeInCheckout) {
        this.timeInCheckout += timeInCheckout;
    }//End setTimeInCheckout
    
    /**
     * @return The customer's arrival time
     */
    public double getArrival() {
        return arrival;
    }//End getArrival

    /**
     * @return The number of items the customer has
     */
    public int getItems() {
        return items;
    }//End getItems

    /**
     * @return The time the customer spends shopping
     */
    public double getShopping() {
        return shopping;
    }//End getShopping

    /**
     * @return The time the customer spends in checkout
     */
    public double getTimeInCheckout() {
        return timeInCheckout;
    }//End getTimeInCheckout
    
    /**
     * Method uses StringBuilder to create a string of the customer's info
     * @return the string of info
     * https://www.baeldung.com/java-round-decimal-number Site was used to determine
     * how to format the results in the desired manner
     */
    public String toString(){
        StringBuilder string = new StringBuilder();
        DecimalFormat df = new DecimalFormat("###.##");
        string.append(this.arrival + "\t" + this.items + "\t" + df.format(this.shopping) + "\t" + df.format(this.timeInCheckout));
        return string.toString();
    }// End toString
}
