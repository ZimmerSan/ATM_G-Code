package atm.model;

import atm.model.components.*;

public class Atm {

    private String bankName;

    //components
    private CardReader cardReader;
    private CashDispenser cashDispenser;
    private CheckPrinter checkPrinter;
    private Panel panel;
    private Screen screen;

    //state
    private State state;
    private boolean switchOn;
    private boolean cardInserted;

    public enum State{
        OFF_STATE, IDLE_STATE, PROCESSING_STATE
    }

    public Atm(String bankName){
        this.bankName = bankName;

        //create components
        cardReader = new CardReader(this);
        cashDispenser = new CashDispenser();
        checkPrinter = new CheckPrinter();
        panel = new Panel(this);
        screen = new Screen();

        //initial state
        state = State.OFF_STATE;
        switchOn = false;
        cardInserted = false;
    }


    public Panel getPanel() {
        return panel;
    }

    public boolean isCardInserted() {
        return cardInserted;
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
}
