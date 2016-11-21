package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;
import atm.model.shared.exception.MoneyException;

public class Withdrawal extends Transaction{

    public Withdrawal(Atm atm, Client from, Client to, Money money) {
        super(atm, from, to, money);
    }

    public void makeWithdrawal() throws MoneyException{
        long cents = (from.getBalance().getCents() - money.getCents())/100;
        if(cents<0) throw new MoneyException();
        else {
            from.setBalance(new Money(cents));
            from.updateInDB();
        }
    }

    protected Message getSpecificsFromCustomer(){
        return new Message(Message.MessageCode.WITHDRAWAL, id, from, to, money);
    }


    public Check completeTransaction() {
        return new Check(this.atm, this.from.getCard(), this, this.from.getBalance()) {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "WITHDRAWAL FROM: " +from.toString();
                detailsPortion[1] = "AMOUNT: " + money;
            }
        };
    }

}
