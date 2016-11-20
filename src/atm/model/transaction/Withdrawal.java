package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

public class Withdrawal extends Transaction{

    private Money amount;

    public Withdrawal(Atm atm, Client client) {
        super(atm, client);
    }
    //TODO:get amount for makeWithdrawal
    public void makeWithdrawal(){
        amount = null;
        client.setBalance(new Money(client.getBalance().getCents() - amount.getCents()));
    }
    //TODO:get amount for getSpecificsFromCustomer Withdrawal
    protected Message getSpecificsFromCustomer(){
        amount = null;
        return new Message(Message.MessageCode.WITHDRAWAL, id, client, null, amount);
    }


    protected Check completeTransaction() {
        return new Check(this.atm, this.client.getCard(), this, this.client.getBalance()) {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "WITHDRAWAL FROM: " +client.toString();
                detailsPortion[1] = "AMOUNT: " + amount;
            }
        };
    }

}
