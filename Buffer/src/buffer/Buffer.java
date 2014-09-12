package buffer;

import java.util.concurrent.Semaphore;

/**
 * A Java implementation of the bounded buffer problem.
 *
 * @author Jack Finlay ID: 1399273
 */
public class Buffer extends Thread {

    public static final int BUFFER_SIZE = 5;
    private static int sleepLength, numProducers, numConsumers;
    public static int[] bufferArray;
    private final Producer[] producers;
    private final Consumer[] consumers;

    public Buffer(int sleepLength, int numProducers, int numConsumers) {
        Buffer.sleepLength = sleepLength;
        Buffer.numProducers = numProducers;
        Buffer.numConsumers = numConsumers;
        // Assign arguments.

        bufferArray = new int[BUFFER_SIZE];
        /* 2. Initialize buffer */

        producers = new Producer[numProducers];
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Producer(this);
        } /* 3. Create producer thread(s) */

        consumers = new Consumer[numConsumers];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer(this);
        } /* 4. Create consumer thread(s) */

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Semaphore full = new Semaphore(0, true);
        Semaphore empty = new Semaphore(BUFFER_SIZE, true);
        //Mutex 

        try {
            int _sleepLength = Integer.parseInt(args[0]);
            int _numProducers = Integer.parseInt(args[1]);
            int _numConsumers = Integer.parseInt(args[2]);
            // 1. Get command line arguments. Note: Zero-based in Java

            Buffer buffer = new Buffer(_sleepLength, _numProducers, _numConsumers);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException x) {
            System.out.println("Incorrect usage, requires: 'Buffer int int int'");
        }

        try {
            Thread.sleep(sleepLength);
            /* 5. Sleep */

        } catch (InterruptedException iE) {
            System.out.println("Woken via interupt");
        }

        System.exit(0);
        /* 6. Exit */
    }

    boolean insert_item(int item) {
        boolean success = false;
        /* insert item into buffer
         return 0 if successful, otherwise
         return -1 indicating an error condition */
        System.out.println("insert");
        return success;
    }

    boolean remove_item(int item) {
        boolean success = false;
        /* remove an object from buffer
         placing it in item
         return 0 if successful, otherwise
         return -1 indicating an error condition */
                System.out.println("remove");
        return success;
    }

}
