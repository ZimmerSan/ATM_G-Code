package atm.view.screenpanels;

import atm.tools.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AuthPanel extends JPanel {

	private static AuthPanel instance;
	JTextField cardTextField;
	JPasswordField passwordField;
	JButton acceptbtn;
	JButton declinebtn;
	

    private AuthPanel(){
    	super();
    	ViewUtils.setupDefaultScreenPanel(this);
    	
    	init();
    	initActionListners();
    }
    
    private void init(){
    	JLabel cardLabel = new JLabel("Input Card Number:", SwingConstants.RIGHT);
    	cardLabel.setForeground(Color.white);
    	cardLabel.setSize(120, 40);
    	cardLabel.setLocation(40, 20);
    	cardLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    	add(cardLabel);
    	
    	cardTextField = new JTextField();
    	cardTextField.setSize(200, 40);
    	cardTextField.setLocation(200, 20);
    	add(cardTextField);
    	
    	JLabel pinLabel = new JLabel("Input Card Number:", SwingConstants.RIGHT);
    	pinLabel.setForeground(Color.white);
    	pinLabel.setSize(120, 40);
    	pinLabel.setLocation(40, 80);
    	pinLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    	add(pinLabel);
    	
    	passwordField = new JPasswordField(4);
    	passwordField.setSize(200, 40);
    	passwordField.setLocation(200, 80);
    	add(passwordField);
    	
    	acceptbtn = ViewUtils.createButton("Accept", 100, 180, 100, 40);
    	declinebtn = ViewUtils.createButton("Decline", 240, 180, 100, 40);
    	add(acceptbtn);
    	add(declinebtn);
    }

    @Deprecated
    private void initActionListners(){
    	// TODO: Action Listners
    	
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        refresh();
    }

    public synchronized void refresh(){
		// TODO: 19-Nov-16 clear text fields
		cardTextField.setText("");
		passwordField.setText("");
	}

	public void addDeclineListener(ActionListener listener){
		declinebtn.addActionListener(listener);
	}
	public void addAcceptListener(ActionListener listener){
		acceptbtn.addActionListener(listener);
	}

    public String getEnteredCardNumber(){
        return cardTextField.getText();
    }

    public String getEnteredPin(){
        return String.valueOf(passwordField.getPassword());
    }

    public static synchronized AuthPanel getInstance(){
        if (instance == null) instance = new AuthPanel();
        return instance;
    }
}
