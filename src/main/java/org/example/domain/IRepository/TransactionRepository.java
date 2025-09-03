package org.example.domain.IRepository;

import org.example.domain.models.Transaction;

import java.util.concurrent.CopyOnWriteArrayList;

public interface TransactionRepository {
    public Transaction save(Transaction transaction);
    public CopyOnWriteArrayList<Transaction> getTransactions();
}
