package atm.model.components;

import atm.model.Atm;

public class KeyBoardModel {
    private Atm atm;

    public KeyBoardModel(Atm atm) {
        this.atm = atm;
    }

    public void getInitialCash() {
        System.out.println("getInitialCash");
    }
}
