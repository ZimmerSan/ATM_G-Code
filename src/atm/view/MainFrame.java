package atm.view;

import atm.tools.Constants;
import atm.view.components.CardReaderView;
import atm.view.components.HeaderView;
import atm.view.components.KeyboardView;
import atm.view.screenpanels.AuthPanel;
import atm.view.screenpanels.ChangePinPanel;
import atm.view.screenpanels.GetCardNumberPanel;
import atm.view.screenpanels.GetCashPanel;
import atm.view.screenpanels.MenuPanel;
import atm.view.screenpanels.StartPanel;

import javax.swing.*;

import static atm.tools.ViewConstants.MAINFRAME_HEIGHT;
import static atm.tools.ViewConstants.MAINFRAME_WIDTH;

/**
 * Created by Andrii Voitenko on 18-Nov-16.
 */
public class MainFrame extends JFrame {
    private static MainFrame instance;
    private State state;

	private KeyboardView keyboardView;
	private CardReaderView cardReaderView;
    private JPanel headerView;

    private GetCashPanel getCashPanel;
    private MenuPanel menuPanel;
    private AuthPanel authPanel;
    private StartPanel startPanel;
    private ChangePinPanel changePinPanel;
    private GetCardNumberPanel getCardNumberPanel;

    private MainFrame() {
        super("G-ATM");
        setSize(MAINFRAME_WIDTH, MAINFRAME_HEIGHT);
        setLayout(null);
        init();

		setVisible(true);
		setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void init() {
    	//Components init
    	//Keyboard init
        keyboardView = KeyboardView.getInstance();
        add(keyboardView);

    	//CardReader init
        cardReaderView = CardReaderView.getInstance();
        add(cardReaderView);

        // Header init
        headerView = HeaderView.getInstance();
        add(headerView);

        // Start ScreenPanel init
        
        menuPanel = MenuPanel.getInstance();
        authPanel = AuthPanel.getInstance();
        getCashPanel = GetCashPanel.getInstance();
        startPanel = StartPanel.getInstance();
        changePinPanel = ChangePinPanel.getInstance();
        getCardNumberPanel = GetCardNumberPanel.getInstance();
        add(getCardNumberPanel);
        add(changePinPanel);
        add(getCashPanel);
        add(menuPanel);
        add(authPanel);
        add(startPanel);

        repaintScreen(startPanel);
    }

    public synchronized void setState(State state){
        // TODO: 19-Nov-16 implement other states
        switch (state){
            case INIT:
                repaintScreen(startPanel);
                cardReaderView.setEnabled(true);
                break;
            case CHECK_CARD:
            	
                repaintScreen(getCardNumberPanel);
                break;
            case AUTHORIZING:
                repaintScreen(authPanel);
                
                break;
            case PROCESSING_MENU:
                repaintScreen(menuPanel);
                break;
            case GET_CASH:
            	repaintScreen(getCashPanel);
            	break;
            case TRANSMIT_MONEY:
            	//repaintScreen();
            	break;
            case CHANGE_PIN:
            	repaintScreen(changePinPanel);
            	break;
        }
    }

    private synchronized void repaintScreen(JPanel panelToShow){
    	changePinPanel.setVisible(false);
        getCashPanel.setVisible(false);
        menuPanel.setVisible(false);
        authPanel.setVisible(false);
        startPanel.setVisible(false);
        getCardNumberPanel.setVisible(false);
        
        panelToShow.setVisible(true);
        panelToShow.requestFocus();
    }

    public void showMessage(String message, Constants.MessageType type){
        JOptionPane.showMessageDialog(this, message, type.getTitle(), type.getType());
    }

    public enum State {
        // TODO: 19-Nov-16 add other states
        INIT, CHECK_CARD, AUTHORIZING, PROCESSING_MENU, GET_CASH, TRANSMIT_MONEY, CHANGE_PIN
    }

    public static synchronized MainFrame getInstance() {
        if (instance == null) instance = new MainFrame();
        return instance;
    }
    
    public GetCardNumberPanel getGetCardNumberPanel() {
		return getCardNumberPanel;
	}

    public CardReaderView getCardReaderView() {
        return cardReaderView;
    }

    public GetCashPanel getGetCashPanel() {
        return getCashPanel;
    }

    public StartPanel getStartPanel() {
        return startPanel;
    }

    public AuthPanel getAuthPanel() {
        return authPanel;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
    
    public ChangePinPanel getChangePinPanel() {
        return changePinPanel;
    }

}
