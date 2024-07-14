package com.crp.ucp.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<AccountEntity> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    public Optional<AccountEntity> getAccountById(Integer id) {
        return this.accountRepository.findById(id);
    }

    public Optional<AccountEntity> getAccountByEmail(String email) {
        return this.accountRepository.findByEmail(email);
    }

    public List<AccountEntity> getByUsernameOrEmail(String username, String email) {
        return accountRepository.findByUsernameContainingOrEmailContaining(username, email);
    }

    public Optional<AccountEntity> getAccountByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }

    public AccountEntity createAccount(AccountEntity account) {
        account.setPassword(hashPassword(account.getPassword()));
        account.setCreatedAt(OffsetDateTime.now());
        account.setAdmin(0);
        account.setMuted(0);

        accountRepository.save(account);

        return account;
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
