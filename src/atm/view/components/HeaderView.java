package atm.view.components;

import static atm.tools.GUIConstants.HEADER_BACKGROUND_COLOUR;
import static atm.tools.GUIConstants.SCREEN_BACKGROUND_COLOR;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.view.ScreenPanels.StartPanel;

public class HeaderView extends JPanel{

	    private static HeaderView instance;

	    private HeaderView(){
	        setSize(440,20);
	        setLocation(40, 40);
	        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
	        setBackground(HEADER_BACKGROUND_COLOUR);
	        setLayout(null);

	        JLabel atmName = new JLabel("G-ATM");
	        atmName.setFont(new Font("Arial", Font.PLAIN, 12));
	        atmName.setSize(60, 14);
	        atmName.setLocation(370, 3);
	        add(atmName);

	    }

	    public static synchronized HeaderView getInstance(){
	        if (instance == null) instance = new HeaderView();
	        return instance;
	    }
}


