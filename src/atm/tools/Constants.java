package atm.tools;

public interface Constants {
    int PIN_LENGTH = 4;

    // Titles
    String TITLE_ERROR = "Error";
    String TITLE_VALID = "Valid";

    // Info
    String INFO_VALID_PIN_LENGTH = "Valid PIN length.";

    // Warnings

    // Errors
    String ERR_INVALID_PIN_LENGTH = "Invalid PIN length - must be " + PIN_LENGTH + " digits long.";
}
