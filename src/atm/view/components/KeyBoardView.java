package atm.view.components;

import javax.swing.*;

import static atm.tools.ViewUtils.createButton;
import static atm.tools.ViewUtils.createDigitButton;

public class KeyBoardView extends JPanel{
	private static KeyBoardView instance;

	public static synchronized KeyBoardView getInstance(){
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
		b1 = createDigitButton("1", 80, 40);
		b2 = createDigitButton("2", 140, 40);
		b3 = createDigitButton("3", 200, 40);
		b4 = createDigitButton("4", 80, 100);
		b5 = createDigitButton("5", 140, 100);
		b6 = createDigitButton("6", 200, 100);
		b7 = createDigitButton("7", 80, 160);
		b8 = createDigitButton("8", 140, 160);
		b9 = createDigitButton("9", 200, 160);
		b0 = createDigitButton("0", 140, 220);

		btnInputCard = createButton("Input Card", 140, 0, 160, 20);
		btnCancel = createButton("Cancel", 280, 40, 80, 40);
		btnClear = createButton("Clear", 280, 100, 80, 40);
		btnEnter = createButton("Enter", 280, 160, 80, 40);

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

}
