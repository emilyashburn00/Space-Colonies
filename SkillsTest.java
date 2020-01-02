// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

import student.TestCase;
/**
 * This class tests the Skills class
 * 
 * @author emily
 * @version 2019.11.07
 */
public class SkillsTest extends TestCase {
    private Skills skill1;
    private Skills skill2;
    /**
     * sets up the test case
     */
    public void setUp()
    {
        skill1 = new Skills(2, 2, 3);
        skill2 = new Skills(4, 4, 4);
    }
    /**
     * test the getter methods and tostring method
     */
    public void testGetters()
    {
        assertEquals(skill1.getAgriculture(), 2);
        assertEquals(skill1.getMedicine(), 2);
        assertEquals(skill1.getTechnology(), 3);
        assertEquals(skill1.toString(), "A:2 M:2 T:3");
    }
    /**
     * test the isBelow method
     */
    public void testBelow()
    {
        //when all below
        assertTrue(skill1.isBelow(skill2));
        //when agr is below
        skill2 = new Skills(1, 2, 3);
        assertFalse(skill1.isBelow(skill2));
        //when med is below
        skill2 = new Skills(2, 1, 3);
        assertFalse(skill1.isBelow(skill2));
        //when tech is below
        skill2 = new Skills(2, 2, 1);
        assertFalse(skill1.isBelow(skill2));
        //when all are equal
        skill2 = new Skills(1, 2, 3);
        assertFalse(skill1.isBelow(skill2));
    }
    /**
     * test the equals method
     */
    public void testEquals()
    {
        //when diff class
        String diffClass = "diffClass";
        assertFalse(skill1.equals(diffClass));
        //when null
        diffClass = null;
        assertFalse(skill1.equals(diffClass));
        //when it's this
        assertTrue(skill1.equals(skill1));
        //when agr is not equal
        skill2 = new Skills(1, 2, 3);
        assertFalse(skill1.equals(skill2));
        //when med is not equal
        skill2 = new Skills(2, 1, 3);
        assertFalse(skill1.equals(skill2));
        //when tech is not equal
        skill2 = new Skills(2, 2, 1);
        assertFalse(skill1.equals(skill2));
        //when all are equal
        skill2 = new Skills(2, 2, 3);
        assertTrue(skill1.equals(skill2));
    }
}
