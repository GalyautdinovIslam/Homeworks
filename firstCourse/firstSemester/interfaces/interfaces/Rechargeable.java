package firstSemester.interfaces.interfaces;

/**
 * An interface for firearms that can be recharged
 *
 * @author Galyautdinov Islam
 * @version interface_v2.1.0
 * @since main_v1.1.0
 */
public interface Rechargeable {
    /**
     * Method for recharging.
     *
     * @return new value(maximum) of bullets in firearm magazine.
     */
    int recharge();
}
