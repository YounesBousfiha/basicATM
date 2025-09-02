package org.example.domain.models;

import org.example.domain.models.enums.TransactionType;

public class Transaction {

    private TransactionType type;
    private double amount;
    private String fromAccount;
    private String toAccount;

    public Transaction() {}

    public Transaction(TransactionType type, double amount, String fromAccount, String toAccount) {
        this.type = type;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getToAccount() {
        return toAccount;
    }
}
