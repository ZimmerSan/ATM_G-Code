package atm.view.screenpanels;

import atm.tools.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuPanel extends JPanel {

    private static MenuPanel instance;
    private JButton getCash, transferMoney, checkBalance;
    private JButton changePin;
    private JButton closeSessionBtn;

    public static final int BTN_WIDTH = 180;
    public static final int BTN_HEIGHT = 40;

    private MenuPanel() {
        super();
        ViewUtils.setupDefaultScreenPanel(this);
        init();
    }

    private void init() {
        JLabel label = new JLabel("Choose Action:", SwingConstants.CENTER);
        label.setForeground(Color.white);
        label.setSize(240, 20);
        label.setLocation(100, 20);
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        add(label);

        //ROW_1 x=20/160/300 y=60/140/220 Don't touch =)
        checkBalance = ViewUtils.createButton("Check Balance", 30, 60, BTN_WIDTH, BTN_HEIGHT);
        getCash = ViewUtils.createButton("Get Cash", 50 + BTN_WIDTH, 60, BTN_WIDTH, BTN_HEIGHT);
        //ROW_2
        transferMoney = ViewUtils.createButton("Transfer Money", 30, 60 + BTN_HEIGHT + 20, BTN_WIDTH, BTN_HEIGHT);
        changePin = ViewUtils.createButton("Change PIN", 50 + BTN_WIDTH, 60 + BTN_HEIGHT + 20, BTN_WIDTH, BTN_HEIGHT);
        //ROW_3
        closeSessionBtn = ViewUtils.createButton("Finish Work", 130, 200, BTN_WIDTH, BTN_HEIGHT);
        ViewUtils.addActionToMap(this, closeSessionBtn, KeyEvent.VK_ESCAPE);

        add(getCash);
        add(transferMoney);
        add(checkBalance);
        add(changePin);
        add(closeSessionBtn);
    }

    public static synchronized MenuPanel getInstance() {
        if (instance == null) instance = new MenuPanel();
        return instance;
    }

    // AL means ActionListener
    public void addWithdrawalAL(ActionListener al) {
        getCash.addActionListener(al);
    }

    public void addTransferAL(ActionListener al) {
        transferMoney.addActionListener(al);
    }

    public void addInquiryAL(ActionListener al) {
        checkBalance.addActionListener(al);
    }

    public void addChangePinAL(ActionListener al) {
        changePin.addActionListener(al);
    }

    public void addCloseSessionAL(ActionListener al) {
        closeSessionBtn.addActionListener(al);
    }
}
