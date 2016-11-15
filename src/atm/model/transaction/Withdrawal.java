package atm.model.transaction;

/**
 * Created by KOKOWKA on 15.11.2016.
 */
public class Withdrawal extends Transaction{

    private int from;
    //private Money amount;

    public Withdrawal(/*ATM atm, Session session, Card card, int pin*/) {
        //super(atm, session, card, pin);
    }


    protected void getSpecificsFromCustomer(){
        System.out.println("getSpecificsFromCustomer");
    }


    protected void completeTransaction() {
        System.out.println("completeTransaction");
    }

}
