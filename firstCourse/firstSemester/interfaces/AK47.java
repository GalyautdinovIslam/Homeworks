package firstSemester.interfaces;

import firstSemester.interfaces.Exceptions.NegativeAmmunitionException;
import firstSemester.interfaces.interfaces.InterfaceForWeapon;

import java.util.Objects;

/**
 * Class for AK47-weapon-object which extends AssaultRifle class and implements InterfaceForWeapon interface
 *
 * @author Galyautdinov Islam
 * @version class_v3.1.0
 * @since main_v1.1.0
 */
public class AK47 extends AssaultRifle implements InterfaceForWeapon {
    /**
     * Maximum value in AK47 magazine.
     */
    protected final int AMMUNITION_MAX = 30;
    /**
     * Variable for AK47's issue number.
     */
    protected String issueNumber;
    /**
     * how many bullets in magazine now?
     */
    protected int ammunition;
    /**
     * Is barrel contaminated now?
     */
    protected boolean barrelContamination;

    /**
     * AK47 class constructor.
     *
     * @param issueNumber         AK47's issue number.
     * @param ammunition          number of bullets in magazine now.
     * @param barrelContamination boolean variable about contamination of barrel.
     */
    public AK47(String issueNumber, int ammunition, boolean barrelContamination) {
        if (issueNumber != null) {
            this.issueNumber = issueNumber;
        }
        if (ammunition >= 0 && ammunition <= AMMUNITION_MAX) {
            this.ammunition = ammunition;
        }
        this.barrelContamination = barrelContamination;
    }

    /**
     * Method which describes how AK47 shoot.
     *
     * @return new value of bullets in firearm magazine.
     */
    @Override
    public int shoot() throws NegativeAmmunitionException {
        if (ammunition < 0) throw new NegativeAmmunitionException("Your weapon has negative value of ammunition");
        if (!barrelContamination) {
            System.out.println("Shot.");
            return ammunition - 1;
        } else if (ammunition == 0) {
            System.out.println("No ammunition. Please recharge you weapon.");
            return ammunition;
        } else {
            System.out.println("Barrel is dirty.");
            return ammunition;
        }
    }

    /**
     * Method which describes how AK47 are recharged.
     * new value(AMMUNITION_MAX) of bullets in firearm magazine.
     */
    @Override
    public int recharge() {
        System.out.println("Recharged.");
        return AMMUNITION_MAX;
    }

    /**
     * Method which describes barrel cleaning process.
     *
     * @return Boolean variable for the value of the contamination of the barrel.
     */
    @Override
    public boolean cleanBarrel() {
        if (barrelContamination) {
            System.out.println("Barrel was cleaned.");
        } else {
            System.out.println("Barrel is not dirty.");
        }
        return false;
    }

    /**
     * equals method for AK47 object
     *
     * @param o Second object for comparison.
     * @return true if two objects are identical else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AK47) || o == null) return false;
        AK47 ak47 = (AK47) o;
        return this.ammunition == ak47.ammunition &&
                this.barrelContamination == ak47.barrelContamination &&
                this.issueNumber.equals(ak47.issueNumber);
    }

    /**
     * hashCode method for AK47 object
     *
     * @return object's hashcode
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(issueNumber, ammunition, barrelContamination);
        return 31 * result;
    }

    /**
     * toString method for AK47 object
     *
     * @return Standard description for AK47 object in line.
     */
    @Override
    public String toString() {
        if (issueNumber == null) {
            return "Issue number not exist";
        }
        if (ammunition < 0 || ammunition > AMMUNITION_MAX) {
            return "Incorrect ammunition value";
        }
        if (barrelContamination) {
            return "Assault rifle " + issueNumber + " number have " + ammunition + " ammunition and his barrel is dirty";
        }
        return "Assault rifle " + issueNumber + " number have " + ammunition + " ammunition and his barrel is clean";
    }
}
