package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;
import atm.model.shared.exception.MoneyException;

import static atm.tools.Constants.ERR_UNKNOWN;

public class Withdrawal extends Transaction {

    public Withdrawal(Atm atm, Client from, Money money) {
        super(atm, from, null, money);
    }

    @Override
    public long performTransaction() throws Exception {
        long balanceAfterTransaction = (from.getBalance().getCents() - money.getCents()) / 100;
        if (balanceAfterTransaction < 0 || money.getCents() > atm.getPresentMoney().getCents()) throw new MoneyException();
        else {
            from.setBalance(new Money(balanceAfterTransaction));
            try {
                from.updateInDB();
            } catch (Exception e) {
                throw new Exception(ERR_UNKNOWN);
            }
            atm.getPresentMoney().subtract(money);
        }
        return money.getCents();
    }

    public Check completeTransaction() {
        return new Check(this.atm, this.from.getCard(), this, this.from.getBalance()) {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "WITHDRAWAL FROM: " + from.toString();
                detailsPortion[1] = "AMOUNT: " + money;
            }
        };
    }

}
