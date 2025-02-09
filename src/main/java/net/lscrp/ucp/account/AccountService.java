package net.lscrp.ucp.account;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public List<AccountEntity> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    public Page<AccountEntity> getAllAccounts(Pageable pageable) {
        return this.accountRepository.findAll(pageable);
    }

    public Optional<AccountEntity> getAccountById(Integer id) {
        return this.accountRepository.findById(id);
    }

    public Optional<AccountEntity> getAccountByEmail(String email) {
        return this.accountRepository.findByEmail(email);
    }

    public Page<AccountEntity> getByUsernameOrEmail(String username, String email, Pageable pageable) {
        return accountRepository.findByUsernameContainingOrEmailContaining(username, email, pageable);
    }

    public Page<AccountEntity> getByUsernameAndEmail(String username, String email, Pageable pageable) {
        return accountRepository.findByUsernameContainingAndEmailContaining(username, email, pageable);
    }

    public Optional<AccountEntity> getAccountByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }

    public AccountEntity createAccount(AccountEntity account) {
        account.setPassword(hashPassword(account.getPassword()));
        account.setCreatedAt(OffsetDateTime.now());
        account.setAdmin(0);
        account.setMuted(0);
        account.setPremium(0);
        account.setPremiumCoins(0);

        accountRepository.save(account);

        return account;
    }

    public AccountEntity update(AccountEntity account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Integer id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getAccountByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
