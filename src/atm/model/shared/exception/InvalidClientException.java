package atm.model.shared.exception;

import atm.tools.Constants;

public class InvalidClientException extends Exception {
    public InvalidClientException() {
        super(Constants.ERR_INVALID_CLIENT);
    }
}
