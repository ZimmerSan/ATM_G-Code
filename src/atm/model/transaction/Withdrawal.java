package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;
import atm.model.shared.exception.MoneyException;

public class Withdrawal extends Transaction {

    public Withdrawal(Atm atm, Client from, Money money) {
        super(atm, from, null, money);
    }

    @Override
    public void performTransaction() throws MoneyException {
        // TODO: 23-Nov-16 c'mon man. Give the variables good names
        long cents = (from.getBalance().getCents() - money.getCents()) / 100;
        if (cents < 0) throw new MoneyException();
        else {
            from.setBalance(new Money(cents));
            from.updateInDB();
        }
    }

    protected Message getSpecificsFromCustomer() {
        return new Message(Message.MessageCode.WITHDRAWAL, transactionId, from, to, money);
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
