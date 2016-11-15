package atm.model.components;

import atm.model.Atm;
import atm.model.shared.Card;

/**
 * Created by KOKOWKA on 14.11.2016.
 */
public class CardReader {

    private Atm atm;

    public CardReader(Atm atm){
        this.atm = atm;
    }

    public Card readCard() {
        System.out.println("readCard");
        return null;
    }

    public void ejectCard() {
        System.out.println("ejectCard");
    }

    public void retainCard() {
        System.out.println("retainCard");
    }


}
