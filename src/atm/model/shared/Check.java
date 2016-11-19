
package atm.model.shared;

import atm.model.Atm;
import atm.model.transaction.Transaction;

import java.util.Date;
import java.util.Enumeration;

public abstract class Check {

    private String[] headingPortion;
    protected String[] detailsPortion;
    private String balancesPortion;

    protected Check(Atm atm, Card card, Transaction transaction, Money balance) {
        headingPortion = new String[3];
        headingPortion[0] = new Date().toString();
        headingPortion[1] = atm.getBankName();
        headingPortion[2] = "CARD " + card.getNumber() + " TRANS #" + transaction.getId();
        balancesPortion = "TOTAL BAL: " + balance;
    }
    public String toString(){
        return  headingPortion[0] +"\n"+headingPortion[1]+"\n"+headingPortion[2]+"\n"+balancesPortion;
    }
}