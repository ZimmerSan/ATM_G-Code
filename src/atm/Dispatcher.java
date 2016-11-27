package atm;

import atm.model.Atm;
import atm.model.shared.exception.InvalidClientException;
import atm.tools.Constants;
import atm.view.MainFrame;

import javax.swing.*;
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
        mainFrame.getTransferCardPanel().addDeclineListener(backToMenuListener);
        mainFrame.getTransferCardPanel().addAcceptListener(new TransmitCardListener());

        //Transmit Send Panel
        mainFrame.getTransferSendPanel().addCustomAmountAL(new TransferSendCustomAmountListener());
        mainFrame.getTransferSendPanel().add50AL(new TransferSendListener(50));
        mainFrame.getTransferSendPanel().add100AL(new TransferSendListener(100));
        mainFrame.getTransferSendPanel().add200AL(new TransferSendListener(200));
        mainFrame.getTransferSendPanel().add500AL(new TransferSendListener(500));
        mainFrame.getTransferSendPanel().add1000AL(new TransferSendListener(1000));
        mainFrame.getTransferSendPanel().addDeclineListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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

    //getClientCardForAuth Panel
    private class getClientCardForAuthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cardNumber = mainFrame.getGetCardNumberPanel().getCardNumber();
            boolean validCard = atm.insertCard(cardNumber);
            if (validCard) {
                mainFrame.setState(MainFrame.State.AUTHORIZING);
            } else {
                mainFrame.showMessage(ERR_INVALID_CARD, Constants.MessageType.INFO);
                mainFrame.setState(MainFrame.State.INIT);
            }
        }
    }

    //Auth Panel
    private class AuthenticateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String pin = mainFrame.getAuthPanel().getEnteredPin();
            try {
                atm.authClient(pin);
                mainFrame.setState(MainFrame.State.PROCESSING_MENU);
            } catch (InvalidClientException e1) {
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

    private class GetCashDefaultListener implements ActionListener {
        private int amount;

        public GetCashDefaultListener(int amount) {
            this.amount = amount;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getGetCashPanel().setCustomAmount(amount);
        }
    }

    private class GetCashCustomListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cashString = mainFrame.getGetCashPanel().getCustomAmount();

            try {
                long cash = Long.parseLong(cashString);
                if (cash <= 0) return;

                int dialogResult = JOptionPane.showConfirmDialog(mainFrame, "Proceed Get Cash [amount = $" + cash + "]?", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    String message = atm.doWithdrawal(cash);
                    dialogResult = JOptionPane.showConfirmDialog(mainFrame, Constants.CONFIRM_PRINT_CHECK, "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        mainFrame.showMessage(message, Constants.MessageType.INFO);
                    }
                }

            } catch (NumberFormatException e1) {
                mainFrame.showMessage(ERR_INVALID_NUMBER, MessageType.ERROR);
                return;
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
            String cardNumber = mainFrame.getTransferCardPanel().getCardNumber();
            try {
                boolean validCard = atm.verifyCardForTransfer(cardNumber);
                if (validCard) {
                    mainFrame.setState(MainFrame.State.TRANSMIT_MONEY_SEND);
                } else {
                    mainFrame.showMessage(ERR_INVALID_CARD, Constants.MessageType.ERROR);
                    mainFrame.getTransferCardPanel().refresh();
                }
            } catch (Exception e1) {
                mainFrame.showMessage(e1.getMessage(), MessageType.ERROR);
            }
        }
    }

    //Transfer Send Panel
    private class TransferSendListener implements ActionListener {
        private int amount;

        TransferSendListener(int amount) {
            this.amount = amount;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.getTransferSendPanel().setCustomAmount(amount);
        }
    }

    private class TransferSendCustomAmountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cardNumber = mainFrame.getTransferCardPanel().getCardNumber();
            String cashString = mainFrame.getTransferSendPanel().getCustomAmount();

            try {
                long cash = Long.parseLong(cashString);
                if (cash <= 0) return;

                int dialogResult = JOptionPane.showConfirmDialog(mainFrame, "Proceed Transfer [amount = $" + cash + "] to [" + cardNumber + "]?", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    String message = atm.doTransfer(cardNumber, cash);
                    dialogResult = JOptionPane.showConfirmDialog(mainFrame, Constants.CONFIRM_PRINT_CHECK, "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        mainFrame.showMessage(message, Constants.MessageType.INFO);
                    }
                }
            } catch (NumberFormatException e1) {
                mainFrame.showMessage(ERR_INVALID_NUMBER, MessageType.ERROR);
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
