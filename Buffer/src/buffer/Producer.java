package buffer;

/**
 * Manages producer threads
 *
 * @author Jack Finlay ID: 1399273
 */
public class Producer implements Runnable {

    private static final int RAND_MAX = 1000;

    private final Buffer buffer;
    private int item;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
        new Thread(this).start();      
    }
    

    @Override
    public void run() {
        System.out.println("Producer thread started.");
        while (true) {
            try {
                Thread.sleep((int) (Math.random() * RAND_MAX));
            } catch (InterruptedException ex) {
                System.out.println("Something.");
            }

            item = (int) (Math.random() * RAND_MAX);

            if (!buffer.insert_item(item)) {
                System.out.println("Report Error Condition");
            } else {
                System.out.println("Producer produced " + item);
            }
        }

    }

}
