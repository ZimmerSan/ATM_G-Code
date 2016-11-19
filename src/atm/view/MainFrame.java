package atm.view;

import static atm.tools.ViewConstants.MAINFRAME_HEIGHT;
import static atm.tools.ViewConstants.MAINFRAME_WIDTH;

import javax.swing.JFrame;
import javax.swing.JPanel;

import atm.view.components.CardReaderView;
import atm.view.components.HeaderView;
import atm.view.components.KeyboardView;
import atm.view.screenpanels.StartPanel;

/**
 * Created by Andrii Voitenko on 18-Nov-16.
 */
public class MainFrame extends JFrame {
	
	KeyboardView keyboardView;
	CardReaderView cardReaderView;
    JPanel headerView;
    JPanel screenView;
    
    StartPanel homePanel;

    public MainFrame() {
        super("G-ATM");
        setSize(MAINFRAME_WIDTH, MAINFRAME_HEIGHT);

        setLayout(null);
        init();
        
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
        screenView = StartPanel.getInstance();
        add(screenView);
    }
    
    
}
