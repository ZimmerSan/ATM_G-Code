package atm.view;

import atm.tools.Constants;
import atm.view.components.CardReaderView;
import atm.view.components.HeaderView;
import atm.view.components.KeyboardView;
import atm.view.screenpanels.AuthPanel;
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

    private MainFrame() {
        super("G-ATM");
        setSize(MAINFRAME_WIDTH, MAINFRAME_HEIGHT);
        setLayout(null);
        init();
        initActionListners();

        //repaint();
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
        getCashPanel = GetCashPanel.getInstance();
        menuPanel = MenuPanel.getInstance();
        authPanel = AuthPanel.getInstance();
        startPanel = StartPanel.getInstance();
        add(getCashPanel);
        add(menuPanel);
        add(authPanel);
        add(startPanel);

        repaintScreen(startPanel);
    }

    private void initActionListners(){
    	// TODO: Action Listners for Cardreader

    }

    public synchronized void setState(State state){
        // TODO: 19-Nov-16 implement other states
        switch (state){
            case INIT:
                repaintScreen(startPanel);
                cardReaderView.setEnabled(true);
                break;
            case AUTHORIZING:
                repaintScreen(authPanel);
                cardReaderView.setEnabled(false);
                break;
            case PROCESSING_MENU:
                repaintScreen(menuPanel);
                break;
        }
    }

    private synchronized void repaintScreen(JPanel panelToShow){
        getCashPanel.setVisible(false);
        menuPanel.setVisible(false);
        authPanel.setVisible(false);
        startPanel.setVisible(false);
        panelToShow.setVisible(true);
    }

    public void showMessage(String message, Constants.MessageType type){
        JOptionPane.showMessageDialog(this, message, type.getTitle(), type.getType());
    }

    public enum State {
        // TODO: 19-Nov-16 add other states
        INIT, AUTHORIZING, PROCESSING_MENU
    }

    public static synchronized MainFrame getInstance() {
        if (instance == null) instance = new MainFrame();
        return instance;
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
}
