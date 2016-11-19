package atm.view;

import atm.view.panels.HomePanel;

import javax.swing.*;

import static atm.tools.GUIConstants.MAINFRAME_HEIGHT;
import static atm.tools.GUIConstants.MAINFRAME_WIDTH;

/**
 * Created by Andrii Voitenko on 18-Nov-16.
 */
public class MainFrame extends JFrame {
	HomePanel homePanel;

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
    
    private void init(){
        homePanel = HomePanel.newInstance();
        add(homePanel);

        homePanel.setVisible(true);
    }
    
}
