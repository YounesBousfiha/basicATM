package org.example.infrastructure.Service;

import org.example.domain.IRepository.AccountRepository;
import org.example.domain.IRepository.TransactionRepository;
import org.example.domain.exceptions.InsufficientBalanceException;
import org.example.domain.exceptions.NegativeAmountException;
import org.example.domain.exceptions.UserNotFoundException;
import org.example.domain.models.Account;
import org.example.domain.service.IBankService;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class BankServiceImpl implements IBankService {

    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;
    private final ConcurrentHashMap<Integer, ReentrantLock> accountLocks = new ConcurrentHashMap<>();

    public BankServiceImpl (AccountRepository accountRepo, TransactionRepository transactionRepo) {
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }


    private ReentrantLock getLockAccount(int accountNumber) {
        return accountLocks.computeIfAbsent(accountNumber, k -> new ReentrantLock());
    }

    @Override
    public  void  withdraw(int accountNumber, double amount) {
        ReentrantLock lock = getLockAccount(accountNumber);
        lock.lock();
        try {
            Account account = accountRepo.findByAccountNumber(accountNumber);
            if(java.util.Objects.isNull(account)) {
                throw new UserNotFoundException("user not Found");
            }
            double balance = account.getBalance();
            if(balance < amount) {
                throw new InsufficientBalanceException("Insufficient Balance");
            }

            account.setBalance(balance - amount);
            accountRepo.update(account);
        } catch (UserNotFoundException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public  void deposit(int accountNumber, double amount) {
        ReentrantLock lock = getLockAccount(accountNumber);
        lock.lock();

        try {
            Account account = accountRepo.findByAccountNumber(accountNumber);
            if(java.util.Objects.isNull(account)) {
                throw new UserNotFoundException("User not Found!");
            }
            if(amount < 0) {
                throw new NegativeAmountException("Amount is negative");
            }
            account.setBalance(account.getBalance() + amount);
            accountRepo.update(account);
        } catch (UserNotFoundException | NegativeAmountException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public  void transfer(int fromAccountNumber, int toAccountNumber, double amount) {

    }

    @Override
    public  double getBalance(int accountNumber) {
        return 0;
    }
}