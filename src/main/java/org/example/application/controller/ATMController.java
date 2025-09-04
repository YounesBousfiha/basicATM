package org.example.application.controller;

import org.example.application.ui.ConsoleUI;
import org.example.domain.service.IBankService;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ATMController {
    private final IBankService bankService;
    private final ConsoleUI consoleUI;

    public ATMController(IBankService bankService, ConsoleUI consoleUI) {
        this.bankService = bankService;
        this.consoleUI = consoleUI;
    }

    public void startSession() {
        int accountNumber = Integer.parseInt(consoleUI.readAccountNumber());
        int pin = Integer.parseInt(consoleUI.readPin());

        if(!bankService.authenticate(accountNumber, pin)) {
            consoleUI.showError("Authentication Failed");
            return;
        }

        boolean running = true;
        while (running) {
            int choice = consoleUI.showMenu();
            switch (choice) {
                case 1  -> checkBalance(accountNumber);
                case 2 -> withdraw(accountNumber);
                case 3 -> deposit(accountNumber);
                case 4 -> transfer(accountNumber);
                case 0 -> running = false;
            }
        }
        consoleUI.showMessage("Session ended");
    }

    private void checkBalance(int accountNumber) {
        double balance = bankService.getBalance(accountNumber);
        consoleUI.showBalance(balance);
    }

    private void withdraw(int accountNumber) {
        double amount = consoleUI.readAmount();
        boolean success = bankService.withdraw(accountNumber, amount);
        consoleUI.showMessage(success ? "Withdraw Success" : "Withdraw Failed");
    }

    private void deposit(int accountNumber) {
        double amount = consoleUI.readAmount();
        boolean success = bankService.deposit(accountNumber, amount);
        consoleUI.showMessage(success ? "Deposit Success" : "Deposit Failed");
    }

    private void transfer(int fromAccount) {}
}


class SessionTask implements Runnable {
    private final ATMController atmController;

    public SessionTask(ATMController atmController) {
        this.atmController = atmController;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started.");
        atmController.startSession();
        System.out.println(Thread.currentThread().getName() + " ended.");
    }
}

