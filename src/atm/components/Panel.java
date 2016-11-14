package atm.components;

import atm.model.Atm;

/**
 * Created by KOKOWKA on 14.11.2016.
 */
public class Panel {
    private Atm atm;

    public Panel(Atm atm) {
        this.atm = atm;
    }

    public void getInitialCash() {
        System.out.println("getInitialCash");
    }
}
