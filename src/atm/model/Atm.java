package atm.model;

import atm.model.components.*;

public class Atm {

    private String bankName;

    //components
    private CardReader cardReader;
    private CashDispenser cashDispenser;
    private CheckPrinter checkPrinter;
    private KeyBoardModel keyBoardModel;
    private Screen screen;

    //state
    private State state;
    private boolean switchOn;
    private boolean cardInserted;

    public enum State{
        IDLE_STATE, PROCESSING_STATE
    }

    public Atm(String bankName){
        this.bankName = bankName;

        //create components
        cardReader = new CardReader(this);
        cashDispenser = new CashDispenser();
        checkPrinter = new CheckPrinter();
        keyBoardModel = new KeyBoardModel(this);
        screen = new Screen();

        //initial state
        state = State.IDLE_STATE;
        switchOn = false;
        cardInserted = false;
    }



    public KeyBoardModel getKeyBoardModel() {
        return keyBoardModel;
    }

    public boolean isCardInserted() {
        return cardInserted;
    }

    public void setCardInserted(boolean flag) {
        cardInserted = flag;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public CardReader getCardReader() {
        return cardReader;
    }

    public boolean isSwitchOn() {
        return switchOn;
    }

    public String getBankName() {
        return bankName;
    }

    public Screen getScreen() {
        return screen;
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
}
