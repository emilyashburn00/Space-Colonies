// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

import student.TestCase;
/**
 * Test for the colonyCalculator class
 * 
 * @author emily
 * @version 2019.11.07
 */
public class ColonyCalculatorTest extends TestCase {
    private ArrayQueue<Person> q1;
    private Planet[] planets;
    private ColonyCalculator calc;
    private Person low;
    private Person middle;
    private Person high;
    private Planet planet1;
    private Planet planet2;
    private Planet planet3;
    /**
     * this sets up the test class
     */
    public void setUp()
    {
        q1 = new ArrayQueue<Person>();
        low = new Person("First", 1, 1, 1, "Planet1");
        middle = new Person("Second", 2, 2, 2, "");
        high = new Person("Third", 3, 3, 3, "Planet3");
        q1.enqueue(low);
        q1.enqueue(middle);
        q1.enqueue(high);
        planet1 = new Planet("Planet1", 2, 2, 2, 2);
        planet2 = new Planet("Planet2", 3, 3, 3, 2);
        planet3 = new Planet("Planet3", 1, 1, 1, 2);
        planets = new Planet[4];
        planets[1] = planet1;
        planets[2] = planet2;
        planets[3] = planet3;
        calc = new ColonyCalculator(q1, planets);
    }
    /**
     * Tests the exception constructor
     */
    public void testException() {
        SpaceColonyDataException e = new SpaceColonyDataException("Error");
        assertTrue(e instanceof SpaceColonyDataException);
        assertEquals("Error", e.getMessage());
    }
    /**
     * tests the getter methods
     */
    public void testGetters()
    {
        assertEquals(calc.getQueue(), q1);
        assertEquals(ColonyCalculator.getPlanets(), planets);
        Boolean bools = false;
        try {
            calc = new ColonyCalculator(null, planets);
        }
        catch (IllegalArgumentException e)
        {
            bools = true;
        }
        assertTrue(bools);
    }
    /**
     * test the getPlanetForPerson method
     */
    public void testGetPlanetForPerson()
    {
        Person nully = null;
        Planet nelly = null;
        assertEquals(calc.getPlanetForPerson(nully), nelly);
        assertEquals(calc.getPlanetForPerson(low), null);
        assertEquals(calc.getPlanetForPerson(high), planet3);
        planet1.addPerson(middle);
        assertEquals(calc.getPlanetForPerson(middle), planet3);
    }
    /**
     * test getAvailable planet
     */
    public void testGetAvail()
    {
        //when planet is full
        planet1.addPerson(middle);
        planet1.addPerson(middle);
        planet2.addPerson(middle);
        planet2.addPerson(middle);
        planet3.addPerson(middle);
        planet3.addPerson(middle);
        assertEquals(calc.getPlanetForPerson(middle), null);
    }
    /**
     * test the accept method
     */
    public void testAccept()
    {
        assertFalse(calc.accept());
        calc.reject();
        assertTrue(calc.accept());
        assertEquals(planet1.getPopulationSize(), 0);
        assertEquals(planet2.getPopulationSize(), 0);
        assertEquals(planet3.getPopulationSize(), 1);
        assertTrue(calc.accept());
        assertFalse(calc.accept());
        assertFalse(calc.accept());
    }
    /**
     * test planetByNumber
     */
    public void testNum()
    {
        assertEquals(calc.planetByNumber(2), planet2);
        assertEquals(calc.planetByNumber(5), null);
        assertEquals(calc.planetByNumber(0), null);
    }
    /**
     * test the getPlanetIndex
     */
    public void testIndex()
    {
        assertEquals(2, calc.getPlanetIndex("Planet2"));
        assertEquals(0, calc.getPlanetIndex("Planet6"));
    }
}
