// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

import queue.EmptyQueueException;
import queue.QueueInterface;

/**
 * This data structure implements QueueInterface with a circular array
 * implementation. It provides default queue behavior, such as enqueue, dequeue,
 * getFront, and isEmpty.
 * 
 * @author Emily Ashburn
 * @version 2019.11.07
 * @param <T>
 */
public class ArrayQueue<T> implements QueueInterface<T> {
    private T[] queue;
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * the max capacity for this array
     */
    public static final int MAX_CAPACITY = 100;
    private int enqueueIndex;
    private int dequeueIndex;
    private int size;

    /**
     * creates a new queue and sets dequeue to 0 and enqueue to cap
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        queue = (T[]) new Object[DEFAULT_CAPACITY + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;
    }

    /**
     * "Resets" the object
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        queue = (T[]) new Object[DEFAULT_CAPACITY + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;
    }

    /**
     * tests if it's at capacity
     */
    private boolean isFull() {
        return (this.getSize() + 1 == queue.length);
    }

    /**
     * takes the element in the front
     * 
     * @return the element in the front
     */
    @Override
    public T dequeue() {
        T item = this.getFront();
        queue[dequeueIndex] = null;
        dequeueIndex = this.incrementIndex(dequeueIndex);
        size--;
        return item;
    }

    /**
     * Adds param onto back of queue
     * 
     * @param element the element to be added
     */
    @Override
    public void enqueue(T element) {
        this.ensureCapacity();
        queue[enqueueIndex] = element;
        enqueueIndex = this.incrementIndex(enqueueIndex);
        size++;
    }

    /**
     * Increments the given number so it circles around
     * 
     * @param index the index to be incrementes
     * @return the incremented index
     */
    private int incrementIndex(int index) {
        return ((index + 1) % queue.length);
    }

    /**
     * this sees if the queue is full and will double the cap
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (isFull()) {
            int num = 0;
            if (size * 2 + 1 > MAX_CAPACITY) {
                throw new IllegalStateException();
            }
            T[] newQueue = (T[]) new Object[this.size * 2 + 1];
            for (int i = dequeueIndex; i != enqueueIndex; i = incrementIndex(
                    i)) {
                newQueue[num] = queue[i];
                num++;
            }
            queue = newQueue;
            enqueueIndex = this.size;
            dequeueIndex = 0;
        }
    }

    /**
     * Gets the item in front (does nomt remove it)
     * 
     * @return the item in front
     */
    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return queue[dequeueIndex];
    }

    /**
     * Returns true if queue is empty
     * 
     * @return whether it's empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns true if object is same or has same contents
     * 
     * @param obj the object to be checked
     * @return whether two queues are equal
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass().equals(obj.getClass())) {
            ArrayQueue<T> other = ((ArrayQueue<T>) obj);
            if (other.getSize() == this.getSize()) {
                for (int i = 0; i < this.getSize(); i++) {

                    T myElement = queue[(dequeueIndex + i) % queue.length];

                    T otherElement = other.queue[(other.dequeueIndex + i)
                            % other.queue.length];

                    if (!myElement.equals(otherElement)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Returns how many objects are in queue
     * 
     * @return the size of the queue
     */
    public int getSize() {
        return size;
    }

    /**
     * returns the current capacity of the queue
     * 
     * @return the length of the queue
     */
    public int getLength() {
        return queue.length;
    }

    /**
     * Converts the queue into an array
     * 
     * @return the converted array
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        int index = dequeueIndex;

        T[] newArray = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = (T) queue[index];
            index = incrementIndex(index);
        }
        final T[] finalArray = (T[]) newArray;
        for (int i = 0; i < size; i++) {
            finalArray[i] = (T) newArray[i];
        }
        return finalArray;
    }

    /**
     * converts the queue as a string
     * 
     * @return string, the new string
     */
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        int index = dequeueIndex;
        StringBuilder builder = new StringBuilder("[");
        while (index != enqueueIndex - 1) {
            builder.append(queue[index].toString() + ", ");
            index = incrementIndex(index);
        }
        builder.append(queue[enqueueIndex - 1].toString() + "]");
        return builder.toString();
    }
}
