package atm.view.screenpanels;

import atm.tools.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GetCardNumberPanel extends JPanel{

	private static GetCardNumberPanel instance;
	JTextField cardTextField;
	JButton acceptbtn;
	JButton declinebtn;
	

    private GetCardNumberPanel(){
    	super();
    	ViewUtils.setupDefaultScreenPanel(this);
    	init();
    }
    
    private void init(){
    	JLabel cardLabel = new JLabel("Input Card Number:", SwingConstants.LEFT);
    	cardLabel.setForeground(Color.white);
    	cardLabel.setSize(120, 40);
    	cardLabel.setLocation(40, 80);
    	cardLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    	add(cardLabel);
    	
    	cardTextField = new JTextField();
    	cardTextField.setSize(200, 40);
    	cardTextField.setLocation(200, 80);
    	
    	add(cardTextField);
    	
    	
    	acceptbtn = ViewUtils.createButton("Accept", 100, 180, 100, 40);
    	declinebtn = ViewUtils.createButton("Decline", 240, 180, 100, 40);
    	
    	ViewUtils.addActionToMap(this, acceptbtn, KeyEvent.VK_ENTER);
    	ViewUtils.addActionToMap(this, declinebtn, KeyEvent.VK_ESCAPE);
    	ViewUtils.addActionToMap(cardTextField, acceptbtn, KeyEvent.VK_ENTER);
    	ViewUtils.addActionToMap(cardTextField, declinebtn, KeyEvent.VK_ESCAPE);
    	
    	add(acceptbtn);
    	add(declinebtn);
    }
    
    @Override
    public void setVisible(boolean aFlag) {
    	if(aFlag)
            refresh();
    	super.setVisible(aFlag); 
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
        cardTextField.requestFocus();
    }

    public synchronized void refresh(){
		cardTextField.setText("");
	}
    
    public void addDeclineListener(ActionListener listener){
		declinebtn.addActionListener(listener);
	}
    
	public void addAcceptListener(ActionListener listener){
		acceptbtn.addActionListener(listener);
	}
	
	public String getCardNumber() {
		return cardTextField.getText();
		
	}
	
	public static synchronized GetCardNumberPanel getInstance(){
        if (instance == null) instance = new GetCardNumberPanel();
        return instance;
    }
}
