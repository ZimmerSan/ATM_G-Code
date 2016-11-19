package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

public class Inquiry extends Transaction{

    public Inquiry(Atm atm, Client client) {
        super(atm, client);
    }

    protected Message getSpecificsFromCustomer() {
        return new Message(Message.MessageCode.INQUIRY, id, client, null, new Money(0));
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
