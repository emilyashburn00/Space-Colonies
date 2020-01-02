// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)

package spacecolonies;

import java.util.Scanner;

import bsh.ParseException;

import java.io.FileNotFoundException;
import java.io.File;

/**
 * The ColonyReader parses the input data from two text files. It generates the
 * planets and queue of applicants based on one file of comma separated values
 * about applicants and the other about each planet. Then it gives SpaceWindow
 * this queue in order to tie everything together.
 * 
 * @author emily
 * @version 2019.11.07
 */
public class ColonyReader {
    private Planet[] planets;
    private ArrayQueue<Person> queue;

    /**
     * Creates a colony and window based on input files
     * 
     * @param applicantFileName the input file for applicant
     * @param planetFileName    the input file for planet
     * @throws SpaceColonyDataException
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public ColonyReader(String applicantFileName, String planetFileName)
            throws FileNotFoundException, ParseException,
            SpaceColonyDataException {
        queue = readQueueFile(applicantFileName);
        planets = readPlanetFile(planetFileName);
        ColonyCalculator calc = new ColonyCalculator(queue, planets);
        new SpaceWindow(calc);
    }

    /**
     * Reads the planet file
     * 
     * @param fileName
     * @return an array of planets
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws SpaceColonyDataException
     */
    private Planet[] readPlanetFile(String fileName)
            throws FileNotFoundException, ParseException,
            SpaceColonyDataException {
        Planet[] planetArray = new Planet[ColonyCalculator.NUM_PLANETS + 1];
        Scanner file = new Scanner(new File(fileName));
        String line = "";
        int count = 0;
        while (file.hasNextLine()) {
            line = file.nextLine();
            String[] str = line.split(", *");
            if (str.length != 5) {
                file.close();
                throw new ParseException();
            }
            if (!(this.isInSkillRange(Integer.parseInt(str[1]),
                    Integer.parseInt(str[2]), Integer.parseInt(str[3])))) {
                file.close();
                throw new SpaceColonyDataException(line);
            }
            planetArray[count + 1] = new Planet(str[0],
                    Integer.parseInt(str[1]), Integer.parseInt(str[2]),
                    Integer.parseInt(str[3]), Integer.parseInt(str[4]));
            count++;

        }
        if (count < ColonyCalculator.NUM_PLANETS) {
            file.close();
            throw new SpaceColonyDataException(line);
        }
        file.close();
        return planetArray;
    }

    /**
     * Reads the applicant file
     * 
     * @param fileName the input file name
     * @return an arrayqueue of people
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws SpaceColonyDataException
     */
    private ArrayQueue<Person> readQueueFile(String fileName)
            throws FileNotFoundException, ParseException,
            SpaceColonyDataException {
        ArrayQueue<Person> people = new ArrayQueue<Person>();
        Scanner file = new Scanner(new File(fileName));
        while (file.hasNextLine()) {
            String line = file.nextLine();
            String[] str = line.split(", *", -1);
            if (str.length != 5) {
                file.close();
                throw new ParseException();
            }
            if (!(this.isInSkillRange(Integer.parseInt(str[1]),
                    Integer.parseInt(str[2]), Integer.parseInt(str[3])))) {
                file.close();
                throw new SpaceColonyDataException(line);
            }
            people.enqueue(new Person(str[0], Integer.parseInt(str[1]),
                    Integer.parseInt(str[2]), Integer.parseInt(str[3]),
                    str[4]));
        }
        file.close();
        return people;
    }

    /**
     * Checks if given numbers are valid skill values
     * 
     * @param agr  the agriculture skill value
     * @param med  the medicine skill value
     * @param tech the technology skill value
     * @return true, if all three values are in range of 1 to 5
     */
    private boolean isInSkillRange(int agr, int med, int tech) {
        return isSkill(agr) && isSkill(med) && isSkill(tech);
    }

    /**
     * Private helper method that checks individual skill range
     * 
     * @param skill one of the skill values
     * @return true, if skill is in range of 1 through 5
     */
    private boolean isSkill(int skill) {
        return skill >= ColonyCalculator.MIN_SKILL_LEVEL
                && skill <= ColonyCalculator.MAX_SKILL_LEVEL;
    }

}
