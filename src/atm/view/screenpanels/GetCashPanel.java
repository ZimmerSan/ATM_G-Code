package atm.view.screenpanels;

import atm.tools.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GetCashPanel extends JPanel{
	
	private static final int ROW_1_X = 20;
	private static final int ROW_2_X = 340;
	private static final int ROW_1_Y = 100;
	private static final int ROW_2_Y = 160;
	private static final int ROW_3_Y = 220;

	private static GetCashPanel instance;
	private JTextField amountTextField;
	private JButton get50, get100, get200, get500, get1000, ownAmount;
	JButton declinebtn;

    private GetCashPanel(){
    	super();
    	ViewUtils.setupDefaultScreenPanel(this);
    	
    	init();
    }
    
    private void init(){
    	JLabel label = new JLabel("Please, choose amount:", SwingConstants.CENTER);
    	label.setForeground(Color.white);
    	label.setSize(280, 40);
    	label.setLocation(80, 20);
    	label.setFont(new Font("Arial", Font.PLAIN, 14));
    	add(label);
    	
    	amountTextField = new JTextField();
    	amountTextField.setSize(160, 40);
    	amountTextField.setLocation(140, 80);
    	add(amountTextField);
    	
    	//COL_1 x=20/340 y=100/160/220 Don't touch =)
    	get50 = ViewUtils.createButton("50", ROW_1_X, ROW_1_Y, 80, 40);
    	get100 = ViewUtils.createButton("100", ROW_1_X, ROW_2_Y, 80, 40);
    	get200 = ViewUtils.createButton("200", ROW_1_X, ROW_3_Y, 80, 40);
    	//COL_2
    	get500 = ViewUtils.createButton("200", ROW_2_X, ROW_1_Y, 80, 40);
    	get1000 = ViewUtils.createButton("1000", ROW_2_X, ROW_2_Y, 80, 40);
    	ownAmount = ViewUtils.createButton("Enter", ROW_2_X, ROW_3_Y, 80, 40);
    	ViewUtils.addActionToMap(this, ownAmount, KeyEvent.VK_ENTER);
    	ViewUtils.addActionToMap(amountTextField, ownAmount, KeyEvent.VK_ENTER);
    	
    	add(get50);
    	add(get100);
    	add(get200);
    	add(get500);
    	add(get1000);
    	add(ownAmount);
    	
    	declinebtn= new JButton();
    	ViewUtils.addActionToMap(amountTextField, declinebtn, KeyEvent.VK_ESCAPE);
    	ViewUtils.addActionToMap(this, declinebtn, KeyEvent.VK_ESCAPE);
    	
    }

    @Override
    public void setVisible(boolean aFlag) {
    	if(aFlag) refresh();
    	super.setVisible(aFlag);
    }
    
    public static synchronized GetCashPanel getInstance(){
        if (instance == null) instance = new GetCashPanel();
        return instance;
    }
    
    public void refresh(){
    	amountTextField.setText("");
    }
    
    public String getCustomAmount(){
    	return amountTextField.getText();
    }

	public void setCustomAmount(long amount){
    	amountTextField.setText(String.valueOf(amount));
    }
    
    public void add50AL(ActionListener al){
    	get50.addActionListener(al);
    }
    
    public void add100AL(ActionListener al){
    	get100.addActionListener(al);
    }
    
    public void add200AL(ActionListener al){
    	get200.addActionListener(al);
    }
    
    public void add500AL(ActionListener al){
    	get500.addActionListener(al);
    }
    
    public void add1000AL(ActionListener al){
    	get1000.addActionListener(al);
    }
    
    public void addCustomAmountAL(ActionListener al){
    	ownAmount.addActionListener(al);
    }
    
    public void addDeclineListener(ActionListener listener){
		declinebtn.addActionListener(listener);
	}
    
    
}
