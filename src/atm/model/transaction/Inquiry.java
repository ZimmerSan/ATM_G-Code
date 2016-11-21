package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

public class Inquiry extends Transaction{

    public Inquiry(Atm atm, Client from, Client to, Money money) {
        super(atm, from, to, money);
    }

    protected Message getSpecificsFromCustomer() {
        return new Message(Message.MessageCode.INQUIRY, id, from, to, new Money(0));
    }

    protected Check completeTransaction() {
        return new Check(this.atm, this.from.getCard(), this, this.from.getBalance()) {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "INQUIRY FROM: " + from.toString();
                detailsPortion[1] = "";
            }
        };
    }

}
