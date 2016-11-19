package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

/**
 * Created by KOKOWKA on 15.11.2016.
 */
public class Transfer extends Transaction{
    private Client to;
    private Money amount;

    public Transfer(Atm atm, Client client) {
        super(atm, client);
    }

    //TODO:get to and amount for getSpecificsFromCustomer Transfer
    protected Message getSpecificsFromCustomer() {
        to = null;
        amount = new Money(100);
       return new Message(Message.MessageCode.TRANSFER, id, client, to, amount);
    }

    //TODO:write correct check for Transfer
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
