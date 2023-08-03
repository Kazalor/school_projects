package cs1181.lab02.brown;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class can be used to create ArrayLists of values and then allows the main class or a class using
 *    it to perform set operations.
 */
@SuppressWarnings("serial")
public class CS1181Set extends ArrayList<Integer>
{
   
/**
 * The constructor will take various integers and then go through each argument passed
 *    in and adds them to the list
 * I used the site https://www.geeksforgeeks.org/variable-arguments-varargs-in-java/ in order to learn
 *    how to pass and then add the various passed arguments into the list.
 * @param args
 */
   public CS1181Set(int...args) {
      for(int i: args) {
         this.add(i);
      }

   }
   
/**
 * This constructor allows the creation of an arraylist that is a copy of another
 * @param list being copied
 */
   public CS1181Set(CS1181Set list)
{
   this.addAll(list);
}

   /**
    * @param list2
    * @return the intersection set
    */
   public ArrayList<Integer> intersection(CS1181Set list2)
   {
      ArrayList<Integer> L2 = list2;
      // This for loop will iterate through the first list and search for which elements
      //    it has in common and then remove the ones they don't have in common
      for(int i = 0; i < this.size(); i++) {
         if (!L2.contains(this.get(i))) {
            this.remove(i);
            i--;
         }
      }
      return this;
   }

   /**
    * @param list2
    * @return the union set
    */
   public ArrayList<Integer> union(CS1181Set list2)
   {
      ArrayList<Integer> L2 = list2;
      // The for loop iterates through the second list to find out which values the
      //    first list does not have, and then adds the ones they don't have in common to
      //    the first loop
      for(int i = 0; i < L2.size(); i++) {
         if (!this.contains(L2.get(i))) {
            this.add(L2.get(i));
         }
      }
      return this;
   }

   /**
    * @param list2
    * @return (The first list) - (The second list)
    */
   public ArrayList<Integer> difference(CS1181Set list2)
   {
      ArrayList<Integer> L2 = list2;
      // The for loop iterates through the first list and check to see which values
      //    it has in common with the second list and then removes the common values
      for(int i = 0; i < this.size(); i++) {
         if (L2.contains(this.get(i))) {
            this.remove(i);
            i--;
         }
      }
      return this;
   }
}
