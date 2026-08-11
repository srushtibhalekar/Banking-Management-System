package main;

import dao.AccountDAO;
import model.Account;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AccountDAO dao = new AccountDAO();

        while (true) {

            System.out.println("\n=================================");
            System.out.println("     BANKING MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Delete Account");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Account Type: ");
                    String type = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();

                    Account account =
                            new Account(name, email, type, balance);

                    dao.createAccount(account);

                    break;

                case 2:

                    List<Account> accounts = dao.getAllAccounts();

                    System.out.println("\n----------- ACCOUNTS -----------");

                    for (Account a : accounts) {

                        System.out.println(
                                "ID: " + a.getAccountId()
                                + " | Name: " + a.getName()
                                + " | Email: " + a.getEmail()
                                + " | Type: " + a.getAccountType()
                                + " | Balance: ₹" + a.getBalance()
                        );
                    }

                    break;

                case 3:

                    System.out.print("Enter Account ID: ");
                    int depositId = sc.nextInt();

                    System.out.print("Enter Amount: ");
                    double depositAmount = sc.nextDouble();

                    if (depositAmount <= 0) {
                        System.out.println("Invalid amount!");
                    } else {
                        dao.deposit(depositId, depositAmount);
                    }

                    break;

                case 4:

                    System.out.print("Enter Account ID: ");
                    int withdrawId = sc.nextInt();

                    System.out.print("Enter Amount: ");
                    double withdrawAmount = sc.nextDouble();

                    if (withdrawAmount <= 0) {
                        System.out.println("Invalid amount!");
                    } else {
                        dao.withdraw(withdrawId, withdrawAmount);
                    }

                    break;

                case 5:

                    System.out.print("Enter Account ID: ");
                    int deleteId = sc.nextInt();

                    dao.deleteAccount(deleteId);

                    break;

                case 6:

                    System.out.println("Thank you for using Banking System!");
                    sc.close();
                    System.exit(0);

                default:

                    System.out.println("Invalid choice!");
            }
        }
    }
}