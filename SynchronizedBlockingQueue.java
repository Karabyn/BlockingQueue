package BlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Thread safe implementation of a Queue based on LinkedList
 * using synchronized key word.
 * @param <T> the type of elements in the data structure
 * Created by Petro Karabyn
 * 15-Nov-17
 */

public class SynchronizedBlockingQueue<T> implements Queue<T> {

    private final List<T> queue;
    private final int capacity; // maximum amount of elements

    public SynchronizedBlockingQueue() {
        this(32);
    }

    public SynchronizedBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void push(T item) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(isEmpty()) {
            notifyAll();
        }
        queue.add(item);
    }

    public synchronized T poll() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(isFull()) {
            notifyAll();
        }
        return queue.remove(0);
    }

    public synchronized int size() {
        return queue.size();
    }

    private boolean isFull() {
        return queue.size() == capacity;
    }

    private boolean isEmpty() { return queue.size() == 0;}
}
