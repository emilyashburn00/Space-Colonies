// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

import student.TestCase;

/**
 * A test class for the Planet class
 * 
 * @author emily
 * @version 2019.11.08
 */
public class PlanetTest extends TestCase {
    private Planet planet1;
    private Planet planet2;
    private Person person1;

    /**
     * sets up the test class
     */
    public void setUp() {
        planet1 = new Planet("Krypton", 2, 2, 2, 2);
        planet2 = new Planet("Asgard", 3, 3, 3, 5);
        person1 = new Person("Leia", 3, 3, 3, "");
    }

    /**
     * test the getter and setter methods as well as the isFull method
     */
    public void testGetterSetter() {
        assertEquals(planet1.getName(), "Krypton");
        planet1.setName("Alderaan");
        assertEquals(planet1.getName(), "Alderaan");
        Skills skills1 = new Skills(2, 2, 2);
        assertTrue(planet1.getSkills().equals(skills1));
        planet1.addPerson(person1);
        planet2.addPerson(person1);
        assertTrue(
                planet1.getPopulation()[0].equals(planet2.getPopulation()[0]));
        assertEquals(planet1.getPopulationSize(), planet2.getPopulationSize());
        assertEquals(planet1.getCapacity(), 2);
        assertEquals(planet1.getAvailability(), 1);
        assertFalse(planet1.isFull());
        planet1.addPerson(person1);
        assertTrue(planet1.isFull());
    }

    /**
     * test the addPerson method
     */
    public void testAddPerson() {
        // when theapplicant isn't qualified
        person1 = new Person("Thor", 2, 2, 2, "");
        assertFalse(planet2.addPerson(person1));
        // when the applicant is qualified
        assertTrue(planet1.addPerson(person1));
        // when the planet is full
        assertTrue(planet1.addPerson(person1));
        assertFalse(planet1.addPerson(person1));
    }

    /**
     * test the toString method
     */
    public void testTostring() {
        assertEquals(
                "Krypton, population 0 (cap: 2), "
                + "Requires: A >= 2, M >= 2, T >= 2",
                planet1.toString());
    }

    /**
     * test the equals method
     */
    public void testEquals() {
        // when diff class
        String diffClass = "diffClass";
        assertFalse(planet1.equals(diffClass));
        // when null
        diffClass = null;
        assertFalse(planet1.equals(diffClass));
        // when it's this
        assertTrue(planet1.equals(planet1));
        // when name is not equal
        planet2 = new Planet("Alderaan", 2, 2, 2, 2);
        assertFalse(planet1.equals(planet2));
        // when skills are not equal
        planet2 = new Planet("Krypton", 1, 2, 2, 2);
        assertFalse(planet1.equals(planet2));
        // when popSize is not equal
        planet2 = new Planet("Krypton", 2, 2, 2, 2);
        planet2.addPerson(person1);
        assertFalse(planet1.equals(planet2));
        // when max capacity is not equal
        planet2 = new Planet("Krypton", 2, 2, 2, 3);
        assertFalse(planet1.equals(planet2));
        // when all are equal
        planet2 = new Planet("Krypton", 2, 2, 2, 2);
        assertTrue(planet1.equals(planet2));
    }

    /**
     * test the isQualified method
     */
    public void testIsQualified() {
        person1 = new Person("person1", 2, 1, 1, "");
        assertFalse(planet1.isQualified(person1));
        person1 = new Person("person1", 3, 2, 1, "");
        assertFalse(planet1.isQualified(person1));
    }

    /**
     * test the compareTo method
     */
    public void testCompareTo() {
        // when null
        planet2 = null;
        Boolean bools = false;
        try {
            planet1.compareTo(planet2);
        } 
        catch (IllegalArgumentException e) {
            bools = true;
        }
        assertTrue(bools);
        // when avail is the same
        planet2 = new Planet("Alderaan", 2, 2, 2, 2);
        assertEquals(planet1.compareTo(planet2), 0);
        // when planet2 is bigger
        planet2 = new Planet("Alderaan", 2, 2, 2, 3);
        assertEquals(planet1.compareTo(planet2), -1);
        // when planet2 is smaller
        planet2 = new Planet("Alderaan", 2, 2, 2, 1);
        assertEquals(planet1.compareTo(planet2), 1);
    }

}
