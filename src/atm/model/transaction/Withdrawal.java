package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.Card;
import atm.model.shared.Check;
import atm.model.shared.Message;
import atm.model.shared.Money;

/**
 * Created by KOKOWKA on 15.11.2016.
 */
public class Withdrawal extends Transaction{

    private int from;
    private Money amount;

    public Withdrawal(Atm atm, Card card, String pin) {
        super(atm, card, pin);
    }

//TODO:getFrom, amount for getSpecificsFromCustomer Withdrawal
    protected Message getSpecificsFromCustomer(){
        from = 0;
        amount = new Money(100);
        return new Message(Message.MessageCode.WITHDRAWAL, card, pin, id, from, -1, amount);
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
