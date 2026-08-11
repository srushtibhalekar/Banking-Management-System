package dao;

import model.Account;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public void createAccount(Account account) {

        String sql = "INSERT INTO accounts " +
                "(name, email, account_type, balance) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, account.getName());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getAccountType());
            ps.setDouble(4, account.getBalance());

            ps.executeUpdate();

            System.out.println("Account created successfully!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Account> getAllAccounts() {

        List<Account> accounts = new ArrayList<>();

        String sql = "SELECT * FROM accounts ORDER BY account_id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Account account = new Account();

                account.setAccountId(rs.getInt("account_id"));
                account.setName(rs.getString("name"));
                account.setEmail(rs.getString("email"));
                account.setAccountType(rs.getString("account_type"));
                account.setBalance(rs.getDouble("balance"));

                accounts.add(account);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return accounts;
    }

    public void deposit(int id, double amount) {

        String sql =
                "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Amount deposited successfully!");
            } else {
                System.out.println("Account not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void withdraw(int id, double amount) {

        String sql =
                "UPDATE accounts " +
                "SET balance = balance - ? " +
                "WHERE account_id = ? AND balance >= ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setInt(2, id);
            ps.setDouble(3, amount);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Amount withdrawn successfully!");
            } else {
                System.out.println(
                        "Insufficient balance or account not found!"
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteAccount(int id) {

        String sql = "DELETE FROM accounts WHERE account_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Account deleted successfully!");
            } else {
                System.out.println("Account not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}