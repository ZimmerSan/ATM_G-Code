package atm.model.shared.exception;

/**
 * Created by KOKOWKA on 19.11.2016.
 */
public class CardRetained extends Exception {
    public CardRetained() {
        super("Card retained due to too many invalid PINs");
    }
}
