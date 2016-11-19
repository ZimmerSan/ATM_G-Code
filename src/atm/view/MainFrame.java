package atm.view;

import atm.view.ScreenPanels.StartPanel;
import atm.view.components.HeaderView;
import atm.view.components.KeyBoardView;

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
	
	KeyBoardView keyboardView;
    JPanel headerView;
    JPanel screenView;
    
    StartPanel homePanel;

    public MainFrame() {
        super("ATM");
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
        keyboardView = KeyBoardView.getInstance();
        keyboardView.setLocation(40, 360);
        add(keyboardView);

        // Header init
        headerView = HeaderView.getInstance();
        add(headerView);

        // Start Screen init
        screenView = StartPanel.getInstance();
        screenView.setLocation(40, 60);
        add(screenView);
    }
    
}
