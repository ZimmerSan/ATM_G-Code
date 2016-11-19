package atm.model.components;

import atm.dao.DataManager;
import atm.model.Atm;
import atm.model.shared.Client;

public class CardReaderModel {
    private Atm atm;

    public CardReaderModel(Atm atm){
        this.atm = atm;
    }

    public boolean verifyPin(String pin, Client client){
        return pin.equals(client.getPass());
    }

    public Client readCard(String number) {
        return DataManager.getClientByCardNumber(number);
    }

    public void ejectCard() {
        System.out.println("ejectCard");
        atm.setCardInserted(false);
        atm.setState(Atm.State.IDLE_STATE);
    }

    public void retainCard() {
        System.out.println("retainCard");
    }


}
