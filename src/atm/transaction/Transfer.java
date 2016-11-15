package atm.transaction;

/**
 * Created by KOKOWKA on 15.11.2016.
 */
public class Transfer extends Transaction{
    private int from;
    private int to;
    //private Money amount;

    public Transfer(/*ATM atm, Session session, Card card, int pin*/) {
        //super(atm, session, card, pin);
    }


    protected void getSpecificsFromCustomer(){
       System.out.println("getSpecificsFromCustomer");
    }


    protected void completeTransaction() {
        System.out.println("completeTransaction");
    }

}
