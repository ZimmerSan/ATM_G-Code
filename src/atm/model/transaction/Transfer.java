package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;
import atm.model.shared.exception.MoneyException;

import static atm.tools.Constants.ERR_UNKNOWN;

public class Transfer extends Transaction{

    public Transfer(Atm atm, Client from, Client to, Money money) {
        super(atm, from, to, money);
    }

    @Override
    public long performTransaction() throws Exception {
        double balanceAfterTransaction = (from.getBalance().getCents() - money.getCents()) / 100;
        if (balanceAfterTransaction < 0) throw new MoneyException();
        else {
            from.setBalance(new Money(balanceAfterTransaction));
            to.setBalance(new Money((to.getBalance().getCents() + money.getCents()) / 100));
            try {
                from.updateInDB();
            } catch (Exception e) {
                throw new Exception(ERR_UNKNOWN);
            }
            try {
                to.updateInDB();
            } catch (Exception e) {
                from.setBalance(new Money(balanceAfterTransaction + money.getCents()/100));
                to.setBalance(new Money((to.getBalance().getCents() - money.getCents()) / 100));
                from.updateInDB();
                throw new Exception(ERR_UNKNOWN);
            }
        }
        return money.getCents();
    }

    public Message getSpecificsFromCustomer() {
       return new Message(Message.MessageCode.TRANSFER, transactionId, from, to, money);
    }

    public Check completeTransaction() {
        return new Check(this.atm, this.from.getCard(), this, this.from.getBalance()) {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "TRANSFER FROM: " +
                        from.toString() +
                        " TO: " + to.toString();
                detailsPortion[1] = "AMOUNT: " + money;
            }
        };
    }

}
