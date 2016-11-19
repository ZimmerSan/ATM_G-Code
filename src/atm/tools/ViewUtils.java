package atm.tools;

import static atm.tools.ViewConstants.*;
import static atm.tools.ViewConstants.D_KEY_HEIGHT;
import static atm.tools.ViewConstants.D_KEY_WIDTH;
import static atm.tools.ViewConstants.F_KEY_WIDTH;
import static atm.tools.ViewConstants.SCREEN_BACKGROUND_COLOR;

import java.awt.Color;

import static atm.tools.ViewConstants.F_KEY_HEIGHT;

import javax.swing.JButton;
import javax.swing.JPanel;

import atm.view.screenpanels.MenuPanel;

public class ViewUtils {
	
	
    public static final JButton createDigitButton(String label, int x, int y) {
        return createButton(label, x, y, D_KEY_WIDTH, D_KEY_HEIGHT);
    }
    
    public static final JButton createFuncButton(String label, int x, int y) {
        return createButton(label, x, y, F_KEY_WIDTH, F_KEY_HEIGHT);
    }

    public static final JButton createButton(String label, int x, int y, int width, int height){
        JButton button = new JButton(label);
        button.setSize(width, height);
        button.setLocation(x, y);
        button.setBackground(Color.white);
        return button;
    }
    
    public static final void setupDefaultScreenPanel(JPanel panel){
    	panel.setLayout(null);
    	panel.setLocation(SCREEN_LOCATION_WIDTH, SCREEN_LOCATION_HEIGHT);
    	panel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    	panel.setBackground(SCREEN_BACKGROUND_COLOR);
    }
}
