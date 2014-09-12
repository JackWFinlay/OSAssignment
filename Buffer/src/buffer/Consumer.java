package buffer;

/**
 * Manages consumer threads
 *
 * @author Jack Finlay ID: 1399273
 */
public class Consumer implements Runnable {

    private static final int RAND_MAX = 1000;

    private final Buffer buffer;
    private int item;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
        new Thread(this).start();

    }

    @Override
    public void run() {
        System.out.println("Consumer Thread started.");
        while (true) {
            try {
                Thread.sleep((int) (Math.random() * RAND_MAX));
            } catch (InterruptedException ex) {
            }

            if (!buffer.remove_item(item)) {
                System.out.println("Report Error Condition");
            } else {
                System.out.println("Producer produced " + item);
            }
        }
    }
}
