package atm.view.screenpanels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import atm.tools.ViewUtils;

public class StartPanel extends JPanel{
    private static StartPanel instance;

    private StartPanel(){
    	super();
    	ViewUtils.setupDefaultScreenPanel(this);
    	init();
    	
    }
    
    private void init(){
    	JLabel welcome = new JLabel("WELCOME", SwingConstants.CENTER);
    	welcome.setForeground(Color.white);
    	welcome.setSize(280, 40);
    	welcome.setLocation(80, 120);
    	welcome.setFont(new Font("Arial", Font.PLAIN, 22));
    	add(welcome);
    }

    public static synchronized StartPanel getInstance(){
        if (instance == null) instance = new StartPanel();
        return instance;
    }
}
