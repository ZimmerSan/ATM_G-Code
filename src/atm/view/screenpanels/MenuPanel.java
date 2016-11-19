package atm.view.screenpanels;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import atm.tools.ViewUtils;

public class MenuPanel extends JPanel {

	private static MenuPanel instance;
	JButton getCash, transmitCash, checkBalance;
	JButton deposits, changePin, somebtn2;
	JButton somebtn3, somebtn4, somebtn5;

    private MenuPanel(){
    	super();
    	ViewUtils.setupDefaultScreenPanel(this);
    	
    	init();
    	initActionListners();
    }
    
    private void init(){
    	JLabel label = new JLabel("Chose Action:", SwingConstants.CENTER);
    	label.setSize(240, 20);
    	label.setLocation(100, 20);
    	label.setFont(new Font("Arial", Font.PLAIN, 14));
    	add(label);
    	//ROW_1 x=20/160/300 y=60/140/220 Don't touch =)
    	getCash = ViewUtils.createButton("Get Cash", 20, 60, 120, 40);
    	transmitCash = ViewUtils.createButton("Transmit Money", 160, 60, 120, 40);
    	checkBalance = ViewUtils.createButton("Check Balance", 300, 60, 120, 40);
    	//ROW_2
    	deposits = ViewUtils.createButton("View Deposits", 20, 140, 120, 40);
    	changePin = ViewUtils.createButton("Change PIN", 160, 140, 120, 40);
    	somebtn2 = ViewUtils.createButton("Random Action 2", 300, 140, 120, 40);
    	
    	//ROW_3
    	somebtn3 = ViewUtils.createButton("Random Action 3", 20, 220, 120, 40);
    	somebtn4 = ViewUtils.createButton("Random Action 4", 160, 220, 120, 40);
    	somebtn5 = ViewUtils.createButton("Random Action 5", 300, 220, 120, 40);
    	
    	add(getCash);
    	add(transmitCash);
    	add(checkBalance);
    	add(deposits);
    	add(changePin);
    	add(somebtn2);
    	add(somebtn3);
    	add(somebtn4);
    	add(somebtn5);
    }
    
    private void initActionListners(){
    	// TODO: Action Listners
    	
    }

    public static synchronized MenuPanel getInstance(){
        if (instance == null) instance = new MenuPanel();
        return instance;
    }
}
