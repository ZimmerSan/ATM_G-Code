package atm.model;

import atm.model.components.*;
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
    private KeyBoardModel keyBoardModel;
    private ScreenModel screenModel;

    //state
    private State state;
    private Session currentSession;
    private boolean sessionActive;

    public enum State {
        IDLE_STATE, PROCESSING_STATE
    }

    public Atm() {
        //create components
        cardReader = new CardReaderModel(this);
        cashDispenser = new CashDispenser();
        checkPrinter = new CheckPrinter();
        keyBoardModel = new KeyBoardModel(this);
        screenModel = new ScreenModel();

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
                    break;
            }
        }
    }

    public Session startSession(Client client){
        currentSession = new Session(this, client);
        sessionActive = true;
        return currentSession;
    }

    public void endSession(){
        currentSession = null;
        sessionActive = false;
        this.state = IDLE_STATE;
    }

    public Client validateAuth(String cardNumber, String enteredPin) throws InvalidClientException {
        Client client;

        client = cardReader.readCard(cardNumber);
        if (cardReader.verifyPin(enteredPin, client)) {
            return client;
        } else {
            throw new InvalidClientException();
        }
    }

    public void ejectCard() {
        // TODO: 19-Nov-16 implement valid method
        cardReader.ejectCard();
        setSessionActive(false);
        setState(Atm.State.IDLE_STATE);
    }

    public static synchronized Atm getInstance() {
        if (instance == null) instance = new Atm();
        return instance;
    }

    public KeyBoardModel getKeyBoardModel() {
        return keyBoardModel;
    }

    public boolean isSessionActive() {
        return sessionActive;
    }

    public void setSessionActive(boolean flag) {
        sessionActive = flag;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public CardReaderModel getCardReader() {
        return cardReader;
    }

    public String getBankName() {
        return bankName;
    }

    public ScreenModel getScreenModel() {
        return screenModel;
    }

    public CheckPrinter getCheckPrinter() {
        return checkPrinter;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
