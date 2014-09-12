package buffer;

/**
 * A Java implementation of the bounded buffer problem.
 *
 * @author Jack Finlay ID: 1399273
 */
public class Buffer extends Thread {

    public static final int BUFFER_SIZE = 5;
    private static int sleepLength, numProducers, numConsumers;
    public static int[] buffer;

    /**
     * Default constructor.
     */
    public Buffer() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            sleepLength = Integer.parseInt(args[0]);
            numProducers = Integer.parseInt(args[1]);
            numConsumers = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect usage, requires: 'Buffer int int int'");
        }

        for (int i = 0; i < numProducers; i++) {
            new Producer();
        }
        
        for (int i = 0; i < numConsumers; i++) {
            new Consumer();
        }

    }

    int insert_item(int item) {
        /* insert item into buffer
         return 0 if successful, otherwise
         return -1 indicating an error condition */
        return 0;
    }

    int remove_item(int item) {
        /* remove an object from buffer
         placing it in item
         return 0 if successful, otherwise
         return -1 indicating an error condition */
        return 0;
    }

}
