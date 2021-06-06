package firstSemester.interfaces.interfaces;

/**
 * An interface for firearms that can clean the barrel of a weapon
 *
 * @author Galyautdinov Islam
 * @version interface_v3.1.0
 * @since main_v1.1.0
 */
public interface InterfaceForWeapon extends Shootable, Rechargeable {
    /**
     * Method for cleaning the barrel of a firearm using a ramrod.
     *
     * @return Boolean variable for the value of the contamination of the barrel.
     */
    boolean cleanBarrel();
}
