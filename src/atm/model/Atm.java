package atm.model;

import atm.model.components.CardReaderModel;
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

    //state
    private State state;
    private String insertedCard;
    private Client currentClient;
    private boolean sessionActive;

    public enum State {
        IDLE_STATE, PROCESSING_STATE
    }

    public Atm() {
        //create components
        cardReader = new CardReaderModel();

        //initial state
        state = State.IDLE_STATE;
        sessionActive = false;
    }

    public boolean insertCard(String card){
        boolean verifyCard = verifyCard(card);
        if (verifyCard) insertedCard = card;
        return verifyCard;
    }

    public boolean verifyCard(String card){
        try {
            if (cardReader.readCard(card) != null) {
                insertedCard = card;
                return true;
            } else
                return false;
        } catch (InvalidClientException e) {
            return false;
        }
    }

    public void authClient(String enteredPin) throws InvalidClientException {
        Client client = cardReader.readCard(insertedCard);
        if (verifyPin(enteredPin, client)) {
            setState(PROCESSING_STATE);
            currentClient = client;
            sessionActive = true;
        }
        else throw new InvalidClientException();
    }

    public void ejectCard() {
        if (isSessionActive()) cardReader.ejectCard();
        setState(IDLE_STATE);
        currentClient = null;
        insertedCard = null;
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

    public String doWithdrawal(long cash) throws Exception {
        Withdrawal w = new Withdrawal(instance, currentClient, new Money(cash));
        w.performTransaction();
        return w.completeTransaction().toString();
    }

    public String doTransfer(String cardNumber, long cash) throws Exception {
        Client clientForTransfer = cardReader.readCard(cardNumber);
        Transfer t = new Transfer(instance, currentClient, clientForTransfer, new Money(cash));
        t.performTransaction();
        return t.completeTransaction().toString();
    }

    public String doInquiry() throws Exception {
        Transaction inquiry = new Inquiry(instance, currentClient);
        inquiry.performTransaction();
        return inquiry.completeTransaction().toString();
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

    private Client getCurrentClient(){
        return currentClient;
    }
}
