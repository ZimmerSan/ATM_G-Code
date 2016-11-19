package atm.view.ScreenPanels;

import javax.swing.*;

import atm.view.components.KeyBoardView;

import java.awt.*;

import static atm.tools.GUIConstants.*;

public class StartPanel extends JPanel{
    private static StartPanel instance;

    private StartPanel(){
    	setLayout(null);
        setSize(440, 280);
        setBackground(SCREEN_BACKGROUND_COLOR);
    }

    public static synchronized StartPanel getInstance(){
        if (instance == null) instance = new StartPanel();
        return instance;
    }
}
