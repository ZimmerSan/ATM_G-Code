package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.Card;
import atm.model.shared.Check;
import atm.model.shared.Message;
import atm.model.shared.Money;

/**
 * Created by KOKOWKA on 15.11.2016.
 */
public class Transfer extends Transaction{
    private int from;
    private int to;
    private Money amount;

    public Transfer(Atm atm, Card card, String pin) {
        super(atm, card, pin);
    }

    //TODO:getFrom,to and amount for getSpecificsFromCustomer Transfer
    protected Message getSpecificsFromCustomer() {
        from = 0;
        to = 1;
        amount = new Money(100);
       return new Message(Message.MessageCode.TRANSFER, card, pin, id, from, to, amount);
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
