package atm.components;

import atm.ATM;

/**
 * Created by KOKOWKA on 14.11.2016.
 */

/** Manager for the ATM's card reader.  In a real ATM, this would
 *  manage a physical device; in this simulation, it uses classes
 *  in package simulation to simulate the device.
 */

public class CardReader {

    private atm.ATM atm;

    /** Constructor
     *
     *  @param atm the atm.ATM that owns this card reader
     */
    public CardReader(atm.ATM atm){
        this.atm = atm;
    }

    /** Read a card that has been partially inserted into the reader
     *
     *  @return Card object representing information on the card if read
     *          successfully, null if not read successfully
     */
    public void readCard() {
        System.out.println("readCard");
    }

    /** Eject the card that is currently inside the reader.
     */
    public void ejectCard() {
        System.out.println("ejectCard");
    }

    /** Retain the card that is currently inside the reader for action by the
     *  bank.
     */
    public void retainCard() {
        System.out.println("retainCard");
    }


}
