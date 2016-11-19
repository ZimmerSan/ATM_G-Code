package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.*;


public abstract class Transaction {
    protected Atm atm;
    protected Client client;
    protected int id;
    protected Message message;
    protected Money money;

    private enum TRANSACTION_TYPES {
        Withdrawal, Transfer, BalanceInquiry
    }

    private static int nextId = 1;

    protected Transaction(Atm atm, Client client) {
        this.atm = atm;
        this.client = client;
        this.id = nextId ++;
    }

    //TODO:getChoice for makeTransaction
    public static Transaction makeTransaction(Atm atm, Client client) {
        int choice = 0;
        switch (choice) {
            case 0:
                return new Withdrawal(atm, client);
            case 1:
                return new Transfer(atm, client);
            case 2:
                return new Inquiry(atm, client);
            default:
                return null;
        }
    }


    public int getId() {
        return id;
    }

    protected abstract Message getSpecificsFromCustomer();

    protected abstract Check completeTransaction();

}
