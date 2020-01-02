// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

/**
 * This will be thrown if data is incorrect in the input files.  
 * @author emily
 * @version 2019.11.07
 */
@SuppressWarnings("serial")
public class SpaceColonyDataException extends Exception {
    /**
     * The constructor should call super and pass it the string message.
     * @param string The message given with the exception
     */
    public SpaceColonyDataException(String string)
    {
        super(string);
    }

}
