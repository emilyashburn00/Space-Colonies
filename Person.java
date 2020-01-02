// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

/**
 * These objects contain a string, for a person’s name, a skills object, and a
 * String representation of their planet preference.
 * 
 * @author emily
 * @version 2019.11.07
 */
public class Person {
    private String name;
    private Skills skills;
    private String planetPreference;

    /**
     * Creates a new person
     * 
     * @param name    the person's name
     * @param agr     the person's agriculture score
     * @param med     the person's medicine score
     * @param tech    the person's technology score
     * @param planetP the person's preferred planet
     */
    public Person(String name, int agr, int med, int tech, String planetP) {
        this.name = name;
        this.planetPreference = planetP;
        skills = new Skills(agr, med, tech);
    }

    /**
     * getter method for applicants name
     * 
     * @return the name of applicant
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for paplicants skills
     * 
     * @return the skills of the person
     */
    public Skills getSkills() {
        return skills;
    }

    /**
     * Returns the planet name
     * 
     * @return the planet preference
     */
    public String getPlanetName() {
        return planetPreference;
    }

    /**
     * Converts person to string
     * 
     * @return the person as a string
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name + " " + skills.toString());
        if (planetPreference.length() > 0) {
            builder.append(" Wants: " + planetPreference);
        } 
        else {
            builder.insert(0, "No-Planet ");
        }
        return builder.toString();
    }

    /**
     * Compares object to skills and returns true only if all three skill scores
     * are equal, the names, and planet preference are equal
     * 
     * @param obj the object to be compared
     * @return boolean, whether it's equal
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            Person other = ((Person) obj);
            if (other.skills.equals(this.skills) && this.name == other.name
                    && this.planetPreference == other.planetPreference) {
                return true;
            }

        }
        return false;
    }

}
