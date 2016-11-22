package atm.model.shared.exception;

/**
 * Created by KOKOWKA on 21.11.2016.
 */
public class MoneyException extends  Exception {
    public MoneyException(){
        super("Not enough money");
    }

}
