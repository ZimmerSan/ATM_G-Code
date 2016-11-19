package atm.view.screenpanels;

import static atm.tools.ViewConstants.SCREEN_BACKGROUND_COLOR;

import javax.swing.JPanel;

import atm.tools.ViewUtils;

public class StartPanel extends JPanel{
    private static StartPanel instance;

    private StartPanel(){
    	super();
    	ViewUtils.setupDefaultScreenPanel(this);
    }

    public static synchronized StartPanel getInstance(){
        if (instance == null) instance = new StartPanel();
        return instance;
    }
}
