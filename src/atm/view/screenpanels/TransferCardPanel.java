package atm.view.screenpanels;

import atm.tools.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class TransferCardPanel extends JPanel {

    private static TransferCardPanel instance;
    private JTextField cardTextField;
    private JButton acceptBtn;
    private JButton declineBtn;


    private TransferCardPanel() {
        super();
        ViewUtils.setupDefaultScreenPanel(this);
        init();
    }

    private void init() {
        JLabel cardLabel = new JLabel("Payee's Card Number:", SwingConstants.LEFT);
        cardLabel.setForeground(Color.white);
        cardLabel.setSize(140, 40);
        cardLabel.setLocation(30, 80);
        cardLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(cardLabel);

        cardTextField = new JTextField();
        cardTextField.setSize(200, 40);
        cardTextField.setLocation(200, 80);

        add(cardTextField);

        acceptBtn = ViewUtils.createButton("Accept", 100, 180, 100, 40);
        declineBtn = ViewUtils.createButton("Decline", 240, 180, 100, 40);

        ViewUtils.addActionToMap(this, acceptBtn, KeyEvent.VK_ENTER);
        ViewUtils.addActionToMap(this, declineBtn, KeyEvent.VK_ESCAPE);
        ViewUtils.addActionToMap(cardTextField, acceptBtn, KeyEvent.VK_ENTER);
        ViewUtils.addActionToMap(cardTextField, declineBtn, KeyEvent.VK_ESCAPE);

        add(acceptBtn);
        add(declineBtn);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) refresh();
        super.setVisible(aFlag);
    }

    public synchronized void refresh() {
        cardTextField.setText("");
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
        cardTextField.requestFocus();
    }

    public void addDeclineListener(ActionListener listener) {
        declineBtn.addActionListener(listener);
    }

    public void addAcceptListener(ActionListener listener) {
        acceptBtn.addActionListener(listener);
    }

    public String getCardNumber() {
        return cardTextField.getText();
    }

    public static synchronized TransferCardPanel getInstance() {
        if (instance == null) instance = new TransferCardPanel();
        return instance;
    }
}
