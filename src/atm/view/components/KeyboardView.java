package atm.view.components;

import static atm.tools.GUIConstants.*;
import static atm.tools.ViewUtils.createButton;
import static atm.tools.ViewUtils.createDigitButton;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KeyboardView extends JPanel {
    private static KeyboardView instance;

    private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private JButton btnCancel;
    private JButton btnClear;
    private JButton btnEnter;
    
    public static synchronized KeyboardView getInstance() {
        if (instance == null) instance = new KeyboardView();
        return instance;
    }

    private KeyboardView() {
        super();
        setSize(KEYBOARD_WIDTH, KEYBOARD_HEIGHT);
        setLayout(null);
        init();
    }

    private void init() {
        b1 = createDigitButton("1", 80, ROW_1_Y);
        b2 = createDigitButton("2", 140, ROW_1_Y);
        b3 = createDigitButton("3", 200, ROW_1_Y);
        b4 = createDigitButton("4", 80, ROW_2_Y);
        b5 = createDigitButton("5", 140, ROW_2_Y);
        b6 = createDigitButton("6", 200, ROW_2_Y);
        b7 = createDigitButton("7", 80, ROW_3_Y);
        b8 = createDigitButton("8", 140, ROW_3_Y);
        b9 = createDigitButton("9", 200, ROW_3_Y);
        b0 = createDigitButton("0", 140, ROW_4_Y);

        btnCancel = createButton("Cancel", 280, ROW_1_Y, 80, 40);
        btnClear = createButton("Clear", 280, ROW_2_Y, 80, 40);
        btnEnter = createButton("Enter", 280, ROW_3_Y, 80, 40);

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

    

}
