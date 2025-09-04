package org.example.infrastructure.Service;

import org.example.domain.IRepository.AccountRepository;
import org.example.domain.IRepository.TransactionRepository;
import org.example.domain.exceptions.InsufficientBalanceException;
import org.example.domain.exceptions.NegativeAmountException;
import org.example.domain.exceptions.UserNotFoundException;
import org.example.domain.exceptions.WrongCredsException;
import org.example.domain.models.Account;
import org.example.domain.service.IBankService;

import java.util.Objects;
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

    public boolean authenticate(int accountNumber, int pin) {
        try {
            Account account = accountRepo.findByAccountNumber(accountNumber);
            if(java.util.Objects.isNull(account)) {
                throw new UserNotFoundException("User not Found");
            }
            if(account.getPin() != pin) {
                throw new WrongCredsException("InCorrect PIN");
            }
            return true;
        } catch (UserNotFoundException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public  boolean  withdraw(int accountNumber, double amount) {
        ReentrantLock lock = getLockAccount(accountNumber);
        lock.lock();
        try {
            Account account = accountRepo.findByAccountNumber(accountNumber);
            if(Objects.isNull(account)) {
                throw new UserNotFoundException("user not Found");
            }
            double balance = account.getBalance();
            if(balance < amount) {
                throw new InsufficientBalanceException("Insufficient Balance");
            }

            account.setBalance(balance - amount);
            accountRepo.update(account);
            return true;
        } catch (UserNotFoundException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public  boolean deposit(int accountNumber, double amount) {
        ReentrantLock lock = getLockAccount(accountNumber);
        lock.lock();

        try {
            Account account = accountRepo.findByAccountNumber(accountNumber);
            if(Objects.isNull(account)) {
                throw new UserNotFoundException("User not Found!");
            }
            if(amount < 0) {
                throw new NegativeAmountException("Amount is negative");
            }
            account.setBalance(account.getBalance() + amount);
            accountRepo.update(account);
            return true;
        } catch (UserNotFoundException | NegativeAmountException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public  void transfer(int fromAccountNumber, int toAccountNumber, double amount) {

        int first = Math.min(fromAccountNumber, toAccountNumber);
        int second = Math.max(fromAccountNumber, toAccountNumber);

        ReentrantLock lockFirst = getLockAccount(first);
        ReentrantLock lockSecond = getLockAccount(second);
        lockFirst.lock();
        lockSecond.lock();
        try {
        Account From = accountRepo.findByAccountNumber(fromAccountNumber);
        Account To = accountRepo.findByAccountNumber(toAccountNumber);
        if(java.util.Objects.isNull(From) || java.util.Objects.isNull(To)) {
            throw new UserNotFoundException("Sender or Receiver are not Found !");
        }
        double balance = From.getBalance();
        if(balance < amount) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        } catch (UserNotFoundException | InsufficientBalanceException | NegativeAmountException e) {
            System.out.println(e.getMessage());
        } finally {
            lockSecond.unlock();
            lockFirst.unlock();
        }
    }

    @Override
    public  double getBalance(int accountNumber) {
        Account account = null;
        try {
            account = accountRepo.findByAccountNumber(accountNumber);
            if(Objects.isNull(account)) {
                throw new UserNotFoundException("User not Found!");
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return account.getBalance();
    }
}