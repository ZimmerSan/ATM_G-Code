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

    protected Check completeTransaction() {
        return new Check(this.atm, this.client.getCard(), this, this.client.getBalance()) {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "INQUIRY FROM: " + client.toString();
                detailsPortion[1] = "";
            }
        };
    }

}
