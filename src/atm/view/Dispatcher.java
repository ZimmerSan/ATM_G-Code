package atm.view;

import atm.model.Atm;
import atm.model.shared.exception.InvalidClientException;
import atm.tools.Constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static atm.tools.Constants.*;

public class Dispatcher {
    private Atm atm;
    private MainFrame mainFrame;

    public Dispatcher(Atm atm, MainFrame mainFrame) {
        this.atm = atm;
        this.mainFrame = mainFrame;

        // Start Panel
        mainFrame.getCardReaderView().addActionListener(new ShowCardReaderPanel());

        //Auth Panel
        mainFrame.getAuthPanel().addDeclineListener(new CloseSessionListener());
        mainFrame.getAuthPanel().addAcceptListener(new Authenticate());

        // Menu Panel
        mainFrame.getMenuPanel().addGetCashAL(new ShowGetCashPanelListener());
        mainFrame.getMenuPanel().addTransmitMoneyAL(new ShowTransmitMoneyPanelListener());
        mainFrame.getMenuPanel().addChangePinAL(new ShowChangePinPanelListener());
        mainFrame.getMenuPanel().addCloseSessioneAL(new CloseSessionListener());

        // Change PIN Panel
        mainFrame.getChangePinPanel().addAcceptListener(new ChangePINListener());
        mainFrame.getChangePinPanel().addDeclineListener(new BackToMenuListener());

        //Get Cash Panel
        mainFrame.getGetCashPanel().add50AL(new GetCashDefaultListener(50));
        mainFrame.getGetCashPanel().add100AL(new GetCashDefaultListener(100));
        mainFrame.getGetCashPanel().add200AL(new GetCashDefaultListener(200));
        mainFrame.getGetCashPanel().add500AL(new GetCashDefaultListener(500));
        mainFrame.getGetCashPanel().add1000AL(new GetCashDefaultListener(1000));
        mainFrame.getGetCashPanel().addCustomAmountAL(new GetCashCustomListener());
    }

    // Start Panel
    private class ShowCardReaderPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.AUTHORIZING);
        }
    }

    private class Authenticate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cardNumber = mainFrame.getAuthPanel().getEnteredCardNumber();
            String pin = mainFrame.getAuthPanel().getEnteredPin();
            try {
                atm.insertCard(cardNumber, pin);
                mainFrame.setState(MainFrame.State.PROCESSING_MENU);
            } catch (InvalidClientException e1) {
                atm.ejectCard();
                mainFrame.showMessage(e1.getMessage(), Constants.MessageType.ERROR);
            }
        }
    }

    // Menu Panel
    private class BackToMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }

    private class ShowGetCashPanelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.GET_CASH);
        }
    }

    private class ShowTransmitMoneyPanelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.TRANSMIT_MONEY);
        }
    }

    private class ShowChangePinPanelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.CHANGE_PIN);
        }
    }

    private class CloseSessionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            atm.ejectCard();
        }
    }

    // Change PIN Panel
    private class ChangePINListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String oldPIN = mainFrame.getChangePinPanel().getOldPin();
            String newPIN = mainFrame.getChangePinPanel().getNewPin();
            String confirmPIN = mainFrame.getChangePinPanel().getConfirmPin();
            if (!validateNewPins(newPIN, confirmPIN)) {
                mainFrame.showMessage(ERR_INVALID_NEW_PIN, Constants.MessageType.ERROR);
                mainFrame.getChangePinPanel().refresh();
            } else if (atm.changePin(oldPIN, newPIN)) {
                mainFrame.showMessage(PIN_CHANGED, Constants.MessageType.INFO);
                mainFrame.setState(MainFrame.State.PROCESSING_MENU);
            } else {
                mainFrame.showMessage(ERR_INVALID_OLD_PIN, Constants.MessageType.ERROR);
                mainFrame.getChangePinPanel().refresh();
            }
        }

        private boolean validateNewPins(String pin, String confirm){
            return pin.equals(confirm) && pin.matches("^\\d{4}$");
        }
    }

    // Get Cash Panel
    private class GetCashDefaultListener implements ActionListener {
        private int amount;

        public GetCashDefaultListener(int amount) {
            this.amount = amount;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Implement model logic here (Get Cash)
//            Money money = new Money(amount);
//            Withdrawal w = new Withdrawal(atm, from, null, money);
//            try {
//                w.makeWithdrawal();
//                System.out.println(from.getBalance());
//                mainFrame.showMessage("Pick up " + amount + " USD!", Constants.MessageType.INFO);
//            } catch (MoneyException ex) {
//                mainFrame.showMessage(ex.getMessage(), Constants.MessageType.ERROR);
//            }
//            mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }

    private class GetCashCustomListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            String cashString = mainFrame.getGetCashPanel().getCustomAmount();
//            if (cashString.equals("") || cashString.equals("0")) {
//                return;
//            }
//            // TODO: Implement model logic here (Get custom amount)
//            int cents = Integer.parseInt(cashString);
//            Withdrawal w = new Withdrawal(atm, from, null, new Money(cents));
//            System.out.println(from.getBalance());
//            try {
//                w.makeWithdrawal();
//                System.out.println(from.getBalance());
//                mainFrame.showMessage("Pick up " + cashString + " USD!", Constants.MessageType.INFO);
//            } catch (MoneyException ex) {
//                mainFrame.showMessage(ex.getMessage(), Constants.MessageType.ERROR);
//            }
//            mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }

}
