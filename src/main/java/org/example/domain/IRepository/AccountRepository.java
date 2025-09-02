package org.example.domain.IRepository;
import org.example.domain.models.Account;

public interface AccountRepository {
    public Account findByAccountNumber(int accountNumber);
    public Account update(Account account);
}
