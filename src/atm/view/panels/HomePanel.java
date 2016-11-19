package atm.view.panels;

import atm.view.components.CardReaderView;
import atm.view.components.KeyBoardView;

import javax.swing.*;
import java.awt.*;

import static atm.tools.GUIConstants.*;

public class HomePanel extends JPanel{
    private static HomePanel instance;

    KeyBoardView keyboardView;
    CardReaderView cardReaderView;
    JPanel headerView;
    JPanel screenView;

    private HomePanel(){
        setLayout(null);
        setSize(MAINFRAME_WIDTH, MAINFRAME_HEIGHT);
        init();
    }

    private void init() {
        // Default Screen init
        screenView = new JPanel();
        screenView.setSize(440, 280);
        screenView.setLocation(40, 60);
        screenView.setBackground(SCREEN_BACKGROUND_COLOR);
        add(screenView);

        //CardReader init
        cardReaderView = CardReaderView.getInstance();
        cardReaderView.setLocation(40, 360);
        cardReaderView.setVisible(true);
        add(cardReaderView);

        //Keyboard init
        keyboardView = KeyBoardView.getInstance();
        keyboardView.setLocation(40, 380);
        keyboardView.setVisible(true);
        add(keyboardView);

        // Header init
        headerView = initHeader();
        add(headerView);


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

    public static synchronized HomePanel getInstance(){
        if (instance == null) instance = new HomePanel();
        return instance;
    }
}
