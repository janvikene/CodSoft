import java.util.Scanner;

class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance = balance - amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {

    BankAccount account;
    Scanner sc = new Scanner(System.in);

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void menu() {

        int choice;

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    checkBalance();
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);
    }

    public void checkBalance() {
        System.out.println("Current Balance = Rs. " + account.getBalance());
    }

    public void deposit(double amount) {

        if (amount > 0) {
            account.deposit(amount);
            System.out.println("Amount deposited successfully.");
            System.out.println("Updated Balance = Rs. " + account.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (account.withdraw(amount)) {
            System.out.println("Please collect your cash.");
            System.out.println("Remaining Balance = Rs. " + account.getBalance());
        } else {
            System.out.println("Insufficient Balance.");
        }
    }
}

public class ATMSystem {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(10000);

        ATM atm = new ATM(account);

        atm.menu();
    }
}
