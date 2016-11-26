package atm.dao;

import atm.model.shared.Client;
import atm.tools.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DatabaseConnector dbConnector = new DatabaseConnector();

    public static Client getClientByCardNumber(String number){
        try {
            Connection connection = dbConnector.getConnection();
            String query = "SELECT * FROM atm WHERE CARD = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, number);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) return new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5), rs.getString(6));
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    public static List<Client> getAvailableClients() {
        List<Client> clients = new ArrayList<>();

        try {
            Connection connection = dbConnector.getConnection();
            Statement stmt = connection.createStatement();

            String selTable = "SELECT * FROM atm";
            stmt.execute(selTable);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                clients.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5), rs.getString(6)));
            }
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return clients;
    }

    public static void updateClientInfo(int id, String name, String password, long balance, String email) throws Exception {
        Connection connection = dbConnector.getConnection();
        String updateStatement = "UPDATE atm SET NAME = ?, PASSWORD = ?, BALANCE = ?, EMAIL = ? Where id = ?";
        PreparedStatement statement = connection.prepareStatement(updateStatement);
        statement.setString(1, name);
        statement.setString(2, password);
        statement.setLong(3, balance);
        statement.setString(4, email);
        statement.setInt(5, id);
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
