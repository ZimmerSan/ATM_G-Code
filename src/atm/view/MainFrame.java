package atm.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andrii Voitenko on 18-Nov-16.
 */
public class MainFrame extends JFrame {
	
	private static final Color FRAME_BACKGROUND_COLOR = new Color(237, 238, 240);
	private static final Color HEADER_BACKGROUND_COLOUR = new Color(30, 143, 50);
	private static final Color SCREEN_BACKGROUND_COLOR = new Color(136, 183, 80);
	
	KeyBoardView keyboardView;
	JPanel headerView;
	JPanel screenView;
	
    public MainFrame() {
        super("ATM");
        setSize(520, 660);
        
        setLayout(null);
        init();
        
        //repaint();
		setVisible(true);
		setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void init(){
    	//Keyboard init
    	keyboardView = KeyBoardView.newInstance();
    	keyboardView.setLocation(40, 360);
    	add(keyboardView);
    	keyboardView.setVisible(true);
    	
    	// Header init
    	headerView = new JPanel();
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
    	add(headerView);
    	
    	// Default Screen init
    	screenView = new JPanel();
    	screenView.setSize(440, 280);
    	screenView.setLocation(40, 60);
    	screenView.setBackground(SCREEN_BACKGROUND_COLOR);
    	add(screenView);
    	//setVisible(true);
    	
    }
    
}
