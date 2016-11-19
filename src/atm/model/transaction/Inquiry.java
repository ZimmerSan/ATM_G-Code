package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

public class Inquiry extends Transaction{

    private Client from;

    public Inquiry(Atm atm, Client client) {
        super(atm, client);
    }

    //TODO:getFrom for getSpecificsFromCustomer Inquiry
    protected Message getSpecificsFromCustomer() {
        from = null;
        return new Message(Message.MessageCode.INQUIRY, id, from, null, new Money(0));
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
