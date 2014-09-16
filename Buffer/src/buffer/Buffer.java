package buffer;

import java.util.concurrent.Semaphore;

/**
 * A Java implementation of the bounded buffer problem.
 *
 * @author Jack Finlay ID: 1399273
 */
public class Buffer extends Thread {

    private static final int BUFFER_SIZE = 5;
    private static final int RAND_MAX = 1000;

    private static int sleepLength, numProducers, numConsumers, item;
    private static int[] bufferArray;

    private static Semaphore full, empty, mutex;

    private final Producer[] producers;
    private final Consumer[] consumers;

    /**
     * The Main method.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        full = new Semaphore(0, false);
        empty = new Semaphore(BUFFER_SIZE, false);
        mutex = new Semaphore(1, false);

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
            Thread.sleep(sleepLength*1000);
            /* 5. Sleep */

        } catch (InterruptedException iE) {
            System.out.println("Woken via interupt");
        }

        System.exit(0);
        /* 6. Exit */
    }
    
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

    boolean insert_item(int item) {
        boolean success = false;

        try {
            empty.acquire();
            mutex.acquire();
            // Critical Section:
            bufferArray[full.availablePermits()] = item;

            mutex.release();
            full.release();

            // Non-Critical:
            success = true;
        } catch (InterruptedException ex) {
            System.out.println("Interupt");
        }

        return success;
    }

    boolean remove_item(int item) {
        boolean success = false;

        try {
            full.acquire();
            mutex.acquire();
            // Critical Section:
            Buffer.item = bufferArray[full.availablePermits()];
            bufferArray[full.availablePermits()] = 0;

            mutex.release();
            empty.release();

            // Non-Critical:
            success = true;
        } catch (InterruptedException ex) {
            System.out.println("Interupt");
        }

        return success;
    }

    public void producer() {

        while (true) {
            try {
                Thread.sleep((int) (Math.random() * RAND_MAX));
            } catch (InterruptedException ex) {
                System.out.println("Something.");
            }

            int rand = (int) (Math.random() * RAND_MAX);

            if (!insert_item(rand)) {
                System.out.println("Report Error Condition - insert fail");
            } else {
                System.out.println("Producer produced " + rand);
            }
        }
    }

    public void consumer() {
        while (true) {
            try {
                Thread.sleep((int) (Math.random() * RAND_MAX));
            } catch (InterruptedException ex) {
            }

            if (!remove_item(item)) {
                System.out.println("Report Error Condition - consume fail");
            } else {
                System.out.println("Consumer consumed " + item);
            }
        }
    }

}
