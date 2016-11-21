package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;


public abstract class Transaction {
    protected Atm atm;
    protected Client from;
    protected Client to;
    protected int id;
    protected Money money;

    private static int nextId = 1;

    protected Transaction(Atm atm, Client from, Client to, Money money) {
        this.atm = atm;
        this.from = from;
        this.to = to;
        this.id = nextId ++;
        this.money = money;
    }

    public void setTo(Client client){
        this.to = client;
    }

    public int getId() {
        return id;
    }

    protected abstract Message getSpecificsFromCustomer();

    protected abstract Check completeTransaction();

}
