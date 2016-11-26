package atm.model.shared;

public class Message {
    // Possible values for messageCode
    public enum MessageCode {
        WITHDRAWAL, TRANSFER, INQUIRY
    }

    private MessageCode messageCode;
    private int serialNumber;
    private Client fromAccount;
    private Client toAccount;
    private Money amount;

    public Message(MessageCode messageCode, int serialNumber, Client fromAccount, Client toAccount, Money amount) {
        this.messageCode = messageCode;
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

        result += "\nCARD# " + fromAccount.getCard().getNumber();
        result += "\nTRANS# " + serialNumber;
        if (fromAccount != null && fromAccount.getBalance().getCents() >= 0)
            result += "\nFROM  " + fromAccount;
        else
            result += "\nNO FROM";
        if (toAccount != null && toAccount.getBalance().getCents() >= 0)
            result += "\nTO  " + toAccount;
        if (amount != null && amount.getCents() > 0)
            result += "\nAMOUNT = " + amount;

        return result;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public Client getFromAccount() {
        return fromAccount;
    }

    public Client getToAccount() {
        return toAccount;
    }

    public Money getAmount() {
        return amount;
    }
}