// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

import java.util.Arrays;

import list.AList;

/**
 * This object handles all the major calculations and decision-making for the
 * program. It is in charge of handling accept and reject instructions and
 * checking that all requirements for a person are met before they are added to
 * a planet. It works together with SpaceWindow.
 * 
 * @author Emily Ashburn
 * @version 2019.11.07
 */
public class ColonyCalculator {
    /**
     * The number of planets
     */
    public static final int NUM_PLANETS = 3;
    /**
     * the minimum skill level
     */
    public static final int MIN_SKILL_LEVEL = 1;
    /**
     * the maximum skill level
     */
    public static final int MAX_SKILL_LEVEL = 5;
    private ArrayQueue<Person> applicantQueue;
    private AList<Person> rejectBus;
    private static Planet[] planets = new Planet[NUM_PLANETS + 1];

    /**
     * 
     * @param queue   the queue thats going to be calculated
     * @param planets the planets to be used in the calcluator
     * @throws IllegalArgumentException
     */
    public ColonyCalculator(ArrayQueue<Person> queue, Planet[] planets) {
        if (queue == null) {
            throw new IllegalArgumentException();
        }
        applicantQueue = queue;
        ColonyCalculator.planets = planets;
        rejectBus = new AList<Person>();
    }

    /**
     * getter method for the applicant list
     * 
     * @return the queue
     */
    public ArrayQueue<Person> getQueue() {
        return applicantQueue;
    }

    /**
     * A getter method for the planets array
     * 
     * @return the array of planets
     */
    public static Planet[] getPlanets() {
        return planets;
    }

    /**
     * 
     * @param nextPerson the person to be considered
     * @return the planet the person will be assigned to
     */
    public Planet getPlanetForPerson(Person nextPerson) {
        if (nextPerson == null) {
            return null;
        }
        if (getPlanetIndex(nextPerson.getPlanetName()) == 0) {
            return getMostAvailablePlanet(nextPerson);
        }
        Planet pref = getPreferredPlanet(nextPerson,
                getPlanetIndex(nextPerson.getPlanetName()));
        return pref;
    }

    /**
     * 
     * @param person the person to be considered
     * @param num    the index of planet they prefer
     * @return
     */
    private Planet getPreferredPlanet(Person person, int num) {
        if (planets[num].isQualified(person)) {
            return planets[num];
        }
        return null;
    }

    /**
     * returns the most available planet
     * 
     * @param person the person to be considered
     * @return the most available planet
     */
    private Planet getMostAvailablePlanet(Person person) {
        Planet[] planet = planets.clone();
        Arrays.sort(planet, 1, planet.length - 1);
        Planet p = null;
        for (int i = NUM_PLANETS; i > 0; i--) {
            p = planet[i];
            if (!p.isFull() && p.isQualified(person)) {
                return p;
            }
            p = null;
        }
        return p;
    }

    /**
     * Returns whether they are accepted or not
     * 
     * @return true if accepted
     */
    public boolean accept() {
        if (!applicantQueue.isEmpty()) {
            Person applicant = applicantQueue.getFront();
            Planet planet = this.getPlanetForPerson(applicant);
            if (planet != null) {
                planet.addPerson(applicant);
                applicantQueue.dequeue();
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the reject to the rejected list
     */
    public void reject() {
        Person applicant = applicantQueue.dequeue();
        rejectBus.add(applicant);
    }

    /**
     * Returns the planet at the space of the param
     * 
     * @param num the index of the planet
     * @return the planet at the num
     */
    public Planet planetByNumber(int num) {
        if (num >= 1 && num <= 3) {
            return planets[num];
        }
        return null;
    }

    /**
     * Returns the index the param is at
     * 
     * @param str the planet name
     * @return 0 if not found
     */
    public int getPlanetIndex(String str) {
        for (int i = 1; i < 4; i++) {
            if (planets[i].getName().contentEquals(str)) {
                return i;
            }
        }
        return 0;
    }
}
