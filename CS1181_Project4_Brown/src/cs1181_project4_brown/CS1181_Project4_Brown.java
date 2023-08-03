
package cs1181_project4_brown;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Project4
 * 
 * The main class reads the file of the store's customer data and adds the customers'
 * and their info to an ArrayList to be further handled. The customers' arrival
 * and checkout times are used to progress the simclock to find out how much time
 * has passed in the store.
 */
public class CS1181_Project4_Brown {

    private static PriorityQueue<Event> events = new PriorityQueue(new EventComparator());
    private static ArrayList<RegularLane> regularLanes = new ArrayList<RegularLane>();
    private static ArrayList<RegularLane> allLanes = new ArrayList<RegularLane>();
    private static double simClock = 0.0;
    
    public static void main(String[] args) {
        //The try catch is to read through the file and create the customers
        try{
            FileInputStream fileStream = new FileInputStream("CS1181-Project04-arrival.txt");
            Scanner fileScanner = new Scanner(fileStream);
            while(fileScanner.hasNext()){
                Customer customer = new Customer(fileScanner.nextDouble(), fileScanner.nextInt(), fileScanner.nextDouble());
                Event event = new Event("Arrival", customer, customer.getArrival());
                events.add(event);
            }//End While-loop
        } catch(Exception e){
            System.out.println(e);
        }//End Try-Catch
        
        //Assuming all lanes are open 9 regular and 3 express 0 closed (12 Max)
        for(int i = 0; i < 3; i++){
            ExpressLane lane = new ExpressLane();
            allLanes.add(lane);
        }//End express lane creation loop
        for(int i = 0; i < 9; i++){
            RegularLane lane = new RegularLane();
            regularLanes.add(lane);
            allLanes.add(lane);
        }//End regular lane creation loop
        
        while(!events.isEmpty()){
            Event currentEvent = events.remove();
            simClock = currentEvent.getTime();
            
            // If statement checks the event type and then prompts the proper
            // methods in response
            if(currentEvent.getEventType().equals("Arrival")){
                currentEvent.setEventType("Shopping");
                currentEvent.setTime(currentEvent.getCustomer().getShopping());
                events.add(currentEvent);
            } else if(currentEvent.getEventType().equals("Shopping")){
                //The if statement checks how many items the customer has so they
                //may be added to the proper shopping lane
                 if(currentEvent.getCustomer().getItems() > 12){
                        RegularLane regLane = regularLanes.get(0);
                        //The for loop iterates to find the line with the least
                        //amount of customers
                        for(RegularLane i: regularLanes){
                            if(i.getLineSize() == 0){
                                regLane = i;
                                break;
                            } else {
                                if(i.getLineSize() < regLane.getLineSize()){
                                    regLane = i;
                                }
                            }
                        }//End for-Loop
                        regLane.add(currentEvent.getCustomer());
                        currentEvent.setEventType("Checkout");
                        currentEvent.setTime(currentEvent.getCustomer().getTimeInCheckout());
                        currentEvent.setLane(regLane);
                        events.add(currentEvent);
                    } else{
                        RegularLane regLane = regularLanes.get(0);
                        for(RegularLane i: allLanes){
                            if(i.getLineSize() == 0){
                                regLane = i;
                                break;
                            } else {
                                if(i.getLineSize() < regLane.getLineSize()){
                                    regLane = i;
                                }
                            }
                        }//End for-Loop
                        regLane.add(currentEvent.getCustomer());
                        currentEvent.setEventType("Checkout");
                        currentEvent.setTime(currentEvent.getCustomer().getTimeInCheckout());
                        currentEvent.setLane(regLane);
                        events.add(currentEvent);
                    }//End lane-determining if-statement
            }else if(currentEvent.getEventType().equals("Checkout")){
                currentEvent.getLane().remove();
            }//End checkout condition
            
        }//End event-type check
        
    }//End Main
    
}//End class
