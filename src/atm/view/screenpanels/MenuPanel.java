package atm.view.screenpanels;

import atm.tools.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuPanel extends JPanel {

    private static MenuPanel instance;
    JButton getCash, transferMoney, checkBalance;
    JButton deposits, changePin, somebtn2;
    JButton somebtn3, closeSessionBtn, somebtn5;

    private MenuPanel() {
        super();
        ViewUtils.setupDefaultScreenPanel(this);
        init();
    }

    private void init() {
        JLabel label = new JLabel("Chose Action:", SwingConstants.CENTER);
        label.setForeground(Color.white);
        label.setSize(240, 20);
        label.setLocation(100, 20);
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        add(label);

        //ROW_1 x=20/160/300 y=60/140/220 Don't touch =)
        getCash = ViewUtils.createButton("Get Cash", 20, 60, 120, 40);
        transferMoney = ViewUtils.createButton("Transfer Money", 160, 60, 120, 40);
        checkBalance = ViewUtils.createButton("Check Balance", 300, 60, 120, 40);
        //ROW_2
        deposits = ViewUtils.createButton("View Deposits", 20, 140, 120, 40);
        deposits.setEnabled(false);
        changePin = ViewUtils.createButton("Change PIN", 160, 140, 120, 40);
        somebtn2 = ViewUtils.createButton("Charity", 300, 140, 120, 40);
        somebtn2.setEnabled(false);

        //ROW_3
        //somebtn3 = ViewUtils.createButton("Random Action 3", 20, 220, 120, 40);
        closeSessionBtn = ViewUtils.createButton("Finish Work", 160, 220, 120, 40);
        ViewUtils.addActionToMap(this, closeSessionBtn, KeyEvent.VK_ESCAPE);

        //somebtn5 = ViewUtils.createButton("Random Action 5", 300, 220, 120, 40);

        add(getCash);
        add(transferMoney);
        add(checkBalance);
        add(deposits);
        add(changePin);
        add(somebtn2);
        //add(somebtn3);
        add(closeSessionBtn);
        //add(somebtn5);
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
