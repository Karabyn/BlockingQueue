package BlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread safe implementation of a Queue based on LinkedList
 * using lock.
 * @param <T> the type of elements in the data structure
 * Created by Petro Karabyn
 * 15-Nov-17
 */

public class LockBlockingQueue<T> implements Queue<T> {

    private final List<T> queue;
    private final int capacity; // maximum amount of elements
    private final Lock lock;
    private final Condition notEmptyCondition;
    private final Condition notFullCondition;

    public LockBlockingQueue() {
        this(32);
    }

    public LockBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        this.queue = new LinkedList<>();
        this.capacity = capacity;
        lock = new ReentrantLock();
        notEmptyCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    public void push(T item) {
        lock.lock();
        try {
            while (isFull()) {
                try {
                    notFullCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(item);
            notEmptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public T poll() {
        lock.lock();
        try {
            while (isEmpty()) {
                try {
                    notEmptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T item = queue.remove(0);
            notFullCondition.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    private boolean isFull() {
        return queue.size() == capacity;
    }

    private boolean isEmpty() { return queue.size() == 0;}
}
