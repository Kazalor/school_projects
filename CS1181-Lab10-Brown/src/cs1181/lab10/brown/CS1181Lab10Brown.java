
package cs1181.lab10.brown;

import java.util.Scanner;

/**
 * Lab10
 * @author Trenton Brown
 * @LabInstructor: Andrew Holmes
 * @LectureInstructor: Travis Doom
 * 
 * This class makes an array of 1000 random doubles. It then sorts the list and 
 * prompts the user to enter a type of search and for the index to determine the
 * value to be searched for.
 */
public class CS1181Lab10Brown {
    /**
     * Variable checks how many times the interpolation method is called
     */
    private static int interpolations;

    public static void main(String[] args) {
        // The creation, filling, and sorting of the array.
        double[] list = new double[1000];
        list = fill(list);
        System.out.println(toString(list));
        list = sort(list);
        System.out.println(toString(list));
        System.out.println("");
        
        Scanner input = new Scanner(System.in);
        int searchType = 1;
        // The while loop allows the program to run until the user enters -1.
        while(searchType != -1){
            interpolations = 0;
            // Getting the search type
            System.out.print("Enter type of  search (1 = success, 0 = fail, -1 = quit): ");
            searchType = input.nextInt();
            // Breaking out of the while loop before it prompts for an index if -1
            // is entered
            if (searchType == -1)break;
            // Getting the index
            System.out.print("Enter index (0...999): ");
            int index = input.nextInt();
            // Checking the search type so the right value can be searched for
            if(searchType == 1){
                System.out.println("Searching for the value of data[" + index + "] = " + list[index]);
                System.out.println("Value found: " + interpolation(list, list[index]));
            } else if (searchType == 0){
                System.out.println("Searching for the value of (data[" + index + "]"
                        + " + data[" + (index + 1) + "]) / 2 = " + (list[index] + list[index + 1]) / 2);
                System.out.println("Value found: " + interpolation(list, (list[index] + list[index + 1]) / 2));
            }
            System.out.println("");
        }
    }
    
    /**
     * This method just runs through each step in the array and sets the value 
     * to a randomly generated double.
     * @param list
     * @return the list filled with random double values
     */
    public static double[] fill(double[] list){
        for(int index = 0; index < list.length; index++){
            list[index] = (Math.random() * 1000);
        }
        return list;
    }
    
    /**
     * This method uses the selection sort method to organize the array.
     * @param list
     * @return the passed in list as a sorted list using selection sort.
     */
    public static double[] sort(double[] list){
        for(int indexOne = 0; indexOne < list.length; indexOne++){
            for(int indexTwo = indexOne + 1; indexTwo < list.length; indexTwo++){
                if(list[indexTwo] < list[indexOne]){
                    double temp = list[indexTwo];
                    list[indexTwo] = list[indexOne];
                    list[indexOne] = temp;
                }
            }
        }
        return list;
    }
    
    /**
     * This method reads through the list and adds each of the values to a string
     * @param list
     * @return the list as a string so that it may be viewed.
     */
    public static String toString(double[] list){
        StringBuilder string  = new StringBuilder();
        string.append("{");
        for(int i = 0; i < list.length; i++){
            if(i != 0){
                string.append(", " + list[i]);
            } else {
                string.append(list[i]);
            }
        }
        string.append("}");
        return string.toString();
    }
    
    /**
     * This method will check through the method for the value that is passed in
     * and make the list shorter as it goes to find the value
     * @param list
     * @param searchValue
     * @return true if the value is found, false otherwise.
     */
    public static boolean interpolation(double[] list, double searchValue){
        interpolations++;
        // Checks the middle value of the list for the searchValue
        if(list[list.length / 2] == searchValue){
            System.out.println(" - Interpolations: " + interpolations);
            return true;
        }
        // This if statement checks if the list passed in has a length of 1 and
        // and returns false since the value will not have been found.
        if(list.length <= 1){
            System.out.println(" - Interpolations: " + interpolations);
            return false;
        }
        int half = list.length/2;
        // Checks the upper half of the list for the value
        if (list[list.length / 2] <= searchValue){
            double[] shorterList = new double[list.length - half];
            System.arraycopy(list, half, shorterList, 0, list.length - half);
            return interpolation(shorterList, searchValue);
        }
        // Checks the lower half of the list for the value
        if (list[list.length / 2] > searchValue){
            double[] shorterList = new double[half];
            System.arraycopy(list, 0, shorterList, 0, half);
            return interpolation(shorterList, searchValue);
        }
        return false;
    }
    
}
