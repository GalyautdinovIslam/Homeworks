package firstSemester.interfaces;

import firstSemester.interfaces.Exceptions.HandsOverloadException;
import firstSemester.interfaces.interfaces.InterfaceForWeapon;


/**
 * Main class for running a program which create four AK47-weapon-objects
 *
 * @author Galyautdinov Islam
 * @version main_v1.1.0
 */
public class Main {
    /**
     * Main method
     *
     * @param args Parameter for starting values.
     */
    public static void main(String[] args) throws HandsOverloadException {
        InterfaceForWeapon weapon1 = new AK47("AK47-1", 23, false);
        InterfaceForWeapon weapon2 = new AK47("AK47-2", 0, true);
        Firearm weapon3 = new AK47("AK47-3", 21, true);
        Firearm weapon4 = new AK47("AK47-4", 30, false);
        Firearm[] inventory = new AK47[2];
        //inventory[0] = weapon3;
        if (inventory[0] != null) {
            throw new HandsOverloadException("Your hand are already in use");
        } else {
            inventory[1] = weapon4;
        }
    }
}
