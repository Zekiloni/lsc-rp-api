package net.lscrp.ucp.account.authentication;

import net.lscrp.ucp.account.AccountEntity;
import net.lscrp.ucp.account.AccountMapper;
import net.lscrp.ucp.account.AccountService;
import net.lscrp.ucp.account.log.LoginLogService;
import net.lscrp.ucp.config.JwtAuthenticationService;
import net.lscrp.ucp.server.model.Account;
import net.lscrp.ucp.server.model.Authentication;
import net.lscrp.ucp.server.model.AuthenticationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static net.lscrp.ucp.account.AccountUtil.throwAccountNotFoundException;

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
