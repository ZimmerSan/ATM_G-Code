package atm.tools;

import javax.swing.*;

public interface Constants {
    int PIN_LENGTH = 4;

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


    // Info
    String CARD_EJECTED = "Take your card, please.";
    String PIN_CHANGED = "PIN has been changed.";
    String SELF_TRANSFER = "Transfer to your own card has no sense.";

    // Confirmations
    String CONFIRM_PRINT_CHECK = "Would you like to print check?";

    // Errors
    String ERR_INVALID_NEW_PIN = "PINs do not match or do not consist of only " + PIN_LENGTH + " digit(s).";
    String ERR_INVALID_OLD_PIN = "Invalid old PIN";
    String ERR_INVALID_CLIENT = "Can not get user. Check entered data, please.";
    String ERR_INVALID_NUMBER = "Bad number.";
    String ERR_INVALID_CARD = "Card doesn't exist in DB.";
    String ERR_UNKNOWN = "We have some problems. Please, contact our administrator.";

}
