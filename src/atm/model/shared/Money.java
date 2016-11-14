package atm.model.shared;

public class Money {
    private long cents;

    public Money(int dollars) {
        this(dollars, 0);
    }

    public Money(double dollars){
        this.cents = (long) (dollars*100);
    }

    public Money(int dollars, int cents) {
        this.cents = 100L * dollars + cents;
    }

    public Money(Money that) {
        this.cents = that.cents;
    }

    public String toString() {
        return "$" + cents / 100 +
                (cents % 100 >= 10 ? "." + cents % 100 : ".0" + cents % 100);
    }

    public void add(Money amountToAdd) {
        this.cents += amountToAdd.cents;
    }

    public void subtract(Money amountToSubtract) {
        this.cents -= amountToSubtract.cents;
    }

    public boolean lessEqual(Money compareTo) {
        return this.cents <= compareTo.cents;
    }
}
