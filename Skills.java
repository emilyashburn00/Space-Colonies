// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of
// those who do.
// -- Emily Ashburn (emily2000)
package spacecolonies;

/**
 * These objects contain three ints for their skills(on a scale of 1 to 5).
 * 
 * @author emily
 * @version 2019.11.07
 */
public class Skills {
    private int agriculture;
    private int medicine;
    private int technology;

    /**
     * Creates a new skills with the following stored:
     * 
     * @param agr agriculture skill
     * @param med medicine skill
     * @param tech technology skill
     */
    public Skills(int agr, int med, int tech) {
        this.agriculture = agr;
        this.medicine = med;
        this.technology = tech;
    }

    /**
     * Getter method for agriculture score
     * 
     * @return the skill score for agr
     */
    public int getAgriculture() {
        return this.agriculture;
    }

    /**
     * Getter method for medicine score
     * 
     * @return the skill score for med
     */
    public int getMedicine() {
        return this.medicine;
    }

    /**
     * Getter method for technology score
     * 
     * @return the skill score for tech
     */
    public int getTechnology() {
        return this.technology;
    }

    /**
     * Figures whether all of the skills are below
     * 
     * @param other the thing beling compared
     * @return boolean, whether all skill scores are below
     */
    public boolean isBelow(Skills other) {
        return (this.agriculture <= other.agriculture
                && this.medicine <= other.medicine
                && this.technology <= other.technology);
    }

    /**
     * Converts the skills to a string
     * 
     * @return the converted string
     */
    public String toString() {
        return "A:" + agriculture + " M:" + medicine + " T:" + technology;
    }

    /**
     * Compares object to skills and returns true only if all three skill scores
     * are equal
     * 
     * @param obj the thing being compares
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
            Skills other = ((Skills) obj);
            if (other.agriculture == this.agriculture
                    && other.medicine == this.medicine
                    && other.technology == this.technology) {
                return true;
            }
        }
        return false;
    }
}