package atm.tools;

import javax.swing.*;

import static atm.tools.Constants.D_KEY_HEIGHT;
import static atm.tools.Constants.D_KEY_WIDTH;

public class ViewUtils {
    public static final JButton createDigitButton(String label, int x, int y) {
        return createButton(label, x, y, D_KEY_WIDTH, D_KEY_HEIGHT);
    }

    public static final JButton createButton(String label, int x, int y, int width, int height){
        JButton button = new JButton(label);
        button.setSize(width, height);
        button.setLocation(x, y);
        return button;
    }
}
