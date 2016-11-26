package atm.model;

import atm.model.components.CardReaderModel;
import atm.model.components.CashDispenser;
import atm.model.components.CheckPrinter;
import atm.model.shared.Client;
import atm.model.shared.exception.InvalidClientException;
import atm.model.transaction.Inquiry;
import atm.model.transaction.Transaction;
import atm.model.transaction.Transfer;
import atm.model.transaction.Withdrawal;
import atm.model.shared.Money;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static atm.model.Atm.State.IDLE_STATE;
import static atm.model.Atm.State.PROCESSING_STATE;

public class Atm {
    private static Atm instance;

    private String bankName;

    //components
    private CardReaderModel cardReader;
    private CashDispenser cashDispenser;
    private CheckPrinter checkPrinter;

    //state
    private State state;
    private Client currentClient;
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

    public synchronized void insertCard(String cardNumber, String enteredPin) throws InvalidClientException {
        Client client = cardReader.readCard(cardNumber);
        if (verifyPin(enteredPin, client)) {
            setState(PROCESSING_STATE);
            currentClient = client;
            sessionActive = true;
        }
        else throw new InvalidClientException();
    }

    public synchronized void ejectCard() {
        if (isSessionActive()) cardReader.ejectCard();
        setState(IDLE_STATE);
        currentClient = null;
        sessionActive = false;
    }

    public boolean changePin(String oldPin, String newPin) throws Exception {
        if (verifyPin(oldPin, currentClient)) {
            currentClient.setPass(encryptWithMD5(newPin));
            currentClient.updateInDB();
            return true;
        }
        return false;
    }

    private boolean verifyPin(String pin, Client client) {
        String encryptedPin = encryptWithMD5(pin);
        return encryptedPin.equals(client.getPass());
    }

    private String encryptWithMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            byte[] passBytes = pass.getBytes();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
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

    public String doWithdrawal(int cash) throws Exception {
        Withdrawal w = new Withdrawal(instance, currentClient, new Money(cash));
        w.performTransaction();
        return w.getSpecificsFromCustomer().toString();
    }

    public String doTransfer(String cardNumber, int cash) throws Exception {
        Client clientForTransfer = cardReader.readCard(cardNumber);
        Transfer t = new Transfer(instance, currentClient, clientForTransfer, new Money(cash));
        t.performTransaction();
        return t.getSpecificsFromCustomer().toString();
    }

    public String doInquiry() throws Exception {
        Transaction inquiry = new Inquiry(instance, currentClient);
        inquiry.performTransaction();
        return inquiry.getSpecificsFromCustomer().toString();
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

    private Client getCurrentClient(){
        return currentClient;
    }
}
