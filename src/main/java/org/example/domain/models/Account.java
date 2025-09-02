package org.example.domain.models;

public class Account {

    private int accountNumber;
    private int pin;
    private double balance;


    public Account() {}

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }


    public int getAccountNumber() {
        return this.accountNumber;
    }

    public int getPin() {
        return this.pin;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
