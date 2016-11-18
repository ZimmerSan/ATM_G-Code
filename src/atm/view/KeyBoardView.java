package atm.view;

import javax.swing.*;

import static atm.tools.Constants.D_KEY_HEIGHT;
import static atm.tools.Constants.D_KEY_WIDTH;

public class KeyBoardView extends JPanel{
	private static KeyBoardView instance;

	public static synchronized KeyBoardView newInstance(){
		if (instance == null) instance = new KeyBoardView();
		return instance;
	}

	private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnEnter;
	private JButton btnInputCard;

	private KeyBoardView() {
		super();
		setSize(440,320);
		setLayout(null);
		initKeyboard();
	}

	private void initKeyboard(){
		b1 = getDigitButton("1", 80, 40);
		b2 = getDigitButton("2", 140, 40);
		b3 = getDigitButton("3", 200, 40);
		b4 = getDigitButton("4", 80, 100);
		b5 = getDigitButton("5", 140, 100);
		b6 = getDigitButton("6", 200, 100);
		b7 = getDigitButton("7", 80, 160);
		b8 = getDigitButton("8", 140, 160);
		b9 = getDigitButton("9", 200, 160);
		b0 = getDigitButton("0", 140, 220);

		btnInputCard = getButton("Input Card", 140, 0, 160, 20);
		btnCancel = getButton("Cancel", 280, 40, 80, 40);
		btnClear = getButton("Clear", 280, 100, 80, 40);
		btnEnter = getButton("Enter", 280, 160, 80, 40);

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

	private final JButton getDigitButton(String label, int x, int y) {
		return getButton(label, x, y, D_KEY_WIDTH, D_KEY_HEIGHT);
	}

	private final JButton getButton(String label, int x, int y, int width, int height){
		JButton button = new JButton(label);
		button.setSize(width, height);
		button.setLocation(x, y);
		return button;
	}

}
