package com.crp.ucp.account.authentication;

import com.crp.ucp.account.AccountEntity;
import com.crp.ucp.account.AccountMapper;
import com.crp.ucp.account.AccountService;
import com.crp.ucp.account.AccountException;
import com.crp.ucp.security.JwtService;
import com.crp.ucp.security.RoleType;
import com.crp.ucp.server.model.Account;
import com.crp.ucp.server.model.Authentication;
import com.crp.ucp.server.model.AuthenticationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountMapper accountMapper;

    private final AccountService accountService;

    private final JwtService jwtService;

    public AuthenticationStatus handle(Authentication auth) {
        AccountEntity account = this.accountService.getAccountByUsername(auth.getUsername())
                .orElseThrow(() -> new AccountException(format("Account with username {0} does not exist", auth.getUsername())));

        boolean isAuthenticated = BCrypt.checkpw(auth.getPassword(), account.getPassword());

        if (!isAuthenticated) {
            throw new BadCredentialsException();
        }

        RoleType role = account.getAdmin() != 0 ? RoleType.ADMIN_ROLE : RoleType.USER_ROLE;
        String token = jwtService.generateToken(auth.getUsername(), account.getId(), role);

        AuthenticationStatus authStatus = new AuthenticationStatus();
        authStatus.setAccount(accountMapper.mapTo(account));
        authStatus.setToken(token);

        return authStatus;
    }

    public Optional<Account> validate(String token) {
        token = token.substring("Bearer ".length());
        token = token.substring(1, (token.length()) - 1);
        String username = jwtService.extractUsername(token);
        if (username != null && jwtService.isTokenValid(token, username)) {
            AccountEntity account = accountService.getAccountByUsername(username)
                    .orElseThrow(() -> new AccountException("Account not found for username: " + username));
            return Optional.of(accountMapper.mapTo(account));
        }
        return Optional.empty();
    }

}
