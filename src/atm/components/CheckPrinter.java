package atm.components;

/**
 * Created by KOKOWKA on 14.11.2016.
 */
public class CheckPrinter {
    /** Constructor
     */
    public CheckPrinter()
    {
    }

    /** Print a receipt
     *
     *  @param receipt object containing the information to be printed
     */
    public void printReceipt(String receipt) {
       System.out.println(receipt);
    }
}
