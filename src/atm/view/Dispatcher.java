package atm.view;

import atm.model.Atm;
import atm.model.shared.Client;
import atm.model.shared.exception.InvalidClientException;
import atm.tools.Constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dispatcher {
    private Atm atm;
    private MainFrame mainFrame;

    public Dispatcher(Atm atm, MainFrame mainFrame) {
        this.atm = atm;
        this.mainFrame = mainFrame;

        mainFrame.getCardReaderView().addActionListener(new ShowCardReaderPanel());
        mainFrame.getAuthPanel().addDeclineListener(new ShowHomePanel());
        mainFrame.getAuthPanel().addAcceptListener(new Authenticate());
    }

    private class ShowCardReaderPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.AUTHORIZING);
        }
    }

    private class ShowHomePanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.INIT);
            atm.setState(Atm.State.IDLE_STATE);
        }
    }

    private class Authenticate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cardNumber = mainFrame.getAuthPanel().getEnteredCardNumber();
            String pin = mainFrame.getAuthPanel().getEnteredPin();
            try {
                Client client = atm.validateAuth(cardNumber, pin);
                atm.startSession(client);
                mainFrame.setState(MainFrame.State.PROCESSING_MENU);
            } catch (InvalidClientException e1) {
                System.err.println(e1.getMessage());
                mainFrame.showMessage(e1.getMessage(), Constants.MessageType.ERROR);
            }
        }
    }

}
