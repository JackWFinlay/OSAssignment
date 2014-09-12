package buffer;

/**
 * Manages producer threads
 *
 * @author Jack Finlay ID: 1399273
 */
public class Producer implements Runnable {

    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Producer thread started.");
        buffer.producer();
    }

}
