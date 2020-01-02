// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

/**
 * These objects contain a string, for the planet’s name, three ints for their
 * minimum skill requirements (on a scale of 1 to 5), an array of Person objects
 * for current planet population, an int for storing the current population
 * size, and a final int for the maximum allowed capacity of the planet.
 * 
 * @author emily
 * @version 2019.11.07
 */
public class Planet implements Comparable<Planet> {

    private String name;
    private Skills minSkills;
    private Person[] population;
    private int populationSize;
    private final int capacity;

    /**
     * Creates a new planet object
     * 
     * @param planetName the planet name
     * @param planetAgri the planet minAgriculture score
     * @param planetMedi the planet minMedicine score
     * @param planetTech the planet minTechnology score
     * @param planetCap the planet max capacity
     */
    public Planet(String planetName, int planetAgri, int planetMedi,
            int planetTech, int planetCap) {
        capacity = planetCap;
        name = planetName;
        minSkills = new Skills(planetAgri, planetMedi, planetTech);
        population = new Person[planetCap];
    }

    /**
     * Sets the name of the planet
     * 
     * @param planetName the name you wnat it to be set to
     */
    public void setName(String planetName) {
        name = planetName;
    }

    /**
     * Getter method for name of planet
     * 
     * @return the name of the planet
     */
    public String getName() {
        return name;
    }

    /**
     * getter method for skills min
     * 
     * @return minimum skills
     */
    public Skills getSkills() {
        return minSkills;
    }

    /**
     * getter method for population
     * 
     * @return person array
     */
    public Person[] getPopulation() {
        return population;
    }

    /**
     * getter method for the pop size
     * 
     * @return the current size of the population
     */
    public int getPopulationSize() {
        return populationSize;
    }

    /**
     * getter method for max capacity
     * 
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * getter method for how many spots are available on planet
     * 
     * @return capacity - population
     */
    public int getAvailability() {
        return (capacity - populationSize);
    }

    /**
     * Whether the planet is at max cap or not
     * 
     * @return if cap = pop
     */
    public boolean isFull() {
        return (capacity == populationSize);
    }

    /**
     * This method will attempt to add a Person to the Planet. It will need to
     * check two things. First, that the colony has available space for this
     * applicant. Secondly, it will need to determine whether the applicant is
     * qualified to live on the colony
     * 
     * @param applicant the person to be added
     * @return whether the person was added or not
     */
    public boolean addPerson(Person applicant) {
        if (isFull()) {
            return false;
        } 
        else if (isQualified(applicant)) {
            population[populationSize] = applicant;
            populationSize++;
            return true;
        }
        return false;
    }

    /**
     * Sees if the applicants skills are higher than min
     * 
     * @param applicant the person to be tested
     * @return whether applicant is qualified or not
     */
    public boolean isQualified(Person applicant) {
        Skills appSkills = applicant.getSkills();
        return (appSkills.getAgriculture() >= this.minSkills.getAgriculture()
                && appSkills.getMedicine() >= this.minSkills.getMedicine()
                && appSkills.getTechnology() >= this.minSkills.getTechnology());
    }

    /**
     * Converts the planet to a string
     * 
     * @return the planet as a string
     */
    public String toString() {
        return name + ", population " + populationSize + " (cap: " + capacity
                + "), Requires: A >= " + minSkills.getAgriculture() + ", M >= "
                + minSkills.getMedicine() + ", T >= "
                + minSkills.getTechnology();
    }

    /**
     * Two planets are equal if all 5 their input fields are equal and
     * populationSize is equal.
     * 
     * @param obj the obj to find if is equal
     * @return whether they are equal
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            Planet other = ((Planet) obj);
            if (other.capacity == this.capacity
                    && other.populationSize == this.populationSize
                    && other.minSkills.equals(this.minSkills)
                    && other.name == this.name) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares the given planet's avail to this planet's
     * 
     * @param planet the planet to compare to
     * @return <0 if the given planet's availability is more >0 if avail is less
     *         =0 if avail is same
     */
    @Override
    public int compareTo(Planet planet) {
        if (planet == null) {
            throw new IllegalArgumentException();
        }
        if (this.getAvailability() > planet.getAvailability()) {
            return 1;
        } 
        else if (this.getAvailability() < planet.getAvailability()) {
            return -1;
        } 
        else {
            return 0;
        }
    }
}
