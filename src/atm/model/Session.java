package atm.model;

public class Session {
    private Atm atm;
    private State state;

    // Possible values for state
    public enum State {
        READING_CARD, READING_PIN, CHOOSING_TRANSACTION, PERFORMING_TRANSACTION, EJECTING_CARD, FINAL
    }

    public Session(Atm atm){
        this.atm = atm;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
