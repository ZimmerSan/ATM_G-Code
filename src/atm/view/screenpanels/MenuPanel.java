package atm.view.screenpanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import atm.tools.ViewUtils;

public class MenuPanel extends JPanel {

	private static MenuPanel instance;
	JButton getCash, transmitMoney, checkBalance;
	JButton deposits, changePin, somebtn2;
	JButton somebtn3, closeSessionBtn, somebtn5;

    private MenuPanel(){
    	super();
    	ViewUtils.setupDefaultScreenPanel(this);
    	
    	init();
    }
    
    private void init(){
    	JLabel label = new JLabel("Chose Action:", SwingConstants.CENTER);
    	label.setForeground(Color.white);
    	label.setSize(240, 20);
    	label.setLocation(100, 20);
    	label.setFont(new Font("Arial", Font.PLAIN, 24));
    	add(label);
    	
    	//ROW_1 x=20/160/300 y=60/140/220 Don't touch =)
    	getCash = ViewUtils.createButton("Get Cash", 20, 60, 120, 40);
    	transmitMoney = ViewUtils.createButton("Transmit Money", 160, 60, 120, 40);
    	checkBalance = ViewUtils.createButton("Check Balance", 300, 60, 120, 40);
    	//ROW_2
    	deposits = ViewUtils.createButton("View Deposits", 20, 140, 120, 40);
    	deposits.setEnabled(false);
    	changePin = ViewUtils.createButton("Change PIN", 160, 140, 120, 40);
    	somebtn2 = ViewUtils.createButton("Charity", 300, 140, 120, 40);
    	somebtn2.setEnabled(false);
    	
    	//ROW_3
    	//somebtn3 = ViewUtils.createButton("Random Action 3", 20, 220, 120, 40);
    	closeSessionBtn = ViewUtils.createButton("Finish Work", 160, 220, 120, 40);
    	ViewUtils.addActionToMap(this, closeSessionBtn, KeyEvent.VK_ESCAPE);
    	
    	//somebtn5 = ViewUtils.createButton("Random Action 5", 300, 220, 120, 40);
    	
    	add(getCash);
    	add(transmitMoney);
    	add(checkBalance);
    	add(deposits);
    	add(changePin);
    	add(somebtn2);
    	//add(somebtn3);
    	add(closeSessionBtn);
    	//add(somebtn5);
    }

    public static synchronized MenuPanel getInstance(){
        if (instance == null) instance = new MenuPanel();
        return instance;
    }
    // AL means ActionListner
    public void addGetCashAL(ActionListener al){
    	getCash.addActionListener(al);
    }
    
    public void addTransmitMoneyAL(ActionListener al){
    	transmitMoney.addActionListener(al);
    }
    
    public void addCheckBalanceAL(ActionListener al){
    	checkBalance.addActionListener(al);
    }
    
    public void addChangePinAL(ActionListener al){
    	changePin.addActionListener(al);
    }
    
    public void addCloseSessioneAL(ActionListener al){
    	closeSessionBtn.addActionListener(al);
    }
}
