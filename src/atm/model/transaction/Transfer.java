package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;

public class Transfer extends Transaction{
    private Client to;
    private Money amount;

    public Transfer(Atm atm, Client client) {
        super(atm, client);
    }

    //TODO:get client, amount for transfer
    public void makeTransfer(){
        to = null;
        client.setBalance(new Money(client.getBalance().getCents() - amount.getCents()));
        to.setBalance(new Money(to.getBalance().getCents() + amount.getCents()));
        client.updateInDB();
        to.updateInDB();
    }

    //TODO:get to, amount for getSpecificsFromCustomer Transfer
    protected Message getSpecificsFromCustomer() {
        to = null;
        amount = null;
       return new Message(Message.MessageCode.TRANSFER, id, client, to, amount);
    }

    //TODO:write correct check for Transfer
    protected Check completeTransaction() {
        return new Check(this.atm, this.client.getCard(), this, this.client.getBalance()) {
            {
                detailsPortion = new String[2];
                detailsPortion[0] = "TRANSFER FROM: " +
                        client.toString() +
                        " TO: " + "";
                detailsPortion[1] = "AMOUNT: " + amount;
            }
        };
    }

}
