package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.Check;
import atm.model.shared.Client;
import atm.model.shared.Message;
import atm.model.shared.Money;


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

    public abstract long performTransaction() throws Exception;

    public void setTo(Client client){
        this.to = client;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public abstract Message getSpecificsFromCustomer();

    protected abstract Check completeTransaction();

}
