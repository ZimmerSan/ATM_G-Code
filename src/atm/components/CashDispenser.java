package atm.components;

/**
 * Created by KOKOWKA on 14.11.2016.
 */

/** Manager for the ATM's cash dispenser.  In a real ATM, this would
 *  manage a physical device; in this simulation,  it uses classes
 *  in package simulation to simulate the device.
 */

public class CashDispenser {
    /** Constructor
     *
     *  //@param
     */
    public CashDispenser(){

    }

    /** See if there is enough cash on hand to satisfy a request
     *
     *  //@param amount the amount of cash the customer wants
     *  @return true if at least this amount of money is available
     */
    public boolean checkCashOnHand(/*amount*/) {
        return true;
    }

    /** Dispense cash to a customer
     *
     *  //@param amount the amount of cash to dispense
     *
     *  Precondition: amount is <= cash on hand
     */
    public void dispenseCash(/*amount*/) {
        System.out.println("dispenseCash");
    }


}
