package atm.tools;

import static atm.tools.ViewConstants.D_KEY_HEIGHT;
import static atm.tools.ViewConstants.D_KEY_WIDTH;
import static atm.tools.ViewConstants.F_KEY_HEIGHT;
import static atm.tools.ViewConstants.F_KEY_WIDTH;
import static atm.tools.ViewConstants.SCREEN_BACKGROUND_COLOR;
import static atm.tools.ViewConstants.SCREEN_HEIGHT;
import static atm.tools.ViewConstants.SCREEN_LOCATION_HEIGHT;
import static atm.tools.ViewConstants.SCREEN_LOCATION_WIDTH;
import static atm.tools.ViewConstants.SCREEN_WIDTH;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

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
    
    public static void addActionToMap(JComponent panel, JButton button, int keyEvent){
    	String stringForMap = keyEvent+"ActionKeyboard";
    	panel.getInputMap().put(KeyStroke.getKeyStroke(keyEvent,0),stringForMap);
    	panel.getActionMap().put(stringForMap, new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(panel.getClass()+" "+button.getText()+keyEvent);
				button.doClick();
			}
		});
    	//System.out.println(panel.getClass()+" "+button.getText()+keyEvent);
    }
}
