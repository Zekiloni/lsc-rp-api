package com.crp.ucp.account.authorization;

import com.crp.ucp.account.AccountEntity;
import com.crp.ucp.account.AccountMapper;
import com.crp.ucp.account.AccountService;
import com.crp.ucp.account.exception.AccountNotFoundException;
import com.crp.ucp.server.model.Authorize;
import com.crp.ucp.server.model.AuthorizeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final AccountMapper accountMapper;

    private final AccountService accountService;

    public AuthorizeStatus handle(Authorize authorize) {
        AccountEntity account = this.accountService.getByUsername(authorize.getUsername())
                .orElseThrow(() -> new AccountNotFoundException(format("Account with username {0} does not exist", authorize.getUsername())));

        boolean isAuthenticated = BCrypt.checkpw(authorize.getPassword(), account.getPassword());

        if (!isAuthenticated) {
            throw new WrongPasswordException();
        }

        AuthorizeStatus authorizeStatus = new AuthorizeStatus();
        authorizeStatus.setAccount(accountMapper.mapTo(account));
        authorizeStatus.setToken(UUID.randomUUID().toString());

        return authorizeStatus;
    }


}
