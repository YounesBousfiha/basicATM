package org.example.application.ui;

import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner = new Scanner(System.in);

    public synchronized String readAccountNumber() {
        System.out.print("Enter Account Number");
        return scanner.nextLine();
    }

    public synchronized String readPin() {
        System.out.print("Enter PIN: ");
        return scanner.nextLine();
    }

    public synchronized double readAmount() {
        System.out.println("Enter Amount: ");
        while(!scanner.hasNextDouble()) {
            System.out.print("Invalid. Enter numeric amount: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public synchronized void showBalance(double balance) {
        System.out.println("Current balance: " + balance);
    }

    public synchronized void showMessage(String message) {
        System.out.println(message);
    }

    public synchronized int showMenu() {
        System.out.println("\n1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("0. Exit");

        System.out.print("Choose option: ");
        return scanner.nextInt();
    }

    public synchronized void showError(String error) {
        System.err.println("Error: " + error);
    }

    public synchronized String readTargetAccount() {
        scanner.nextLine();
        System.out.print("Enter Target account number: ");
        return scanner.nextLine();
    }

}
