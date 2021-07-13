package firstCourse.firstSemester.interfaces.interfaces;

import firstCourse.firstSemester.interfaces.Exceptions.NegativeAmmunitionException;

/**
 * An interface for firearms that can shoot
 *
 * @author Galyautdinov Islam
 * @version interface_v1.1.0
 * @since main_v1.1.0
 */
public interface Shootable {
    /**
     * Method for shooting.
     *
     * @return new value of bullets in firearm magazine.
     */
    int shoot() throws NegativeAmmunitionException;
}
