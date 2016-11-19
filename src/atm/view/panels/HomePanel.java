package atm.view.panels;

import atm.view.KeyBoardView;

import javax.swing.*;
import java.awt.*;

import static atm.tools.GUIConstants.*;

public class HomePanel extends JPanel{
    private static HomePanel instance;

    KeyBoardView keyboardView;
    JPanel headerView;
    JPanel screenView;

    private HomePanel(){
        setLayout(null);
        setSize(MAINFRAME_WIDTH, MAINFRAME_HEIGHT);
        init();
    }

    private void init() {
        //Keyboard init
        keyboardView = KeyBoardView.newInstance();
        keyboardView.setLocation(40, 360);
        keyboardView.setVisible(true);
        add(keyboardView);

        // Header init
        headerView = initHeader();
        add(headerView);

        // Default Screen init
        screenView = new JPanel();
        screenView.setSize(440, 280);
        screenView.setLocation(40, 60);
        screenView.setBackground(SCREEN_BACKGROUND_COLOR);
        add(screenView);
    }

    private JPanel initHeader() {
        JPanel headerView = new JPanel();
        headerView.setSize(440,20);
        headerView.setLocation(40, 40);
        headerView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        headerView.setBackground(HEADER_BACKGROUND_COLOUR);
        headerView.setLayout(null);

        JLabel atmName = new JLabel("G-ATM");
        atmName.setFont(new Font("Arial", Font.PLAIN, 12));
        atmName.setSize(60, 14);
        atmName.setLocation(370, 3);
        headerView.add(atmName);

        return headerView;
    }

    public static synchronized HomePanel newInstance(){
        if (instance == null) instance = new HomePanel();
        return instance;
    }
}
