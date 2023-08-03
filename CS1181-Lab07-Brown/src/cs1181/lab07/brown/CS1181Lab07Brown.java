
package cs1181.lab07.brown;

/**
 * Lab07
 * @author Trenton Brown
 * Lab Instructor: Andrew Holmes
 * Lecture Instructor: Travis Doom
 * 
 * This class demonstrates the chain class, using Integers.
 */
public class CS1181Lab07Brown {

    public static void main(String[] args) {
        Chain<Integer> chain = new Chain();
        chain.add(3);
        chain.add(4);
        chain.add(7);
        chain.add(6);
        chain.add(3);
        chain.add(2);
        chain.add(9);
        chain.add(6);
        chain.add(3);
        System.out.println(chain);
        System.out.println("Size: " + chain.getSize());
        chain.removeN(3, 2);
        chain.removeLast(6);
        System.out.println(chain);
        System.out.println("Size: " + chain.getSize());
        System.out.println(chain.removeN(7, 2));
        System.out.println(chain);
        
    }
    
}
