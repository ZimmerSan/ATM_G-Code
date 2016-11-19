package atm.view;

import atm.view.ScreenPanels.StartPanel;
import atm.view.components.CardReaderView;
import atm.view.components.HeaderView;
import atm.view.components.KeyboardView;

import javax.swing.*;

import static atm.tools.GUIConstants.HEADER_BACKGROUND_COLOUR;
import static atm.tools.GUIConstants.MAINFRAME_HEIGHT;
import static atm.tools.GUIConstants.MAINFRAME_WIDTH;
import static atm.tools.GUIConstants.SCREEN_BACKGROUND_COLOR;

import java.awt.Color;
import java.awt.Font;

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
    	
    	//Keyboard init
        keyboardView = KeyboardView.getInstance();
        keyboardView.setLocation(40, 380);
        add(keyboardView);
        
    	//CardReader init
        cardReaderView = CardReaderView.getInstance();
        cardReaderView.setLocation(40, 360);
        add(cardReaderView);
        
        // Header init
        headerView = HeaderView.getInstance();
        add(headerView);
        
        // Start ScreenPanel init
        screenView = StartPanel.getInstance();
       
        screenView.setBackground(SCREEN_BACKGROUND_COLOR);
        add(screenView);

    }
    
    
}
