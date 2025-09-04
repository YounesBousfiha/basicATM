package org.example.domain.service;

public interface IBankService {
    public boolean authenticate(int accountNumber, int pin);
    public boolean withdraw(int accountNumber, double amount);
    public boolean deposit(int accountNumber, double amount);
    public void transfer(int fromAccountNumber, int toAccountNumber, double amount);
    public double getBalance(int accountNumber);
}
