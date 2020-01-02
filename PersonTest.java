// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

import student.TestCase;
/**
 * A test class for the Person class
 * @author emily
 * @version 2019.11.08
 */
public class PersonTest extends TestCase {
    private Person low;
    private Person middle;
    private Person high;
    /**
     * sets up the test class
     */
    public void setUp()
    {
        low = new Person("First", 1, 1, 1, "Planet1");
        middle = new Person("Second", 2, 2, 2, "");
        high = new Person("Third", 3, 3, 3, "Planet3");
    }
    /**
     * test the getter methods
     */
    public void testGetters()
    {
        assertEquals(low.getPlanetName(), "Planet1");
        assertEquals(low.getName(), "First");
        Skills skill = new Skills(1, 1, 1);
        assertEquals(low.getSkills(), skill);
    }
    /**
     * test the toString method
     */
    public void testToString()
    {
        //when there is no planet
        assertEquals("No-Planet Second A:2 M:2 T:2", middle.toString());
        //when there is
        assertEquals("Third A:3 M:3 T:3 Wants: Planet3", high.toString());
    }
    /**
     * test the equals method
     */
    public void testEquals()
    {
        //when different class
        String diffClass = "hallo";
        assertFalse(low.equals(diffClass));
        //when null
        diffClass = null;
        assertFalse(low.equals(diffClass));
        //when this
        assertTrue(low.equals(low));
        //when the skills are different
        Person low2 =  new Person("First", 2, 1, 1, "Planet1");
        assertFalse(low.equals(low2));        
        //when the name is different
        low2 =  new Person("Second", 1, 1, 1, "Planet1");
        assertFalse(low.equals(low2));
        //when the planet is different
        low2 =  new Person("First", 1, 1, 1, "PlanetA");
        assertFalse(low.equals(low2));
        //when actually the same
        low2 =  new Person("First", 1, 1, 1, "Planet1");
        assertTrue(low.equals(low2));
    }

}
