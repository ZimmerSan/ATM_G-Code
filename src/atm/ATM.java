package atm;

final public class ATM {
//    private static Client currentClient;
//    private static MailServer mailServer;
//    private static DataManager dataManager;
//
//    private static List<Client> clients = new ArrayList<>();
//    private static List<String> transactions = new ArrayList<>();
//
//    private static final int CARD_NUMBER_LENGTH = 4;
//    private static final int CASH_LIMIT = 5000;
//    private static final int MIN_PHONE_RECHARGE = 5;
//    private static final int MIN_BALANCE_LIMIT = 10;
//    private static final int MIN_TRANSFER_LIMIT = 10;
//
//    private static int totalClients = 0;
//    private static int transactionId = 12345;
//    private static Integer totalCash = 0;
//    private static JFrame Window;
//
//    private static final String PANE_TITLE_ERROR = "Error";
//    private static final String EXCEEDED_LIMIT_ERROR = "Exceeded Limit";
//    private static final String NOT_ENOUGH_CASH_ERROR = "Not Enough Cash in atm.ATM";
//
//    void addTransaction(String tr) {
//        transactions.add(tr);
//    }
//
//    public static String nextTransaction() {
//        return String.valueOf(++transactionId);
//    }
//
//    public static void updateClientInfo(int id, String password, int balance) {
//        dataManager.updateClientInfo(id, password, balance);
//    }
//
//    public ATM() {
//        Window = GUI.getInstance().getATMwindow();
//        mailServer = new MailServer();
//        dataManager = new DataManager();
//        clients = dataManager.getAvailableClients();
//        totalClients = clients.size();
//    }
//
//    static boolean isClient(String card) {
//        for (int i = 0; i < totalClients; i++)
//            if (clients.get(i).getCard().equals(card)) {
//                currentClient = clients.get(i);
//                return true;
//            }
//        return false;
//    }
//
//    public static boolean enterCard(String input) {
//        return isClient(input);
//    }
//
//    public static boolean enterPIN(String inputPin) {
//        return currentClient.getPass().equals(inputPin);
//    }
//
//    public static boolean processWithdrawal(int amount) {
//        if (amount > CASH_LIMIT) {
//            JOptionPane.showMessageDialog(Window, EXCEEDED_LIMIT_ERROR, PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (amount > totalCash) {
//            JOptionPane.showMessageDialog(Window, NOT_ENOUGH_CASH_ERROR, PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (currentClient.getBalance() > amount + MIN_BALANCE_LIMIT) {
//            currentClient.setBalance(currentClient.getBalance() - amount);
//            totalCash -= amount;
//
//            JOptionPane.showMessageDialog(Window, "Заберіть готівку", "Успішна транзакція", JOptionPane.INFORMATION_MESSAGE);
//            updateClientInfo(currentClient.getId(), currentClient.getPass(), currentClient.getBalance());
//            mailServer.prepareMessage(currentClient.getName(), true, String.valueOf(amount), new java.util.Date().toString(), String.valueOf(currentClient.getBalance()), currentClient.getEmail());
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    static int search(String card) {
//        for (int i = 0; i < totalClients; i++)
//            if (clients.get(i).getCard().equals(card))
//                return i;
//        return -1;
//    }
//
//    public static boolean processTransfer(String fromCard, String toCard, int sum) {
//        if (toCard.equals(currentClient.getCard())) {
//            JOptionPane.showMessageDialog(Window, "Ви ввели свій номер картки", PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (sum < MIN_TRANSFER_LIMIT) {
//            JOptionPane.showMessageDialog(Window, "Мінімальна сума переказу: " + MIN_BALANCE_LIMIT + " грн", PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (toCard.length() != CARD_NUMBER_LENGTH) {
//            JOptionPane.showMessageDialog(Window, "Неправильний номер картки", PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (search(toCard) == -1) {
//            JOptionPane.showMessageDialog(Window, "Такої картки не існує", PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (sum > CASH_LIMIT) {
//            JOptionPane.showMessageDialog(Window, EXCEEDED_LIMIT_ERROR, PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (sum > currentClient.getBalance() - MIN_BALANCE_LIMIT) {
//            JOptionPane.showMessageDialog(Window, NOT_ENOUGH_CASH_ERROR, PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return true;
//        } else {
//            int order = search(toCard);
//            clients.get(order).setBalance(clients.get(order).getBalance() + sum);
//
//            int currentClientBalance = currentClient.getBalance();
//            currentClient.setBalance(currentClientBalance - sum);
//            JOptionPane.showMessageDialog(Window, "Кошти переказано", "Успішна транзакція", JOptionPane.INFORMATION_MESSAGE);
//
//            updateClientInfo(clients.get(order).getId(), clients.get(order).getPass(), clients.get(order).getBalance());
//            mailServer.prepareMessage(currentClient.getName(), false, String.valueOf(sum), new java.util.Date().toString(), String.valueOf(currentClient.getBalance()), currentClient.getEmail());
//
//            updateClientInfo(currentClient.getId(), currentClient.getPass(), currentClient.getBalance());
//            mailServer.prepareMessage(currentClient.getName(), true, String.valueOf(sum), new java.util.Date().toString(), String.valueOf(currentClient.getBalance()), currentClient.getEmail());
//            return true;
//        }
//    }
//
//    public static boolean phoneRecharge(int sum) {
//        if (sum < MIN_PHONE_RECHARGE) {
//            JOptionPane.showMessageDialog(Window, "Введіть більшу суму", PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (sum > CASH_LIMIT) {
//            JOptionPane.showMessageDialog(Window, EXCEEDED_LIMIT_ERROR, PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (sum > currentClient.getBalance() - MIN_BALANCE_LIMIT) {
//            JOptionPane.showMessageDialog(Window, NOT_ENOUGH_CASH_ERROR, PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//
//        currentClient.setBalance(currentClient.getBalance() - sum);
//        JOptionPane.showMessageDialog(Window, "Мобільний поповнено", "Успішна транзакція", JOptionPane.INFORMATION_MESSAGE);
//        updateClientInfo(currentClient.getId(), currentClient.getPass(), currentClient.getBalance());
//        mailServer.prepareMessage(currentClient.getName(), true, String.valueOf(sum), new java.util.Date().toString(), String.valueOf(currentClient.getBalance()), currentClient.getEmail());
//        return true;
//    }
//
//    public static boolean changePassword(String oldPwd, String newPwd1, String newPwd2) {
//        if (!oldPwd.equals(currentClient.getPass())) {
//            JOptionPane.showMessageDialog(Window, "Неправильний поточний пароль", PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (newPwd1.equals(newPwd2)) {
//            currentClient.setPass(newPwd2);
//            JOptionPane.showMessageDialog(Window, "Пароль змінено", "Зміна PIN-коду", JOptionPane.INFORMATION_MESSAGE);
//            updateClientInfo(currentClient.getId(), currentClient.getPass(), currentClient.getBalance());
//            mailServer.changePassword(currentClient.getName(), currentClient.getPass(), new java.util.Date().toString(), currentClient.getEmail());
//            return true;
//        } else {
//            JOptionPane.showMessageDialog(Window, "Пароль не змінено", PANE_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//    }
//
//    public static Client getCurrentClient() {
//        return currentClient;
//    }
//
//    public static int getTransactionId() {
//        return transactionId;
//    }
//
//    public static void setTransactionId(int iDtransaction) {
//        transactionId = iDtransaction;
//    }
//
//    public static Integer getTotalCash() {
//        return totalCash;
//    }
//
//    public static void setTotalCash(Integer totalCash) {
//        ATM.totalCash = totalCash;
//    }
//
//    public List<Client> getClients() {
//        return clients;
//    }
}
