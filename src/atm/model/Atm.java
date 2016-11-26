package atm.model;

import atm.model.components.CardReaderModel;
import atm.model.components.CashDispenser;
import atm.model.components.CheckPrinter;
import atm.model.shared.Client;
import atm.model.shared.exception.InvalidClientException;
import atm.model.shared.exception.MoneyException;
import atm.model.transaction.Withdrawal;
import atm.model.shared.Money;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
            currentClient.setPass(encryptWithMD5(newPin));
            currentClient.updateInDB();
            return true;
        }
        return false;
    }

    private boolean verifyPin(String pin, Client client){
        String encryptedPin = encryptWithMD5(pin);
        System.out.println(encryptedPin);
        return encryptedPin.equals(client.getPass());
    }

    private String encryptWithMD5(String pass){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex);
        }
        return null;
    }

    public static synchronized Atm getInstance() {
        if (instance == null) instance = new Atm();
        return instance;
    }

    public int doWithdrawal(String cashString) throws MoneyException{
        int intCash = Integer.parseInt(cashString);
        Withdrawal w = new Withdrawal(instance, currentSession.getCurrentClient(), new Money(intCash));
        w.performTransaction();
        return intCash;
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
