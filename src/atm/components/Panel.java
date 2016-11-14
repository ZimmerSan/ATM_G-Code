package atm.components;

import atm.ATM;

/**
 * Created by KOKOWKA on 14.11.2016.
 */
public class Panel {
    /** Constructor
     *
     *  @param atm the ATM this panel is part of
     */
    public Panel(ATM atm)
    {
        this.atm = atm;
    }

    // In a real ATM, code would be needed to sense a change in the state of the
    // switch and notify the ATM - simulated in this case by a button in the GUI

    /** Get the amount of cash in the cash dispenser from the operator at start up
     *
     *  @return dollar value of the bills in the cash dispenser (# of bills x $20)
     */

    public void getInitialCash()
    {
        System.out.println("getInitialCash");
    }

    /** ATM this panel is part of.  The ATM object will be notified when
     * the switch on the panel is turned on or off
     */
    private ATM atm;
}
