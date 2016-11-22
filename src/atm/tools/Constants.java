package atm.tools;

import javax.swing.*;

public interface Constants {
    int PIN_LENGTH = 4;

    // Titles
    enum MessageType {
        ERROR("Error", JOptionPane.ERROR_MESSAGE),
        INFO("Info", JOptionPane.INFORMATION_MESSAGE);
        private final String title;
        private final int type;

        MessageType(String title, int type) {
            this.title = title;
            this.type = type;
        }
        public String getTitle() {
            return title;
        }
        public int getType() {
            return type;
        }
    }
    String TITLE_ERROR = "Error";
    String TITLE_VALID = "Valid";

    // Info
    String CARD_EJECTED = "Take your card, please.";
    String PIN_CHANGED = "PIN has been changed.";

    // Warnings

    // Errors
    String ERR_INVALID_NEW_PIN = "PINs do not match or do not consist of only " + PIN_LENGTH + " digit(s).";
    String ERR_INVALID_OLD_PIN = "Invalid old PIN";
    String ERR_INVALID_CLIENT = "Can not get user. Check entered data, please.";
    String ERR_CANNOT_OBTAIN_CLIENT = "Can not obtain current session user";

}
