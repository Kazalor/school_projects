/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs1181.lab02.brown;


public class DuplicateElementsInSetException extends Exception{
    public DuplicateElementsInSetException(){
        super("Values used more than once.");
    }
}
