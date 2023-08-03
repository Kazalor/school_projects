
package cs1181.concurrency;


public class CS1181Concurrency implements Runnable{


//    public static void main(String[] args) {
//        System.out.println("Start");
//        Thread thread = new Thread();
//        System.out.println("Main done");
//    }

    @Override
    public void run() {
//        System.out.println("Run");
        System.out.println("Run-Start");
        for(int i = 0; i < 26; i++){
            char c = (char) ('A' + i);
            System.out.println(c);
        }
        System.out.println("Run-Done");
    }
    
    public static void main(String[] args) {
//        System.out.println("Start");
        System.out.println("Main-Start");
        Thread thread = new Thread(new CS1181Concurrency());
        thread.start();
        
        for(int i = 0; i < 26; i++){
            char c = (char) ('a' + i);
            System.out.println(c);
        }
        System.out.println("Main done");
    }
    
}
