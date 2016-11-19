package atm.view.ScreenPanels;

import static atm.tools.GUIConstants.SCREEN_BACKGROUND_COLOR;

import javax.swing.JPanel;

public class StartPanel extends JPanel{
    private static StartPanel instance;

    private StartPanel(){
    	setLayout(null);
    	setLocation(40, 60);
        setSize(440, 280);
        setBackground(SCREEN_BACKGROUND_COLOR);
    }

    public static synchronized StartPanel getInstance(){
        if (instance == null) instance = new StartPanel();
        return instance;
    }
}
