package atm.model.shared;

public class Client {
    private int id;
    private String name, pass;
    private Card card;
    private Money balance;
    private String email;

    public Client(int id, String name, String card, String pass, int balance, String email) {
    	this.id = id;
        this.name = name;
        this.card = new Card(card);
        this.pass = pass;
        this.balance = new Money(balance);
        this.email = email;
    }

    public String toString() {
        return id+" "+ name + " "+ card +" "+ pass +" "+ balance +" "+ email;
    }

    public void setPass(String pass) {
        this.pass =  pass;
    }
    public void setBalance(Money balance) {
        this.balance = balance;
    }
    public Money getBalance() {
        return balance;
    }
    public String getName() {
        return name;
    }
    public String getPass() {
        return pass;
    }
    public String getEmail() {
        return email;
    }
	public int getId() {
		return id;
	}
    public Card getCard() {
        return card;
    }
}
