package org.example.domain.service;

public interface IBankService {
    public void withdraw(int accountNumber, double amount);
    public void deposit(int accountNumber, double amount);
    public void transfer(int fromAccountNumber, int toAccountNumber, double amount);
    public double getBalance(int accountNumber);
}
