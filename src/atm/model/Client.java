package atm.model;

public class Client {
    private int id;
    private String name, card, pass;
    private int balance;
    private String email;

    public Client(int _id, String _name, String _card, String _pass, int _balance, String _email) {
    	this.id = _id;
        this.name = _name;
        this.card = _card;
        this.pass = _pass;
        this.balance = _balance;
        this.email = _email;
    }

    public String toString() {
        return id+" "+ name + " "+ card +" "+ pass +" "+ balance +" "+ email;
        
    }

    public void setPass(String _pass) {
        this.pass =  _pass;
    }


    public void setBalance(int _balance) {
        this.balance = _balance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public String getCard() {
        return card;
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
    
    
    
}
