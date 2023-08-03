package cs1181.lab05.brown;

//import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lab 05
 * @author Trenton Brown
 * Lab Instructor: Andrew Holmes
 * Lecture Instructor: Dr. Doom
 * 
 * This class prompts the user for a word, then passes and may recursively call
 * the permutationList(String word) method and print each permutation of the word.
 */
public class CS1181Lab05Brown {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

//        System.out.print("Enter a word: ");
        try {
//            String word = in.next();
//
//            // This for loop is just to iterate through the returned list and print out
//            // each permutation of the word the user entered.
//            for (String wordsInList : permutationList(word)) {
//                System.out.println(wordsInList);
//            }
            
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
        }

    }

//    /**
//     * This method takes a word, then by breaking the word apart and calling itself
//     * to further break up the word and then put the letter back together in every possible order
//     * @param word - The word that is entered to have the permutations printed out
//     * @return ArrayList - An ArrayList<String> that contains the permutations of word
//     */
//    public static ArrayList<String> permutationList(String word) {
//        ArrayList<String> listOfPermutations = new ArrayList<String>();
//        
//        // If statement will determine if the word that is passed in needs to be broken up
//        // to create permutations or simply added to the list
//        if(word.length() <= 1){
//            listOfPermutations.add(word);
//            return listOfPermutations;
//        } else {
//            // For loop iterates through every character in word so that it finds
//            // each permutation starting with that character
//            for(int characterIndex = 0; characterIndex < word.length(); characterIndex++){
//                char letter = word.charAt(characterIndex);
//                String smallerWord;
//                // If statement checks to see how the word needs to be broken apart
//                if(characterIndex == 0){
//                    smallerWord = word.substring(1);
//                } else if(characterIndex > 0 && characterIndex < word.length() - 1){
//                    smallerWord = word.substring(0, characterIndex) + word.substring(characterIndex + 1);
//                } else {
//                    smallerWord = word.substring(0, word.length() - 1);
//                }                
//                
//                // This for loop iterates through the list returned by the recursion
//                // of itself and adds the leter that was broken off of the passed in word
//                // to the each word in the list and adds them to another list
//                for(String wordsInList : permutationList(smallerWord)) {
//                    listOfPermutations.add(letter + wordsInList); 
//                }
//            }
//            return listOfPermutations;
//        }
//    }
    
    /**
     * This method recursively goes through the triangle passed in and finds the
     * maximum path of the natural numbers
     * @param triangle
     * @param row
     * @param column
     * @return maximumPath
     */
    public static int maximumPath(int[][] triangle, int row, int column){
        // The first two if statements simply check to see if the mthod has called
        // the bounds of triangle and prevents it rom going further.
        if(row == triangle.length){
            return 0;
        }
        if(column == triangle.length){
            return 0;
        }
        
        // These variables check down each available path in the triangle and
        // then go through the if statement to only return the sum of the paths
        // for the maximum path.
        int maxPath1 = maximumPath(triangle, row + 1, column);
        int maxPath2 = maximumPath(triangle, row + 1, column + 1);
        if(maxPath1 >= maxPath2){
            return triangle[row][column] + maxPath1;
        }
        return triangle[row][column] + maxPath2;
    }
}