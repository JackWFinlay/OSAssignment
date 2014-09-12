package buffer;

/**
 * Manages consumer threads
 *
 * @author Jack Finlay ID: 1399273
 */
public class Consumer implements Runnable {

    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Consumer Thread started.");
        buffer.consumer();

    }
}
