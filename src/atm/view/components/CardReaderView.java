package atm.view.components;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

import static atm.tools.ViewConstants.CARDREADER_HEIGHT;
import static atm.tools.ViewConstants.CARDREADER_WIDTH;
import static atm.tools.ViewConstants.CARDREADER_LOCATION_HEIGHT;
import static atm.tools.ViewConstants.CARDREADER_LOCATION_WIDTH;
import static atm.tools.ViewUtils.createButton;

public class CardReaderView extends JPanel{
    private static CardReaderView instance;

    private JButton btnInsertCard;

    private CardReaderView(){
        super();
        setSize(CARDREADER_WIDTH, CARDREADER_HEIGHT);
        setLocation(CARDREADER_LOCATION_WIDTH, CARDREADER_LOCATION_HEIGHT);
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        btnInsertCard = createButton("Insert Card", 140, 0, 160, 20);
        btnInsertCard.setBackground(Color.white);
        add(btnInsertCard);
    }

    @Override
    public void setEnabled(boolean enabled) {
        btnInsertCard.setVisible(enabled);
        super.setEnabled(enabled);
    }

    public void addActionListener(ActionListener listener){
        btnInsertCard.addActionListener(listener);
    }

    public static synchronized CardReaderView getInstance() {
        if (instance == null) instance = new CardReaderView();
        return instance;
    }
}
