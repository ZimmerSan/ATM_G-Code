package atm.view;

import atm.model.Atm;
import atm.model.shared.Client;
import atm.model.shared.exception.InvalidClientException;
import atm.tools.Constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.enterprise.inject.Disposes;

public class Dispatcher {
    private Atm atm;
    private MainFrame mainFrame;

    public Dispatcher(Atm atm, MainFrame mainFrame) {
        this.atm = atm;
        this.mainFrame = mainFrame;
        // Start Panel
        mainFrame.getCardReaderView().addActionListener(new ShowCardReaderPanel());
        
        //Auth Panel
        mainFrame.getAuthPanel().addDeclineListener(new ShowStartPanel());
        mainFrame.getAuthPanel().addAcceptListener(new Authenticate());
        
        // Menu Panel
        mainFrame.getMenuPanel().addGetCashAL(new ShowGetCashPanelListner());
        mainFrame.getMenuPanel().addTransmitMoneyAL(new ShowTransmitMoneyPanelListner());
        mainFrame.getMenuPanel().addChangePinAL(new ShowChangePinPanelListner());
        mainFrame.getMenuPanel().addCloseSessioneAL(new CloseSessionListner());
        
        // Change PIN Panel
       mainFrame.getChangePinPanel().addAcceptListener(new ChangePINListner());
       mainFrame.getChangePinPanel().addDeclineListener(new BackToMenuListner());
       
       //Get Cash Panel
       mainFrame.getGetCashPanel().add50AL(new GetCashDefaultListner(50));
       mainFrame.getGetCashPanel().add100AL(new GetCashDefaultListner(100));
       mainFrame.getGetCashPanel().add200AL(new GetCashDefaultListner(200));
       mainFrame.getGetCashPanel().add500AL(new GetCashDefaultListner(500));
       mainFrame.getGetCashPanel().add1000AL(new GetCashDefaultListner(1000));
       mainFrame.getGetCashPanel().addCustomAmountAL(new GetCashCustomListner());
        
    }

    // Start Panel
    private class ShowCardReaderPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.AUTHORIZING);
        }
    }

    // Auth Panel
    private class ShowStartPanel implements ActionListener {
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
            mainFrame.setState(MainFrame.State.PROCESSING_MENU);
//            try {
//                Client client = atm.validateAuth(cardNumber, pin);
//                atm.startSession(client);
//                mainFrame.showMessage("Success!", Constants.MessageType.INFO);
//                mainFrame.setState(MainFrame.State.PROCESSING_MENU);
//            } catch (InvalidClientException e1) {
//                System.err.println(e1.getMessage());
//                mainFrame.showMessage(e1.getMessage(), Constants.MessageType.ERROR);
//            }
        }
    }
    
    // Menu Panel
    private class BackToMenuListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.PROCESSING_MENU);
            
        }
    }
    
    private class ShowGetCashPanelListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.GET_CASH);
            
        }
    }
    
    private class ShowTransmitMoneyPanelListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.TRANSMIT_MONEY);
            
        }
    }
    
    private class ShowChangePinPanelListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setState(MainFrame.State.CHANGE_PIN);
            
        }
    }
    
    private class CloseSessionListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	// TODO: Implement model logic here (Close session)
            
        	mainFrame.setState(MainFrame.State.INIT);
        }
    }
    
    // Change PIN Panel
    private class ChangePINListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	String oldPIN = mainFrame.getChangePinPanel().getOldPin();
        	String newPIN = mainFrame.getChangePinPanel().getNewPin();
        	String confirmPIN = mainFrame.getChangePinPanel().getConfirmPin();
        	// Checking "new pin == confirm pin"
        	if(!newPIN.equals(confirmPIN)){
        		mainFrame.showMessage("New PIN's are not equal!", Constants.MessageType.ERROR);
        		mainFrame.getChangePinPanel().refresh();
        	}
        	
        	// TODO: Implement model logic here (Change PIN)
        	//mainFrame.showMessage("The old PIN is wrong!", Constants.MessageType.ERROR);
        	
        	mainFrame.showMessage("Success!", Constants.MessageType.INFO);
        	mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }
    
    // Get Cash Panel
    private class GetCashDefaultListner implements ActionListener {
    	private int amount;
    	public GetCashDefaultListner(int amount) {
			// TODO Auto-generated constructor stub
    		this.amount = amount;
		}
    	
        @Override
        public void actionPerformed(ActionEvent e) {
        	// TODO: Implement model logic here (Get Cash)
            
        	mainFrame.showMessage("Pick up "+amount+" USD!", Constants.MessageType.INFO);
        	mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }
    
    private class GetCashCustomListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cashString = mainFrame.getGetCashPanel().getCustomAmount();
            if(cashString.equals("")||cashString.equals("0")){
            	return;
            }
            // TODO: Implement model logic here (Get custom amount)
            
            mainFrame.showMessage("Pick up "+cashString+" USD!", Constants.MessageType.INFO);
        	mainFrame.setState(MainFrame.State.PROCESSING_MENU);
        }
    }

}
