package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;
import atm.model.shared.exception.MoneyException;

public class Transfer extends Transaction{

    public Transfer(Atm atm, Client from, Client to, Money money) {
        super(atm, from, to, money);
    }

    public void makeTransfer() throws MoneyException {
        long amount = (from.getBalance().getCents() - money.getCents()) / 100;
        if (amount < 0) throw new MoneyException();
        else {
            from.setBalance(new Money(amount));
            to.setBalance(new Money((to.getBalance().getCents() + money.getCents()) / 100));
            from.updateInDB();
            to.updateInDB();
        }
    }

    protected Message getSpecificsFromCustomer() {
       return new Message(Message.MessageCode.TRANSFER, id, from, to, money);
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
