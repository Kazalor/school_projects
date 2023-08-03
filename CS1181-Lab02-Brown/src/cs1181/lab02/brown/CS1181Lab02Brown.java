/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs1181.lab02.brown;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;


public class CS1181Lab02Brown {

    /**
     * @param args the command line arguments
     * This class is demonstrating the application of of the CS1181SetLab02 class
     */
    public static void main(String[] args){
        
        try{
            CS1181SetLab02 list1 = new CS1181SetLab02(1, 3, 5, 7);
            System.out.println(list1);
            
            CS1181SetLab02 list2 = new CS1181SetLab02(1, 3, 3);
            System.out.println(list2);
            
            String fileName = "SetData.dat";
            CS1181SetLab02 list3 = new CS1181SetLab02();
            list1.saveToFile(fileName);
            list3.loadFromFile(fileName);

        } catch (Exception e){
            System.out.println("File error");
        }
    }
    
}
