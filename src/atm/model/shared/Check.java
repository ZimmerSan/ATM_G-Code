
package atm.model.shared;

import atm.model.Atm;
import atm.model.transaction.Transaction;

import java.util.Date;

public class Check {

    private String[] headingPortion;
    protected String[] detailsPortion;
    private String balancesPortion;

    public Check(Atm atm, Card card, Transaction transaction, Money balance) {
        headingPortion = new String[3];
        headingPortion[0] = new Date().toString();
        headingPortion[1] = atm.getBankName();
        headingPortion[2] = "CARD " + card.getNumber() + " TRANS #" + transaction.getTransactionId();
        balancesPortion = "TOTAL BAL: " + balance;
    }

    public String toString() {
        String res = "";
        res += headingPortion[0] + "\n" + headingPortion[1] + "\n" + headingPortion[2] + "\n" + balancesPortion;
        if (detailsPortion != null)
            for (int i = 0; i < detailsPortion.length; i++)
                res += "\n" + detailsPortion[i];
        return res;
    }
}