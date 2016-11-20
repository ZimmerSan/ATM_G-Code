package atm.view.components;

import static atm.tools.ViewConstants.KEYBOARD_HEIGHT;
import static atm.tools.ViewConstants.KEYBOARD_LOCATION_HEIGHT;
import static atm.tools.ViewConstants.KEYBOARD_LOCATION_WIDTH;
import static atm.tools.ViewConstants.KEYBOARD_WIDTH;
import static atm.tools.ViewConstants.ROW_1_X;
import static atm.tools.ViewConstants.ROW_1_Y;
import static atm.tools.ViewConstants.ROW_2_X;
import static atm.tools.ViewConstants.ROW_2_Y;
import static atm.tools.ViewConstants.ROW_3_X;
import static atm.tools.ViewConstants.ROW_3_Y;
import static atm.tools.ViewConstants.ROW_4_X;
import static atm.tools.ViewConstants.ROW_4_Y;
import static atm.tools.ViewUtils.createDigitButton;
import static atm.tools.ViewUtils.createFuncButton;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KeyboardView extends JPanel {
    private static KeyboardView instance;

    private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private JButton btnCancel;
    private JButton btnClear;
    private JButton btnEnter;
    private Robot robot;
    
    public static synchronized KeyboardView getInstance() {
        if (instance == null) instance = new KeyboardView();
        return instance;
    }

    private KeyboardView() {
        super();
        setSize(KEYBOARD_WIDTH, KEYBOARD_HEIGHT);
        setLocation(KEYBOARD_LOCATION_WIDTH, KEYBOARD_LOCATION_HEIGHT);
        setLayout(null);
        init();
        initActionListners();
    }

    private void init() {
        b1 = createDigitButton("1", ROW_1_X, ROW_1_Y);
        b2 = createDigitButton("2", ROW_2_X, ROW_1_Y);
        b3 = createDigitButton("3", ROW_3_X, ROW_1_Y);
        b4 = createDigitButton("4", ROW_1_X, ROW_2_Y);
        b5 = createDigitButton("5", ROW_2_X, ROW_2_Y);
        b6 = createDigitButton("6", ROW_3_X, ROW_2_Y);
        b7 = createDigitButton("7", ROW_1_X, ROW_3_Y);
        b8 = createDigitButton("8", ROW_2_X, ROW_3_Y);
        b9 = createDigitButton("9", ROW_3_X, ROW_3_Y);
        b0 = createDigitButton("0", ROW_2_X, ROW_4_Y);

        btnCancel = createFuncButton("Cancel", ROW_4_X, ROW_1_Y);
        btnClear = createFuncButton("Clear", ROW_4_X, ROW_2_Y);
        btnEnter = createFuncButton("Enter", ROW_4_X, ROW_3_Y);

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(b0);
        add(btnCancel);
        add(btnClear);
        add(btnEnter);
    }
    
    private void initActionListners(){
    	try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	initButton(b1);
    	initButton(b2);
    	initButton(b3);
    	initButton(b4);
    	initButton(b5);
    	initButton(b6);
    	initButton(b7);
    	initButton(b8);
    	initButton(b9);
    	initButton(b0);
    	initButton(btnEnter);
    	initButton(btnClear);
    	initButton(btnCancel);
    }
    
    private void initButton(JButton btn){
    	String btnText = btn.getText();
    	
    	int key = KeyEvent.VK_0;
    	switch (btnText) {
		case "1":
			key = KeyEvent.VK_1;
			break;
		case "2":
			key = KeyEvent.VK_2;
			break;
		case "3":
			key = KeyEvent.VK_3;
			break;
		case "4":
			key = KeyEvent.VK_4;
			break;
		case "5":
			key = KeyEvent.VK_5;
			break;
		case "6":
			key = KeyEvent.VK_6;
			break;
		case "7":
			key = KeyEvent.VK_7;
			break;
		case "8":
			key = KeyEvent.VK_8;
			break;
		case "9":
			key = KeyEvent.VK_9;
			break;
		case "Enter":
			key = KeyEvent.VK_ENTER;
			break;
		case "Cancel":
			key = KeyEvent.VK_ESCAPE;
			break;
		case "Clear":
			key = KeyEvent.VK_BACK_SPACE;
			break;
			
		default:
			break;
		}
    	final int keyf = key;
    	btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				robot.keyPress(keyf);
				robot.keyRelease(keyf);
				System.out.println(btnText);
			}
		});
    	btn.setFocusable(false);
    }
    
    

    

}
