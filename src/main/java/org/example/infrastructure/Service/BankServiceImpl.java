package org.example.infrastructure.Service;

import org.example.domain.IRepository.AccountRepository;
import org.example.domain.IRepository.TransactionRepository;
import org.example.domain.exceptions.InsufficientBalanceException;
import org.example.domain.exceptions.UserNotFoundException;
import org.example.domain.models.Account;
import org.example.domain.service.IBankService;

import java.util.concurrent.locks.ReentrantLock;

public class BankServiceImpl implements IBankService {

    private final ReentrantLock lock = new ReentrantLock();
    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;


    public BankServiceImpl (AccountRepository accountRepo, TransactionRepository transactionRepo) {
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public  void  withdraw(int accountNumber, double amount) {
        if(lock.tryLock()) {
            try {
                Account account = accountRepo.findByAccountNumber(accountNumber);
                if(account.equals(null)) {
                    throw new UserNotFoundException("User not found");
                }

                if(account.getBalance() < amount) {
                    throw new InsufficientBalanceException("No enough money in your account");
                } else {
                    account.setBalance(account.getBalance() - amount);
                    accountRepo.update(account);
                }
            } catch (UserNotFoundException | InsufficientBalanceException e) {
                System.out.println(e.getMessage());
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Couldn't Get the Lock");
        }
    }

    @Override
    public  void deposit(int accountNumber, double amount) {

    }

    @Override
    public  void transfer(int fromAccountNumber, int toAccountNumber, double amount) {

    }

    @Override
    public  double getBalance(int accountNumber) {
        return 0;
    }
}