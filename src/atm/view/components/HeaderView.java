package atm.view.components;

import static atm.tools.ViewConstants.HEADER_BACKGROUND_COLOUR;
import static atm.tools.ViewConstants.*;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import atm.view.screenpanels.StartPanel;

public class HeaderView extends JPanel{

	    private static HeaderView instance;

	    private HeaderView(){
	    	super();
	        setSize(HEADER_WIDTH,HEADER_HEIGHT);
	        setLocation(HEADER_LOCATION_WIDTH, HEADER_LOCATION_WIDTH);
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


