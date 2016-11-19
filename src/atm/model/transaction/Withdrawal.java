package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

/**
 * Created by KOKOWKA on 15.11.2016.
 */
public class Withdrawal extends Transaction{

    private Client from;
    private Money amount;

    public Withdrawal(Atm atm, Client client) {
        super(atm, client);
    }

//TODO:getFrom, amount for getSpecificsFromCustomer Withdrawal
    protected Message getSpecificsFromCustomer(){
        from = null;
        amount = new Money(100);
        return new Message(Message.MessageCode.WITHDRAWAL, id, from, null, amount);
    }

    //TODO:write correct check for withdrawal
    protected Check completeTransaction() {
        return new Check() {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "";
                detailsPortion[1] = "AMOUNT: " + amount;
            }
        };
    }

}
