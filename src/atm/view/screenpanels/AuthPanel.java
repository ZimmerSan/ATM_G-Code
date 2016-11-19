package atm.view.screenpanels;

import java.awt.Font;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import atm.tools.ViewUtils;

public class AuthPanel extends JPanel {

	private static AuthPanel instance;
	TextField cardTextField;
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
    	cardLabel.setSize(120, 40);
    	cardLabel.setLocation(40, 20);
    	cardLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    	add(cardLabel);
    	
    	cardTextField = new TextField();
    	cardTextField.setSize(200, 40);
    	cardTextField.setLocation(200, 20);
    	add(cardTextField);
    	
    	JLabel pinLabel = new JLabel("Input Card Number:", SwingConstants.RIGHT);
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
    
    private void initActionListners(){
    	// TODO: Action Listners
    	
    }

    public static synchronized AuthPanel getInstance(){
        if (instance == null) instance = new AuthPanel();
        return instance;
    }
}
