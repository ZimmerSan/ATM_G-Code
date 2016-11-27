package atm.view.screenpanels;

import atm.tools.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AuthPanel extends JPanel {

	private static AuthPanel instance;
	JPasswordField passwordField;
	JButton acceptBtn;
	JButton declineBtn;
	

    private AuthPanel(){
    	super();
    	ViewUtils.setupDefaultScreenPanel(this);
    	init();
    }
    
    private void init(){
    	JLabel pinLabel = new JLabel("Input PIN:", SwingConstants.LEFT);
    	pinLabel.setForeground(Color.white);
    	pinLabel.setSize(120, 40);
    	pinLabel.setLocation(40, 80);
    	pinLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    	add(pinLabel);
    	
    	passwordField = new JPasswordField(4);
    	passwordField.setSize(200, 40);
    	passwordField.setLocation(200, 80);
    	add(passwordField);
    	
    	acceptBtn = ViewUtils.createButton("Accept", 100, 180, 100, 40);
    	declineBtn = ViewUtils.createButton("Decline", 240, 180, 100, 40);
    	
    	ViewUtils.addActionToMap(this, acceptBtn, KeyEvent.VK_ENTER);
    	ViewUtils.addActionToMap(this, declineBtn, KeyEvent.VK_ESCAPE);
    	ViewUtils.addActionToMap(passwordField, acceptBtn, KeyEvent.VK_ENTER);
    	ViewUtils.addActionToMap(passwordField, declineBtn, KeyEvent.VK_ESCAPE);
    	
    	add(acceptBtn);
    	add(declineBtn);
    }

    @Override
    public void setVisible(boolean aFlag) {
    	if(aFlag)
            refresh();
    	super.setVisible(aFlag);
    }

    public void refresh(){
		passwordField.setText("");
	}

	@Override
	public void requestFocus() {
		super.requestFocus();
		passwordField.requestFocus();
	}

	public void addDeclineListener(ActionListener listener){
		declineBtn.addActionListener(listener);
	}
    
	public void addAcceptListener(ActionListener listener){
		acceptBtn.addActionListener(listener);
	}
	
    public String getEnteredPin(){
        return String.valueOf(passwordField.getPassword());
    }

    public static synchronized AuthPanel getInstance(){
        if (instance == null) instance = new AuthPanel();
        return instance;
    }
}
