package atm.transaction;

/**
 * Created by KOKOWKA on 15.11.2016.
 */
public class Transaction {
    //protected ATM atm;
    //protected Session session;
    //protected Card card;
    protected int pin;
    protected int serialNumber;
    //protected Message message;
    //protected Balances balances;
    private static final String [] TRANSACTION_TYPES_MENU =
            { "Withdrawal", "Transfer", "Balance Inquiry" };
    private static int nextSerialNumber = 1;
    private int state;
    private static final int GETTING_SPECIFICS_STATE = 1;
    private static final int SENDING_TO_BANK_STATE = 2;
    private static final int INVALID_PIN_STATE = 3;
    private static final int COMPLETING_TRANSACTION_STATE = 4;
    private static final int PRINTING_RECEIPT_STATE = 5;
    private static final int ASKING_DO_ANOTHER_STATE = 6;

    protected Transaction(/*ATM atm, Session session, Card card, int pin*/) {
        /*this.atm = atm;
        this.session = session;
        this.card = card;
        this.pin = pin;
        this.serialNumber = nextSerialNumber ++;
        this.balances = new Balances();

        state = GETTING_SPECIFICS_STATE;*/
    }


    public static Transaction makeTransaction(/*ATM atm, Session session,
                                              Card card, int pin*/) {
        /*int choice = atm.getCustomerConsole().readMenuChoice(
                "Please choose transaction type", TRANSACTION_TYPES_MENU);*/
        int choice = 4;

        switch(choice)
        {
            case 0:

                //return new Withdrawal(atm, session, card, pin);
            case 1:

                //return new Transfer(atm, session, card, pin);

            case 2:

                //return new Inquiry(atm, session, card, pin);

            default:

                return null;    // To keep compiler happy - should not happen!
        }
    }


    public boolean performTransaction() throws CardRetained {
        return false;
    }

    /*public Status performInvalidPINExtension() throws CardRetained {

    }*/


    public int getSerialNumber() {
        return serialNumber;
    }

    //protected abstract Message getSpecificsFromCustomer();

    //protected abstract Receipt completeTransaction();

    public static class CardRetained extends Exception {
        public CardRetained() {
            super("Card retained due to too many invalid PINs");
        }
    }

}
