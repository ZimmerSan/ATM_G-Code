package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

public class Withdrawal extends Transaction{

    public Withdrawal(Atm atm, Client from, Client to, Money money) {
        super(atm, from, to, money);
    }

    public void makeWithdrawal(){
        from.setBalance(new Money((from.getBalance().getCents() - money.getCents())/100));
    }

    protected Message getSpecificsFromCustomer(){
        return new Message(Message.MessageCode.WITHDRAWAL, id, from, to, money);
    }


    protected Check completeTransaction() {
        return new Check(this.atm, this.from.getCard(), this, this.from.getBalance()) {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "WITHDRAWAL FROM: " +from.toString();
                detailsPortion[1] = "AMOUNT: " + money;
            }
        };
    }

}
