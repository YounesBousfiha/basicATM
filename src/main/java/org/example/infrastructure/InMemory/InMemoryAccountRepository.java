package org.example.infrastructure.InMemory;

import org.example.domain.IRepository.AccountRepository;
import org.example.domain.models.Account;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAccountRepository implements AccountRepository {

    private final ConcurrentHashMap <Integer, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public Account findByAccountNumber(int accountNumber) {
        return accounts.get(accountNumber);
    }

    @Override
    public void update(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    @Override
    public void save(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }


}
