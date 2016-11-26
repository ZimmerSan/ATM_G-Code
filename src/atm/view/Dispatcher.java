package atm.view;

import atm.model.Atm;
import atm.model.shared.exception.InvalidClientException;
import atm.model.shared.exception.MoneyException;
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
        mainFrame.getCardReaderView().addActionListener(new WelcomeListner());

        //getClientCardForAuth Panel
        mainFrame.getGetCardNumberPanel().addAcceptListener(new getClientCardForAuthListner());
        mainFrame.getGetCardNumberPanel().addDeclineListener(new CloseSessionListener());

        //Auth Panel
        mainFrame.getAuthPanel().addDeclineListener(new CloseSessionListener());
        mainFrame.getAuthPanel().addAcceptListener(new AuthenticateListner());

        // Menu Panel
        mainFrame.getMenuPanel().addGetCashAL(new ShowGetCashPanelListener());
        mainFrame.getMenuPanel().addTransmitMoneyAL(new ShowTransmitMoneyPanelListener());
        mainFrame.getMenuPanel().addChangePinAL(new ShowChangePinPanelListener());
        mainFrame.getMenuPanel().addCloseSessioneAL(new CloseSessionListener());
        mainFrame.getMenuPanel().addCheckBalanceAL(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Implement model logic here (Get current amount of money on card)
                String amount = "infinity";
                mainFrame.showMessage("You have " + amount + " USD!", Constants.MessageType.INFO);
            }
        });

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
    private class WelcomeListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getCardReaderView().setEnabled(false);
            mainFrame.setState(MainFrame.State.CHECK_CARD);

        }
    }

    //getClientCardForAuth  Panel
    private class getClientCardForAuthListner implements ActionListener {

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
    private class AuthenticateListner implements ActionListener {

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
            } else if (atm.changePin(oldPIN, newPIN)) {
                mainFrame.showMessage(PIN_CHANGED, Constants.MessageType.INFO);
                mainFrame.setState(MainFrame.State.PROCESSING_MENU);
            } else {
                mainFrame.showMessage(ERR_INVALID_OLD_PIN, Constants.MessageType.ERROR);
                mainFrame.getChangePinPanel().refresh();
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
            if (cashString.equals("") || cashString.equals("0")) return;
            try {
                atm.doWithdrawal(cashString);
                mainFrame.showMessage("Pick up " + cashString + " USD!", Constants.MessageType.INFO);
            } catch (MoneyException ex) {
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
            String cashString = mainFrame.getTransmitSendPanel().getCustomAmount();
            String cardNumber = mainFrame.getTransmitCardPanel().getCardNumber();
            if (cashString.equals("") || cashString.equals("0")) {
                return;
            }
            try{
                atm.doTransfer(cardNumber, cashString);
                mainFrame.showMessage("The " + cashString + " USD has been transmited!", Constants.MessageType.INFO);
            }catch (Exception ex){
                mainFrame.showMessage(ex.getMessage(), MessageType.ERROR);
            }
            mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }


}
