package BlockingQueue;

import java.util.Random;

/**
 * Created by Petro Karabyn
 */

public class Producer implements Runnable {

    private Queue<Integer> queue;
    private String threadId;
    private Random random;

    public Producer (Queue<Integer> queue) {
        this.queue = queue;
        random = new Random();
    }

    public void run() {
        threadId = "Producer-" + Thread.currentThread().getId();
        try {
            for (int i = 0; i < 5; i++) {
                queue.push(produce());
                Thread.sleep(1000);
            }
            queue.push(-1);  // signal end of producing
            System.out.println(threadId + " STOPPED.");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private Integer produce() {
        Integer number = random.nextInt(100);
        System.out.println(threadId + ": Producing number => " + number);
        return number;
    }
}
