package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

public class Transfer extends Transaction{

    public Transfer(Atm atm, Client from, Client to, Money money) {
        super(atm, from, to, money);
    }

    public void makeTransfer(){
        from.setBalance(new Money((from.getBalance().getCents() - money.getCents())/100));
        to.setBalance(new Money((to.getBalance().getCents() + money.getCents())/100));
        from.updateInDB();
        to.updateInDB();
    }


    protected Message getSpecificsFromCustomer() {
       return new Message(Message.MessageCode.TRANSFER, id, from, to, money);
    }


    protected Check completeTransaction() {
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
