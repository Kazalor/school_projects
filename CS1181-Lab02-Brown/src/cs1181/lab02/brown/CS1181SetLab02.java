package cs1181.lab02.brown;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.RandomAccessFile;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 */
public class CS1181SetLab02 extends CS1181Set{

    /**
     * This constructor allows the creation of an ArrayList similar to CS1181Set
     *  but runs through the list to make sure no value is used more than once.
     * @param args
     * @throws DuplicateElementsInSetException 
     */
    public CS1181SetLab02(int...args) throws DuplicateElementsInSetException{
    // The for loop just iterates through all of the ints passed into the constructor
    for (int i: args){
        try{
            // The if statement will check if a value is already in the list and then
            //  only add the values that aren't in the list so that a list may still
            //  be made.
            if (this.contains(i)){
                throw new DuplicateElementsInSetException();
            } else{
                this.add(i);
            }
        } catch(DuplicateElementsInSetException e){
            System.out.println(e);
        }
    }
}
    
    /**
     * This method goes through the ArrayList, first adding the size of the list
     *  to the binary file, then adding each value within the ArryList
     * @param name 
     */
    public void saveToFile(String name){
        try{
            RandomAccessFile setFile = new RandomAccessFile(name, "rw");
            setFile.setLength(0);
            
//            setFile.writeByte(this.size());
            // This for loop is just iterating through the ArrayList and writing
            //  each of the values into the file
            for(int i = 0; i < this.size(); i++){
                setFile.writeByte(this.get(i));
            }
            
            setFile.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    /**
     * This method reads the file and then converts the bytes into ints to print
     *  the lists
     * @param name 
     */
    public void loadFromFile(String name){
        try{
            RandomAccessFile setFile = new RandomAccessFile(name, "rw");
            
            setFile.seek(0);
            
            // Instantiating an empty list to fill with the list that is desired
            //  from the file by using the for loop to iterate through the integer
            //  bytes and get the integers
            CS1181SetLab02 list = new CS1181SetLab02();
            for(int i = 0; i < this.size(); i++){
                list.add((int) setFile.readByte());
            }
            System.out.println(list);
            
            setFile.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
