package atm.components;

/**
 * Created by KOKOWKA on 14.11.2016.
 */
public class Screen {
    public Screen() {
    }

    public void display(String message) {
        System.out.println(message);
    }

    public int readPIN(String prompt) throws Cancelled {
        System.out.println("readPIN");
        return 0;
    }

    public synchronized int readMenuChoice(String prompt, String[] menu) throws Cancelled {
        System.out.println("readMenuChoice");
        return 0;
    }

    public synchronized void readAmount(String prompt) throws Cancelled {
        System.out.println(prompt);
    }

    public static class Cancelled extends Exception {
        public Cancelled() {
            super("Cancelled by customer");
        }
    }
}
