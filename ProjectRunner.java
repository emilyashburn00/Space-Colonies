// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Emily Ashburn
package spacecolonies;

import java.io.FileNotFoundException;

import bsh.ParseException;
/**
 * Class that runs the whole project
 * 
 * @author Emily Ashburn
 * @version 2019.11.07
 */
public class ProjectRunner {

    public static void main(String args[]) throws ParseException,
            SpaceColonyDataException, FileNotFoundException {
        if (args.length >= 2) {
            new ColonyReader(args[0], args[1]);
        }
        new ColonyReader("input.txt", "planets.txt");
    }
}
