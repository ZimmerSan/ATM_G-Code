package atm.model.components;

import atm.dao.DataManager;
import atm.model.shared.Client;
import atm.model.shared.exception.InvalidClientException;
import atm.tools.Constants;
import atm.view.MainFrame;

public class CardReaderModel {

    public boolean verifyCard(String card) {
        try {
            readCard(card);
            return true;
        } catch (InvalidClientException e) {
            return false;
        }
    }

    public Client readCard(String number) throws InvalidClientException {
        Client client = DataManager.getClientByCardNumber(number);
        if (client != null) return client;
        else throw new InvalidClientException();
    }

    public void ejectCard() {
        MainFrame.getInstance().setState(MainFrame.State.INIT);
        MainFrame.getInstance().showMessage(Constants.CARD_EJECTED, Constants.MessageType.INFO);
    }
}
