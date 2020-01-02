// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

import queue.EmptyQueueException;
import student.TestCase;
/**
 * This class tests the ArrayQueue class
 * 
 * @author emily
 * @version 2019.11.07
 */
public class ArrayQueueTest extends TestCase {
    private ArrayQueue<String> circleQ;
    /**
     * sets up the test class
     */
    public void setUp()
    {
        circleQ = new ArrayQueue<String>();
    }
    /**
     * test the isEmpty method when it's true
     */
    public void testIsEmptyT()
    {
        assertTrue(circleQ.isEmpty());
        assertEquals(circleQ.getSize(), 0);
        assertEquals(circleQ.getLength(), 11);
    }
    /**
     * test the isEmpty method when it's false
     */
    public void testIsEmptyF()
    {
        circleQ.enqueue("first");
        circleQ.enqueue("second");
        circleQ.enqueue("third");
        circleQ.enqueue("fourth");
        assertFalse(circleQ.isEmpty());
        assertEquals(circleQ.getSize(), 4);
        assertEquals(circleQ.getLength(), 11);
        circleQ.dequeue();
        circleQ.dequeue();
        circleQ.dequeue();
        circleQ.dequeue();
        assertTrue(circleQ.isEmpty());
        assertEquals(circleQ.getSize(), 0);
    }
    /**
     * test dequeue in regular use
     */
    public void testDequeue()
    {
        circleQ.enqueue("first");
        circleQ.enqueue("second");
        circleQ.enqueue("third");
        circleQ.enqueue("fourth");
        assertFalse(circleQ.isEmpty());
        assertEquals(circleQ.getSize(), 4);
        assertEquals(circleQ.getLength(), 11);
        assertEquals(circleQ.dequeue(), "first");
        assertEquals(circleQ.getSize(), 3);
        assertEquals(circleQ.getLength(), 11);
    }
    /**
     * test the getFront()
     */
    public void testGetFront()
    {
        //when empty
        assertTrue(circleQ.isEmpty());
        boolean bools = false;
        try
        {
            circleQ.getFront();
        }
        catch (EmptyQueueException e)
        {
            bools = true;
        }
        assertTrue(bools);
        circleQ.enqueue("first");
        circleQ.enqueue("second");
        circleQ.enqueue("third");
        circleQ.enqueue("fourth");
        assertEquals(circleQ.getFront(), "first");
    }
    /**
     * test enqueue after when it need to ensurecapacity
     */
    public void testEnsureCap()
    {
        for (int i = 0; i < 30; i++)
        {
            circleQ.enqueue("ensureCap");
        }
        assertEquals(circleQ.getSize(), 30);
    }
    /**
     * test enqeueue when it's reached max cap
     */
    public void testMaxCap()
    {
        boolean bools = false;
        try
        {
            for (int i = 0; i <= ArrayQueue.MAX_CAPACITY; i++)
            {
                circleQ.enqueue("maxCap");
            }
        }
        catch (IllegalStateException e)
        {
            bools = true;
        }
        assertEquals(circleQ.getSize(), 80);
        assertTrue(bools);
    }
    /**
     * test dequeue on an empty queue 
     */
    public void testDequeueEmptyQueue()
    {
        assertTrue(circleQ.isEmpty());
        boolean bools = false;
        try
        {
            circleQ.dequeue();
        }
        catch (EmptyQueueException e)
        {
            bools = true;
        }
        assertTrue(bools);
        assertTrue(circleQ.isEmpty());
    }
    /**
     * test clear method
     */
    public void testClear()
    {
        //test clear on already empty queue
        assertTrue(circleQ.isEmpty());
        circleQ.clear();
        assertTrue(circleQ.isEmpty());
        //test clear on a queue with stuff in it
        circleQ.enqueue("first");
        circleQ.enqueue("second");
        circleQ.enqueue("third");
        circleQ.enqueue("fourth");
        assertFalse(circleQ.isEmpty());
        assertEquals(circleQ.getSize(), 4);
        circleQ.clear();
        assertTrue(circleQ.isEmpty());
    }
    /**
     * test the toArray method
     */
    public void testToArray()
    {
        //when empty
        assertTrue(circleQ.isEmpty());
        boolean bools = false;
        try
        {
            circleQ.toArray();
        }
        catch (EmptyQueueException e)
        {
            bools = true;
        }
        assertTrue(bools);
        assertTrue(circleQ.isEmpty());
        //when there's actually stuff in it
        circleQ.enqueue("first");
        circleQ.enqueue("second");
        circleQ.enqueue("third");
        circleQ.enqueue("fourth");
        Object[] newArray = circleQ.toArray();
        assertEquals(newArray[0], "first");
        assertEquals(newArray[1], "second");
        assertEquals(newArray.length, circleQ.getSize());
    }
    /**
     * test the toString method
     */
    public void testToString()
    {
        //when empty
        assertEquals("[]", circleQ.toString());
        //when there's stuff in it
        circleQ.enqueue("first");
        circleQ.enqueue("second");
        circleQ.enqueue("third");
        circleQ.enqueue("fourth");
        assertEquals("[first, second, third, fourth]", circleQ.toString());
    }
    /**
     * test the equals()
     */
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals()
    {
        //on different classes
        String diffClass = "blech";
        assertFalse(circleQ.equals(diffClass));
        //on same object
        assertTrue(circleQ.equals(circleQ));
        //on null object
        diffClass = null;
        assertFalse(circleQ.equals(diffClass));
        //on an object that's not the same length
        circleQ.enqueue("first");
        circleQ.enqueue("second");
        circleQ.enqueue("third");
        circleQ.enqueue("fourth");
        ArrayQueue<String> newQ = new ArrayQueue<String>();
        newQ.enqueue("fifth");
        newQ.enqueue("third");
        newQ.enqueue("fourth");
        assertFalse(circleQ.equals(newQ));
        //on an object that has same length but not same contents
        newQ.enqueue("first");
        assertFalse(circleQ.equals(newQ));
        //same contents but different order
        assertEquals("fifth", newQ.dequeue());
        newQ.enqueue("second");
        assertFalse(circleQ.equals(newQ));
        //same contants same order
        assertEquals("third", newQ.dequeue());
        assertEquals("fourth", newQ.dequeue());
        newQ.enqueue("third");
        newQ.enqueue("fourth");
        assertTrue(circleQ.equals(newQ));
    }
}
