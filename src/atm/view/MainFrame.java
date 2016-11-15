package atm.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Zimme on 15-Nov-16.
 */
public class MainFrame extends JFrame {
    public MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GUI guiPanel = new GUI();
        add(guiPanel);
        setJMenuBar(createMenuBar());
    }

    JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(quitItem);
        menuBar.add(fileMenu);
        return menuBar;
    }
}
