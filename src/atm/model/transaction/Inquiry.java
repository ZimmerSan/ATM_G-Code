package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

public class Inquiry extends Transaction {

    public Inquiry(Atm atm, Client client) {
        super(atm, client, null, null);
    }

    @Override
    public long performTransaction() {
        return from.getBalance().getCents();
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
