package BlockingQueue;

/**
 * Created by petro on 15-Nov-17.
 */

/**
 * FIFO data structure
 * @param <T> type of elements stored in a data structure
 */
public interface Queue<T> {

    /**
     * Retrieve an item from the top of the queue and return it.
     * @return item T
     */
    T poll();

    /**
     * Add an item to the end of a queue.
     * @param item element added to the queue
     */
    void push(T item);

    /**
     * @return number of elements currently in the queue
     */
    int size();

}
