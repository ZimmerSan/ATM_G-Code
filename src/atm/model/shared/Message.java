package atm.model.shared;

public class Message {
    // Possible values for messageCode
    public enum MessageCode {
        WITHDRAWAL, TRANSFER, INQUIRY
    }

    private MessageCode messageCode;
    private Card card;
    private String pin;
    private int serialNumber;
    private int fromAccount;
    private int toAccount;
    private Money amount;

    public Message(MessageCode messageCode, Card card, String pin, int serialNumber, int fromAccount, int toAccount, Money amount) {
        this.messageCode = messageCode;
        this.card = card;
        this.pin = pin;
        this.serialNumber = serialNumber;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public String toString() {
        String result = "";

        switch (messageCode) {
            case WITHDRAWAL:
                    result += "WITHDRAW";
                break;

            case TRANSFER:
                result += "TRANSFER";
                break;

            case INQUIRY:
                result += "INQUIRY ";
                break;
        }

        result += " CARD# " + card.getNumber();
        result += " TRANS# " + serialNumber;
        if (fromAccount >= 0)
            result += " FROM  " + fromAccount;
        else
            result += " NO FROM";
        if (toAccount >= 0)
            result += " TO  " + toAccount;
        else
            result += " NO TO";
        if (amount.getCents() > 0)
            result += " AMOUNT = " + amount;
        else
            result += " NO AMOUNT";

        return result;
    }

    public void setPIN(String pin) {
        this.pin = pin;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public Card getCard() {
        return card;
    }

    public String getPIN() {
        return pin;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public Money getAmount() {
        return amount;
    }
}