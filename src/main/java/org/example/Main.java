package org.example;

import org.example.application.controller.ConcurrentSessionManager;
import org.example.application.ui.ConsoleUI;
import org.example.domain.IRepository.AccountRepository;
import org.example.domain.IRepository.TransactionRepository;
import org.example.domain.models.Account;
import org.example.domain.service.IBankService;
import org.example.infrastructure.InMemory.InMemoryAccountRepository;
import org.example.infrastructure.InMemory.inMemoryTransactionRepository;
import org.example.infrastructure.Service.BankServiceImpl;


public class Main {
    public static void main(String[] args) {
        AccountRepository accountRepo = new InMemoryAccountRepository();
        TransactionRepository transactionRepo = new inMemoryTransactionRepository();
        IBankService bankService = new BankServiceImpl(accountRepo, transactionRepo);
        ConsoleUI consoleUI = new ConsoleUI();


        accountRepo.save(new Account(123, 1111, 1000.0));

        ConcurrentSessionManager manager = new ConcurrentSessionManager(3, bankService, consoleUI);
        manager.startSessions(3);



        manager.shutdown();
    }
}