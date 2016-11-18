package atm.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class KeyBoardView extends JPanel{
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	private JButton b8;
	private JButton b9;
	private JButton b0;
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnEnter;
	private JButton btnInputCard;
	
	public KeyBoardView() {
		// TODO Auto-generated constructor stub
		super();
		setSize(440,260);
		setLayout(null);
		initKeyboard();
	}
	

	private void initKeyboard(){
    	
    	b1 = getKeyboardButton("1", 80, 40, 40, 40);
    	b2 = getKeyboardButton("2", 140, 40, 40, 40);
    	b3 = getKeyboardButton("3", 200, 40, 40, 40);
    	b4 = getKeyboardButton("4", 80, 100, 40, 40);
    	b5 = getKeyboardButton("5", 140, 100, 40, 40);
    	b6 = getKeyboardButton("6", 200, 100, 40, 40);
    	b7 = getKeyboardButton("7", 80, 160, 40, 40);
    	b8 = getKeyboardButton("8", 140, 160, 40, 40);
    	b9 = getKeyboardButton("9", 200, 160, 40, 40);
    	b0 = getKeyboardButton("0", 140, 220, 40, 40);
    	
    	btnInputCard = getKeyboardButton("Input Card", 140, 0, 160, 20);
    	btnCancel = getKeyboardButton("Cancel", 280, 40, 80, 40);
    	btnClear = getKeyboardButton("Clear", 280, 100, 80, 40);
    	btnEnter = getKeyboardButton("Enter", 280, 160, 80, 40);
    	
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
		add(btnInputCard);
		add(btnCancel);
		add(btnClear);
		add(btnEnter);	
    	
    }

    private final JButton getKeyboardButton(String lable, int x, int y, int size_x, int size_y) {
		JButton button = new JButton(lable);
		button.setSize(size_x, size_y);
		button.setLocation(x, y);
		return button;
	}
}
