package atm.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final int frameHeight = 768, frameWidth = 768;
    private final Color FrameColor = new Color(187, 224, 208);
    private JPanel mainPanel;


    public MainFrame(){
        setTitle("atm.ATM Simulator");
        setSize(frameHeight, frameWidth);
        setLocation(20, 20);
        setResizable(false);
        Container container = getContentPane();
//        mainPanel = new MainPanel();
        mainPanel.setBackground(FrameColor);
        container.add(mainPanel);
    }
}
