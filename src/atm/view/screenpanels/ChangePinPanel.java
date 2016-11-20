package atm.view.screenpanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import atm.tools.ViewUtils;

public class ChangePinPanel extends JPanel{

	private static ChangePinPanel instance;
	
	JPasswordField oldPasswordField, newPasswordField, confirmPasswordField;
	JButton acceptPINbtn;
	JButton declinePINbtn;
	

    private ChangePinPanel(){
    	super();
    	ViewUtils.setupDefaultScreenPanel(this);
    	
    	init();
    }
    
    private void init(){
    	JLabel oldLabel = new JLabel("Input old PIN:", SwingConstants.LEFT);
    	oldLabel.setForeground(Color.white);
    	oldLabel.setSize(120, 40);
    	oldLabel.setLocation(40, 20);
    	oldLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    	add(oldLabel);
    	
    	oldPasswordField = new JPasswordField(4);
    	oldPasswordField.setSize(200, 40);
    	oldPasswordField.setLocation(200, 20);
    	add(oldPasswordField);
    	
    	JLabel pinLabel = new JLabel("Input new PIN:", SwingConstants.LEFT);
    	pinLabel.setForeground(Color.white);
    	pinLabel.setSize(120, 40);
    	pinLabel.setLocation(40, 80);
    	pinLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    	add(pinLabel);
    	
    	newPasswordField = new JPasswordField(4);
    	newPasswordField.setSize(200, 40);
    	newPasswordField.setLocation(200, 80);
    	add(newPasswordField);
    	
    	JLabel confirmPinLabel = new JLabel("Confirm new PIN:", SwingConstants.LEFT);
    	confirmPinLabel.setForeground(Color.white);
    	confirmPinLabel.setSize(120, 40);
    	confirmPinLabel.setLocation(40, 140);
    	confirmPinLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    	add(confirmPinLabel);
    	
    	confirmPasswordField = new JPasswordField(4);
    	confirmPasswordField.setSize(200, 40);
    	confirmPasswordField.setLocation(200, 140);
    	add(confirmPasswordField);
    	
    	
    	acceptPINbtn = ViewUtils.createButton("Accept", 100, 200, 100, 40);
    	declinePINbtn = ViewUtils.createButton("Decline", 240, 200, 100, 40);
    	
    	ViewUtils.addActionToMap(this, acceptPINbtn, KeyEvent.VK_ENTER);
    	ViewUtils.addActionToMap(this, declinePINbtn, KeyEvent.VK_ESCAPE);
    	ViewUtils.addActionToMap(oldPasswordField, acceptPINbtn, KeyEvent.VK_ENTER);
    	ViewUtils.addActionToMap(oldPasswordField, declinePINbtn, KeyEvent.VK_ESCAPE);
    	ViewUtils.addActionToMap(newPasswordField, acceptPINbtn, KeyEvent.VK_ENTER);
    	ViewUtils.addActionToMap(newPasswordField, declinePINbtn, KeyEvent.VK_ESCAPE);
    	ViewUtils.addActionToMap(confirmPasswordField, acceptPINbtn, KeyEvent.VK_ENTER);
    	ViewUtils.addActionToMap(confirmPasswordField, declinePINbtn, KeyEvent.VK_ESCAPE);
    	
    	add(acceptPINbtn);
    	add(declinePINbtn);
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        refresh();
    }

    public synchronized void refresh(){
		// TODO: 19-Nov-16 clear text fields
		oldPasswordField.setText("");
		newPasswordField.setText("");
		confirmPasswordField.setText("");	
	}
    
    public String getOldPin(){
        return String.valueOf(oldPasswordField.getPassword());
    }
    public String getNewPin(){
        return String.valueOf(newPasswordField.getPassword());
    }
    public String getConfirmPin(){
        return String.valueOf(confirmPasswordField.getPassword());
    }

	public void addDeclineListener(ActionListener listener){
		declinePINbtn.addActionListener(listener);
	}
	public void addAcceptListener(ActionListener listener){
		acceptPINbtn.addActionListener(listener);
	}
	
	

    public static synchronized ChangePinPanel getInstance(){
        if (instance == null) instance = new ChangePinPanel();
        return instance;
    }
}
