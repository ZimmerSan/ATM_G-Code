package atm.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static atm.tools.ViewConstants.*;

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
    
    public static void addActionToMap(JComponent panel, final JButton button, int keyEvent){
    	String stringForMap = keyEvent+"ActionKeyboard";
    	panel.getInputMap().put(KeyStroke.getKeyStroke(keyEvent,0),stringForMap);
    	panel.getActionMap().put(stringForMap, new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button.doClick();
			}
		});
    }
}
