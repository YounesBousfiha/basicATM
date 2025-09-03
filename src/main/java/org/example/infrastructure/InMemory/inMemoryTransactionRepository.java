package org.example.infrastructure.InMemory;

import org.example.domain.IRepository.TransactionRepository;
import org.example.domain.models.Transaction;

import java.util.concurrent.CopyOnWriteArrayList;

public class inMemoryTransactionRepository implements TransactionRepository {

    private final CopyOnWriteArrayList<Transaction> transactions = new CopyOnWriteArrayList<>();

    @Override
    public Transaction save(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public CopyOnWriteArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
