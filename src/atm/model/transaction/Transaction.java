package atm.model.transaction;

import atm.model.Atm;
import atm.model.shared.Card;
import atm.model.shared.Check;
import atm.model.shared.Message;
import atm.model.shared.Money;
import atm.model.shared.exception.CardRetained;


public abstract class Transaction {
    protected Atm atm;
    protected Card card;
    protected String pin;
    protected int id;
    protected Message message;
    protected Money money;
    private enum TRANSACTION_TYPES {
        Withdrawal, Transfer, BalanceInquiry
    }
    private static int nextId = 1;

    protected Transaction(Atm atm, Card card, String pin) {
        this.atm = atm;
        this.card = card;
        this.pin = pin;
        this.id = nextId ++;
    }

//TODO:getChoice for makeTransaction
    public static Transaction makeTransaction(Atm atm, Card card, String pin) {
        int choice = 0;
        switch(choice) {
            case 0:

                return new Withdrawal(atm, card, pin);
            case 1:

                return new Transfer(atm, card, pin);

            case 2:

                return new Inquiry(atm, card, pin);

            default:

                return null;    // To keep compiler happy - should not happen!
        }
    }


    public int getId() {
        return id;
    }

    protected abstract Message getSpecificsFromCustomer();

    protected abstract Check completeTransaction();

}
