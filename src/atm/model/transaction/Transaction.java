package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;
import atm.model.shared.exception.MoneyException;


public abstract class Transaction {
    protected Atm atm;
    protected Client from;
    protected Client to;
    protected int transactionId;
    protected Money money;

    private static int nextId = 1;

    protected Transaction(Atm atm, Client from, Client to, Money money) {
        this.atm = atm;
        this.from = from;
        this.to = to;
        this.transactionId = nextId ++;
        this.money = money;
    }

    public abstract void performTransaction() throws MoneyException;

    public void setTo(Client client){
        this.to = client;
    }

    public int getTransactionId() {
        return transactionId;
    }

    protected abstract Message getSpecificsFromCustomer();

    protected abstract Check completeTransaction();

}
