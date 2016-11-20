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
    String INFO_VALID_PIN_LENGTH = "Valid PIN length.";
    String CARD_EJECTED = "Card has been ejected.";

    // Warnings

    // Errors
    String ERR_INVALID_PIN_LENGTH = "Invalid PIN length - must be " + PIN_LENGTH + " digits long.";
    String ERR_INVALID_CLIENT = "Can not get user. Check entered data, please.";
    String ERR_CANNOT_OBTAIN_CLIENT = "Can not obtain current session user";

}
