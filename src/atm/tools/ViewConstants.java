package atm.tools;

import java.awt.*;

public interface ViewConstants extends Constants{
    // MainFrame
    Color MAINFRAME_BACKGROUND_COLOR = new Color(237, 238, 240);
    int MAINFRAME_WIDTH = 520;
    int MAINFRAME_HEIGHT = 670;

    //Keyboard
    //Size && Location
    int KEYBOARD_WIDTH = 440;
    int KEYBOARD_HEIGHT = 320;
    int KEYBOARD_LOCATION_WIDTH = 40;
    int KEYBOARD_LOCATION_HEIGHT = 380;
    //Digit-btn size
    int D_KEY_WIDTH =  45;
    int D_KEY_HEIGHT = D_KEY_WIDTH;
    //Functioanal-btn size
    int F_KEY_WIDTH =  80;
    int F_KEY_HEIGHT = 40;
    //Buttons location
    int ROW_1_X = 80;
    int ROW_2_X = 140;
    int ROW_3_X = 200;
    int ROW_4_X = 280;
    int ROW_1_Y = 20;
    int ROW_2_Y = 80;
    int ROW_3_Y = 140;
    int ROW_4_Y = 200;

    //Cardreader
    int CARDREADER_WIDTH = KEYBOARD_WIDTH;
    int CARDREADER_HEIGHT = 20;
    int CARDREADER_LOCATION_WIDTH = 40;
    int CARDREADER_LOCATION_HEIGHT = 360;
    
    
    
    //HeaderView
    int HEADER_WIDTH = 440;
    int HEADER_HEIGHT = 20;
    int HEADER_LOCATION_WIDTH = 40;
    int HEADER_LOCATION_HEIGHT = 380; 
    Color HEADER_BACKGROUND_COLOUR = new Color(30, 143, 50);
    
    //ScreanView
    int SCREEN_WIDTH = 440;
    int SCREEN_HEIGHT = 280;
    int SCREEN_LOCATION_WIDTH = 40;
    int SCREEN_LOCATION_HEIGHT = 60; 
    Color SCREEN_BACKGROUND_COLOR = new Color(136, 183, 80);
    
    
}
