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


    int getAccountNumber() {
        return this.accountNumber;
    }

    int getPin() {
        return this.pin;
    }

    double getBalance() {
        return this.balance;
    }

    void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    void setPin(int pin) {
        this.pin = pin;
    }

    void setBalance(double balance) {
        this.balance = balance;
    }
}
