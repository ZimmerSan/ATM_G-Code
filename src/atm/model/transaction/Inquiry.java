package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.Card;
import atm.model.shared.Check;
import atm.model.shared.Message;
import atm.model.shared.Money;

public class Inquiry extends Transaction{

    private int from;

    public Inquiry(Atm atm, Card card, String pin) {
        super(atm, card, pin);
    }

    //TODO:getFrom for getSpecificsFromCustomer Inquiry
    protected Message getSpecificsFromCustomer() {
        from = 0;
        return new Message(Message.MessageCode.INQUIRY, card, pin, id, from, -1, new Money(0));
    }

//TODO:write correct check for inquiry
    protected Check completeTransaction() {
        return new Check() {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "";
                detailsPortion[1] = "";
            }
        };
    }

}
