package atm.model;

import atm.model.components.CardReaderModel;
import atm.model.components.CashDispenser;
import atm.model.components.CheckPrinter;
import atm.model.shared.Client;
import atm.model.shared.exception.InvalidClientException;

import static atm.model.Atm.State.IDLE_STATE;
import static atm.model.Atm.State.PROCESSING_STATE;

public class Atm implements Runnable {
    private static Atm instance;

    private String bankName;

    //components
    private CardReaderModel cardReader;
    private CashDispenser cashDispenser;
    private CheckPrinter checkPrinter;


    //state
    private State state;
    private Session currentSession;
    private boolean sessionActive;

    public enum State {
        IDLE_STATE, PROCESSING_STATE
    }

    public Atm() {
        //create components
        cardReader = new CardReaderModel();
        cashDispenser = new CashDispenser();
        checkPrinter = new CheckPrinter();

        //initial state
        state = State.IDLE_STATE;
        sessionActive = false;
    }

    @Override
    public void run() {
        while (true) {
            switch (state) {
                case IDLE_STATE:
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    if (sessionActive) {
                        state = PROCESSING_STATE;
                    }
                    // TODO: 19-Nov-16
                    break;
                case PROCESSING_STATE:
                    System.out.println("Processing");
                    // TODO: 19-Nov-16
                    currentSession.performSession();
                    state = IDLE_STATE;
                    break;
            }
        }
    }

    public synchronized void insertCard(String cardNumber, String enteredPin) throws InvalidClientException {
        Client client = cardReader.readCard(cardNumber);
        if (verifyPin(enteredPin, client)) startSession(client);
        else throw new InvalidClientException();
    }

    public synchronized void ejectCard() {
        if (isSessionActive()) cardReader.ejectCard();
        endSession();
    }

    private synchronized Session startSession(Client client){
        currentSession = new Session(this, client);
        sessionActive = true;
        setState(PROCESSING_STATE);
        notify();
        return currentSession;
    }

    private synchronized void endSession(){
        currentSession = null;
        sessionActive = false;
        setState(IDLE_STATE);
        notify();
    }

    public boolean changePin(String oldPin, String newPin){
        Client currentClient = currentSession.getCurrentClient();
        if (verifyPin(oldPin, currentClient)) {
            currentClient.setPass(newPin);
            currentClient.updateInDB();
            return true;
        }
        return false;
    }

    private boolean verifyPin(String pin, Client client){
        return pin.equals(client.getPass());
    }

    public static synchronized Atm getInstance() {
        if (instance == null) instance = new Atm();
        return instance;
    }

    public boolean isSessionActive() {
        return sessionActive;
    }

    public String getBankName() {
        return bankName;
    }

    public State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
