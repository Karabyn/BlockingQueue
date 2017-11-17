package BlockingQueue;

/**
 * Created by Petro Karabyn
 */

public class Consumer implements Runnable {

    private Queue<Integer> queue;
    private String threadId;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        threadId = "Consumer-" + Thread.currentThread().getId();
        try {
            while (true) {
                Integer number = queue.poll();
                // -1 means that Producers finished producing. (Poison Pill)
                if (number == null || number == -1) {
                    // In case of more consumers than producers, not all consumers might receive -1 signal.
                    // This line makes sure, every consumer receives (-1) signal and the program terminates.
                    if (queue.size() == 0) queue.push(-1);
                    break;
                }
                consume(number);
                Thread.sleep(1000);
            }
            System.out.println(threadId + " STOPPED.");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private void consume(Integer number) {
        System.out.println(threadId + ": Consuming number <= " + number);
    }
}
