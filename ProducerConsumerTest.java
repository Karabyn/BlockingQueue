package BlockingQueue;

/**
 * A class used to run a demonstration
 * of multiple producers and multiple consumers operating
 * a thread-safe implementation of a queue.
 * Created by Petro Karabyn
 */

public class ProducerConsumerTest {

    public void runDemo(Queue<Integer> queue, int numberOfProducers, int numberOfConsumers) {

        // Initializing threads
        Thread[] producers = new Thread[numberOfProducers];
        for(int i = 0; i < numberOfProducers; i++)
            producers[i] = new Thread(new Producer(queue));
        Thread[] consumers = new Thread[numberOfConsumers];
        for(int i = 0; i < numberOfConsumers; i++)
            consumers[i] = new Thread(new Consumer(queue));

        // Starting threads
        for(Thread producer : producers)
            producer.start();
        for(Thread consumer : consumers)
            consumer.start();
    }

    public static void main(String[] args) {
        ProducerConsumerTest test = new ProducerConsumerTest();
        LockBlockingQueue<Integer> lockBlockingQueue = new LockBlockingQueue<>(8);
        SynchronizedBlockingQueue<Integer> synchronizedBlockingQueue = new SynchronizedBlockingQueue<>(8);
        test.runDemo(lockBlockingQueue, 4, 5);
        // test.runDemo(synchronizedBlockingQueue, 4, 5);
    }
}