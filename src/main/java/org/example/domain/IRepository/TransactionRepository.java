package org.example.domain.IRepository;

import org.example.domain.models.Transaction;
public interface TransactionRepository {
    public Transaction save(Transaction transaction);
}
