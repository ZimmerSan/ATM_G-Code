package atm.model.components;

import atm.dao.DataManager;
import atm.model.Atm;
import atm.model.shared.Client;
import atm.model.shared.exception.InvalidClientException;

public class CardReaderModel {
    private Atm atm;

    public CardReaderModel(Atm atm){
        this.atm = atm;
    }

    public boolean verifyPin(String pin, Client client){
        return pin.equals(client.getPass());
    }

    public Client readCard(String number) throws InvalidClientException {
        Client client = DataManager.getClientByCardNumber(number);
        if (client != null) return client;
        else throw new InvalidClientException();
    }

    public void ejectCard() {
        System.out.println("ejectCard");
    }

    public void retainCard() {
        System.out.println("retainCard");
    }


}
