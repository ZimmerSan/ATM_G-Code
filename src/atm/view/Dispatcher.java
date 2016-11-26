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
    private final BackToMenuListener backToMenuListener;


    public Dispatcher(Atm atm, final MainFrame mainFrame) {
        this.atm = atm;
        this.mainFrame = mainFrame;

        //Often used AL
        backToMenuListener = new BackToMenuListener();

        // Start Panel
        mainFrame.getCardReaderView().addActionListener(new WelcomeListener());

        //getClientCardForAuth Panel
        mainFrame.getGetCardNumberPanel().addAcceptListener(new getClientCardForAuthListener());
        mainFrame.getGetCardNumberPanel().addDeclineListener(new CloseSessionListener());

        //Auth Panel
        mainFrame.getAuthPanel().addDeclineListener(new CloseSessionListener());
        mainFrame.getAuthPanel().addAcceptListener(new AuthenticateListener());

        // Menu Panel
        mainFrame.getMenuPanel().addWithdrawalAL(new ShowGetCashPanelListener());
        mainFrame.getMenuPanel().addTransferAL(new ShowTransmitMoneyPanelListener());
        mainFrame.getMenuPanel().addInquiryAL(new InquiryListener());
        mainFrame.getMenuPanel().addChangePinAL(new ShowChangePinPanelListener());
        mainFrame.getMenuPanel().addCloseSessionAL(new CloseSessionListener());

        // Change PIN Panel
        mainFrame.getChangePinPanel().addAcceptListener(new ChangePINListener());
        mainFrame.getChangePinPanel().addDeclineListener(backToMenuListener);

        //Get Cash Panel
        mainFrame.getGetCashPanel().add50AL(new GetCashDefaultListener(50));
        mainFrame.getGetCashPanel().add100AL(new GetCashDefaultListener(100));
        mainFrame.getGetCashPanel().add200AL(new GetCashDefaultListener(200));
        mainFrame.getGetCashPanel().add500AL(new GetCashDefaultListener(500));
        mainFrame.getGetCashPanel().add1000AL(new GetCashDefaultListener(1000));
        mainFrame.getGetCashPanel().addCustomAmountAL(new GetCashCustomListener());
        mainFrame.getGetCashPanel().addDeclineListener(backToMenuListener);

        //Transmit Check Card
        mainFrame.getTransmitCardPanel().addDeclineListener(backToMenuListener);
        mainFrame.getTransmitCardPanel().addAcceptListener(new TransmitCardListener());

        //Transmit Send Panel
        mainFrame.getTransmitSendPanel().addCustomAmountAL(new TransmitSendCustomAmountListener());
        mainFrame.getTransmitSendPanel().add50AL(new TransmitSendListener(50));
        mainFrame.getTransmitSendPanel().add100AL(new TransmitSendListener(100));
        mainFrame.getTransmitSendPanel().add200AL(new TransmitSendListener(200));
        mainFrame.getTransmitSendPanel().add500AL(new TransmitSendListener(500));
        mainFrame.getTransmitSendPanel().add1000AL(new TransmitSendListener(1000));
        mainFrame.getTransmitSendPanel().addDeclineListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                mainFrame.setState(MainFrame.State.TRANSMIT_MONEY_CHECK_CARD);
            }
        });
    }

    // Start Panel
    private class WelcomeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardReaderView().setEnabled(false);
            mainFrame.setState(MainFrame.State.CHECK_CARD);

        }
    }

    //getClientCardForAuth  Panel
    private class getClientCardForAuthListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cardNumber = mainFrame.getGetCardNumberPanel().getCardNumber();

            //TODO: Implement model logic here (Check if the card exists)
            boolean validCard = true;
            if (validCard) {
                mainFrame.setState(MainFrame.State.AUTHORIZING);
            } else {
                mainFrame.showMessage("Some wrong card!", Constants.MessageType.INFO);
                mainFrame.setState(MainFrame.State.INIT);
            }
        }
    }

    //Auth Panel
    private class AuthenticateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Pay attention to the following code
            String clientCardNumber = mainFrame.getGetCardNumberPanel().getCardNumber();
            String pin = mainFrame.getAuthPanel().getEnteredPin();
            try {
                atm.insertCard(clientCardNumber, pin);
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
            mainFrame.setState(MainFrame.State.TRANSMIT_MONEY_CHECK_CARD);
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
            mainFrame.setState(MainFrame.State.INIT);
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
            } else try {
                if (atm.changePin(oldPIN, newPIN)) {
                    mainFrame.showMessage(PIN_CHANGED, MessageType.INFO);
                    mainFrame.setState(MainFrame.State.PROCESSING_MENU);
                } else {
                    mainFrame.showMessage(ERR_INVALID_OLD_PIN, MessageType.ERROR);
                    mainFrame.getChangePinPanel().refresh();
                }
            } catch (Exception e1) {
                mainFrame.showMessage(e1.getMessage(), MessageType.ERROR);
            }
        }

        private boolean validateNewPins(String pin, String confirm) {
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
            mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }

    private class GetCashCustomListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cashString = mainFrame.getGetCashPanel().getCustomAmount();
            Integer cash = Integer.getInteger(cashString);
            if (cash == null || cash == 0) return;
            try {
                atm.doWithdrawal(cash);
                mainFrame.showMessage("Pick up " + cashString + " USD!", Constants.MessageType.INFO);
            } catch (Exception ex) {
                mainFrame.showMessage(ex.getMessage(), Constants.MessageType.ERROR);
            }
            mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }

    // Transmit Card Panel
    private class TransmitCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cardNumber = mainFrame.getTransmitCardPanel().getCardNumber();

            //TODO: Implement model logic here (Check if the card exists)
            boolean validCard = true;
            if (validCard) {
                mainFrame.setState(MainFrame.State.TRANSMIT_MONEY_SEND);
            } else {
                mainFrame.showMessage("Some wrong card!", Constants.MessageType.ERROR);
                mainFrame.getTransmitCardPanel().refresh();
            }
        }
    }

    //Transmit Send Panel
    //We chose amount here and send money

    private class TransmitSendListener implements ActionListener {
        private int amount;

        public TransmitSendListener(int amount) {
            this.amount = amount;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String cardNumber = mainFrame.getTransmitCardPanel().getCardNumber();
            // TODO: Implement model logic here (Send money to the some card)
            //
            mainFrame.showMessage("The " + amount + " USD has been transmited!", Constants.MessageType.INFO);
            mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }

    private class TransmitSendCustomAmountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cardNumber = mainFrame.getTransmitCardPanel().getCardNumber();
            String cashString = mainFrame.getTransmitSendPanel().getCustomAmount();
            Integer cash = Integer.getInteger(cashString);
            if (cash == null || cash == 0) return;
            try {
                atm.doTransfer(cardNumber, cash);
                mainFrame.showMessage("The " + cashString + " USD has been transmitted!", Constants.MessageType.INFO);
            } catch (Exception ex) {
                mainFrame.showMessage(ex.getMessage(), MessageType.ERROR);
            }
            mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }

    private class InquiryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String message = atm.doInquiry();
                mainFrame.showMessage(message, MessageType.INFO);
            } catch (Exception e1) {
                mainFrame.showMessage(e1.getMessage(), MessageType.ERROR);
            }
        }
    }

}
