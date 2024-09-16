package com.crp.ucp.account.authentication;

import com.crp.ucp.account.AccountEntity;
import com.crp.ucp.account.AccountMapper;
import com.crp.ucp.account.AccountService;
import com.crp.ucp.account.log.LoginLogService;
import com.crp.ucp.security.JwtAuthenticationService;
import com.crp.ucp.server.model.Account;
import com.crp.ucp.server.model.Authentication;
import com.crp.ucp.server.model.AuthenticationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.crp.ucp.account.AccountUtil.throwAccountNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountMapper accountMapper;

    private final AccountService accountService;

    private final LoginLogService loginLogService;

    private final JwtAuthenticationService jwtAuthenticationService;

    public AuthenticationStatus handle(Authentication auth) {
        AccountEntity account = this.accountService.getAccountByUsername(auth.getUsername())
                .orElseThrow(() -> throwAccountNotFoundException(auth.getUsername()));

        boolean isAuthenticated = BCrypt.checkpw(auth.getPassword(), account.getPassword());

        if (!isAuthenticated) {
            throw new BadCredentialsException();
        }

        String token = jwtAuthenticationService.generateToken(account);

        AuthenticationStatus authStatus = new AuthenticationStatus();
        authStatus.setAccount(accountMapper.mapTo(account));
        authStatus.setToken(token);

        return authStatus;
    }

    public Optional<Account> validate(String token) {
        token = token.substring("Bearer ".length());
        String username = jwtAuthenticationService.extractUsername(token);
        if (username != null && jwtAuthenticationService.isTokenValid(token, username)) {
            AccountEntity account = accountService.getAccountByUsername(username)
                    .orElseThrow(() -> throwAccountNotFoundException(username));
            return Optional.of(accountMapper.mapTo(account));
        }
        return Optional.empty();
    }

}
