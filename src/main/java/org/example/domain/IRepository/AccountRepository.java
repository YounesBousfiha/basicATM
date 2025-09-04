package org.example.domain.IRepository;
import org.example.domain.models.Account;

public interface AccountRepository {
    public Account findByAccountNumber(int accountNumber);
    public void update(Account account);
    public void save(Account account);
}
