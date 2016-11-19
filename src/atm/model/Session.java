package atm.model;

import atm.model.shared.Client;
import atm.model.transaction.Transaction;

import static atm.model.Session.State.*;

public class Session {
    private Atm atm;
    private State state;
    private Client currentClient;

    // Possible values for state
    public enum State {
        INITIAL, CHOOSING_TRANSACTION, PERFORMING_TRANSACTION, EJECTING_CARD, FINAL
    }

    public Session(Atm atm, Client client) {
        this.atm = atm;
        this.currentClient = client;
        state = INITIAL;
    }

    public void performSession() {
        Transaction transaction = null;
        while (!state.equals(FINAL)) {
            switch (state) {
                case INITIAL:
                    if (currentClient != null) state = CHOOSING_TRANSACTION;
                    else System.err.println("currentClient == null"); // TODO: 19-Nov-16
                    break;
                case CHOOSING_TRANSACTION:
                    // TODO: 19-Nov-16 choose transaction
                    transaction = Transaction.makeTransaction(atm, currentClient);
                    state = PERFORMING_TRANSACTION;
                    break;
                case PERFORMING_TRANSACTION:
                    // TODO: 19-Nov-16 perform transaction
                    boolean repeat = false;
                    if (repeat) state = CHOOSING_TRANSACTION;
                    else state = EJECTING_CARD;
                    break;
                case EJECTING_CARD:
                    atm.ejectCard();
                    state = FINAL;
                    break;
            }
        }
    }

    public State getState() {
        return state;
    }

    public Client getCurrentClient() {
        return currentClient;
    }
}
