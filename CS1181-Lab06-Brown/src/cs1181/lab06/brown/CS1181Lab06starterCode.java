package cs1181.lab06.brown;


import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This start code is designed for CS1181 Lab06. In this lab, students will
 * develop a recursive routine to "flood fill" a region of a two dimentional
 * array. A data file contains an array of "1" and "0" values. This array will
 * be displayed, after which the user will be prompted to enter a location in
 * the array to start the "flood fill". If that location is 'empty' (a 0) then
 * it will be changed to 'filled' (filled), as will every other 'empty' value in
 * all cells that are bounded by filled space (or the edge of the array).
 * Further details are available in the Lab Handout.
 *
 * @author Doom
 * 
 * Lab06 Changes By Trenton Brown
 * Lab Instructor: Andrew Holmes
 * Lecture Instructor: Travis Doom
 * Code to fill the "empty spaces in the grid. Code was also added to get the triangles
 * from a file like in lab05 and solve for the maximum path of natural numbers
 * with an additional path as opposed to lab05
 */
public class CS1181Lab06starterCode {

    /**
     * This routine calls the provided and student-generated methods to solve
     * the Flood Fill problem. This routine should not require any permanent
     * modification (although you are welcome to make temporary modifications
     * for testing purposes).
     *
     * @param args command line arguments are not used in this project
     */
    public static void main(String[] args) {

        String inputFile = "CS1181-Lab06Data.txt";

        int grid[][] = loadGrid(inputFile);

        if (grid == null) {
            System.out.println("There was an error creating the initial grid "
                    + "from the data file: " + inputFile);
            System.exit(-1);
        }

        System.out.println("Before flood fill");
        printGrid(grid);

        floodFill(grid);

        System.out.println("After flood fill");
        printGrid(grid);

        inputFile = "CS1181-Lab05Data.txt";
        loadTriangle(inputFile);
    } // end-method main

    /**
     *
     * @param fileName A file that contains text data in the following format.
     * The first line of the file contains the number of rows and the number of
     * columns in the grid. Each other line of the file contains the appropriate
     * value (0 or 1) for the corresponding grid position. All values are space
     * delimited. For example, a 2x3 grid might have the corresponding text file
     * format: 2 3 1 0 0 0 1 1
     * @return A two-dimentional grid of '0' (empty) and '1' (full) values. If
     * no file data is available then the returned value is null;
     */
    public static int[][] loadGrid(String fileName) {

        int grid[][] = null;

        try {
            FileInputStream fileStream = new FileInputStream(fileName);
            Scanner fileScanner = new Scanner(fileStream);

            // Get dimention data from the first row of the data file
            int nRows = fileScanner.nextInt();
            int nCols = fileScanner.nextInt();
            grid = new int[nRows][nCols];

            for (int r = 0; r < nRows; r++) {
                for (int c = 0; c < nCols; c++) {
                    grid[r][c] = fileScanner.nextInt();
                } // end-for each column
            } // end-for each row
            fileStream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } // end-try-catch

        return grid;
    } // end-method loadGrid

    /**
     * THIS METHOD IS INCOMPLETE. THE STUDENT TASK IS TO UPDATE THIS METHOD (and
     * create other methods, as needed) TO SOLVE THE FLOOD FILL PROBLEM. UPDATE
     * THIS DOCUMENTION APPROPRIATELY.
     *
     * @param grid a two-dimentional arrays containing only '0's and '1's.
     */
    public static void floodFill(int[][] grid) {

        int row = -1;
        int column = -1;
        Scanner input = new Scanner(System.in);

        // Get a valid row and column from the user
        System.out.print("Enter a row, then a column, separated by a space. ");
        row = input.nextInt();
        column = input.nextInt();
//        row = validInputCheck();
//        column = validInputCheck();
        // The while statement checks to make sure the entered values are within bounds
        while(row > grid.length - 1 || row < 0 || column < 0 || column > grid[1].length - 1){
            System.out.print("Entries are out of bounds, enter again. ");
            row = input.nextInt();
            column = input.nextInt();
//            row = validInputCheck();
//            column = validInputCheck();
        }
        
        // Start the flood fill from (row, col). (You will probably want to
        // make/call a private function to do the recursive flood fill.)
        performFill(grid, row, column);
 
    } // end-method floodFill
    
    /**
     * Code By Celeritas on https://codereview.stackexchange.com/questions/58800/making-sure-user-inputs-correct-type
     * @return The int if one was entered, otherise throw and exception that
     * allows the loop to restart and get new entries from the user.
     */
//    public static int validInputCheck(){
//        while(true){
//            Scanner in = new Scanner(System.in);
//            String input = in.next();
//            try{
//                return Integer.parseInt(input);
//            } catch(Exception e){
//                System.out.print("Invalid entry type, try again. ");
//            }
//        }
//    }
    
    /**
     * This method actually performs the fill in the grid
     * @param grid
     * @param row
     * @param column 
     */
    public static void performFill(int[][] grid, int row, int column){
        // The coordinate value, if 0, allows the program to change a value in the
        // spot directed by the row and column values. 
        int coordinate = grid[row][column];
        
        // This if statement will check if the value is already filled, and if so
        // it exits the method since ths spaces is already filled and therefore can't
        // fill any surrounding empty spots.
        if(coordinate == 1){
            return;
        }
        
        grid[row][column] = 1;
        
        // These if statements allows the method to call itself if any of the surrounding
        // spaces are empty, and fills them, then checks the spaces around them.
        if(row + 1 < grid.length && grid[row + 1][column] == coordinate){
            performFill(grid, row + 1, column);
        }
        if(row - 1 >= 0 && grid[row - 1][column] == coordinate){
            performFill(grid, row - 1, column);
        }
        if(column + 1 < grid[1].length && grid[row][column + 1] == coordinate){
            performFill(grid, row, column + 1);
        }
        if(column - 1 >= 0 && grid[row][column - 1] == coordinate){
            performFill(grid, row, column - 1);
        }
    }
    
    /**
     * Display the grid on the console
     *
     * @param grid a 2D grid of '0's and '1's.
     */
    public static void printGrid(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                System.out.printf("%3d", grid[r][c]);
            } // end-for each column
            System.out.println();
        } // end-for each row
    } // end-method printGrid

    private static void loadTriangle(String inputFile) {
        // Since the getting the triangles and the data file used for it are the
        // same, the code used here is the same as Lab05
        try {   
            FileInputStream fileStream = new FileInputStream("CS1181-Lab05Data.txt");
            Scanner fileScanner = new Scanner(fileStream);
            // The variable finds the number of triangles and passes it into the
            // for loop to find the max path of each triangle
            int numberOfTrianges = fileScanner.nextInt();
            for(int triangleInProgress = 0; triangleInProgress < numberOfTrianges; triangleInProgress++){
                // The variables and for loops are used together to get each 
                // triangle so that it may be passed into the maximumPath method
                // and then print out the result.
                int numberOfRows = fileScanner.nextInt();
                int[][] triangle = new int[numberOfRows][numberOfRows];
                for(int rowInTriangle = 0; rowInTriangle <= triangle.length - 1; rowInTriangle++){
                  for(int columnInTriangle = 0; columnInTriangle <= triangle.length - 1; columnInTriangle++){
                    triangle[rowInTriangle][columnInTriangle] = fileScanner.nextInt();
                  }  
                }
                System.out.println("The maximum path of triangle " + (triangleInProgress + 1) + " is: "
                       + maximumPath(triangle, 0, 0));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }    }

    /**
     * This method recursively goes through the triangle passed in and finds the
     * maximum path of the natural numbers
     * @param triangle
     * @param row
     * @param column
     * @return maximumPath
     */
    public static int maximumPath(int[][] triangle, int row, int column){
        // Most of the code for this method is the same as the triangle problem 
        // Lab05, only changes are to accomidate for the change in lab06
        
        // The first two if statements simply check to see if the mthod has called
        // the bounds of triangle and prevents it rom going further.
        if(row == triangle.length){
            return 0;
        }
        if(column == triangle.length || column < 0){
            return 0;
        }
        
        // These variables check down each available path in the triangle and
        // then go through the if statement to only return the sum of the paths
        // for the maximum path.
        int maxPath1 = maximumPath(triangle, row + 1, column);
        int maxPath2 = maximumPath(triangle, row + 1, column + 1);
        int maxPath3 = maximumPath(triangle, row + 1, column - 1);
        if(maxPath1 >= maxPath2 && maxPath1 >= maxPath3){
            return triangle[row][column] + maxPath1;
        }
        if(maxPath2 >= maxPath1 && maxPath2 >= maxPath3){
            return triangle[row][column] + maxPath2;
        }
        return triangle[row][column] + maxPath3;
    }

} // end-class
