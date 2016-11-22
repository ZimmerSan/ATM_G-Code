package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

public class Inquiry extends Transaction{

    public Inquiry(Atm atm, Client client, Money money) {
        super(atm, client, null, money);
    }

    @Override
    public void performTransaction() {
        // TODO: 23-Nov-16 complete
    }

    protected Message getSpecificsFromCustomer() {
        return new Message(Message.MessageCode.INQUIRY, transactionId, from, to, new Money(0));
    }

    public Check completeTransaction() {
        return new Check(this.atm, this.from.getCard(), this, this.from.getBalance()) {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "INQUIRY FROM: " + from.toString();
                detailsPortion[1] = "";
            }
        };
    }

}
