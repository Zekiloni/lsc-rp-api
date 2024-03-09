package com.crp.ucp.account.authentication;

import com.crp.ucp.account.AccountEntity;
import com.crp.ucp.account.AccountMapper;
import com.crp.ucp.account.AccountService;
import com.crp.ucp.account.exception.AccountNotFoundException;
import com.crp.ucp.server.model.Authentication;
import com.crp.ucp.server.model.AuthenticationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountMapper accountMapper;

    private final AccountService accountService;

    private final JwtService jwtService;

    public AuthenticationStatus handle(Authentication auth) {
        AccountEntity account = this.accountService.getByUsername(auth.getUsername())
                .orElseThrow(() -> new AccountNotFoundException(format("Account with username {0} does not exist", auth.getUsername())));

        boolean isAuthenticated = BCrypt.checkpw(auth.getPassword(), account.getPassword());

        if (!isAuthenticated) {
            throw new BadCredentialsException();
        }

        String token = jwtService.generateToken(auth.getUsername());

        AuthenticationStatus authStatus = new AuthenticationStatus();
        authStatus.setAccount(accountMapper.mapTo(account));
        authStatus.setToken(token);

        return authStatus;
    }


}
