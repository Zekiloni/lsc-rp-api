package com.crp.ucp.account;

import com.crp.ucp.account.log.LoginLogMapper;
import com.crp.ucp.account.log.LoginLogService;
import com.crp.ucp.server.api.AccountApi;
import com.crp.ucp.server.model.Account;
import com.crp.ucp.server.model.AccountCreate;
import com.crp.ucp.server.model.LoginLogAudit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static com.crp.ucp.account.AccountUtil.throwAccountNotFoundException;

@RestController
@RequiredArgsConstructor
public class AccountController implements com.crp.ucp.server.api.AccountApi {

    private final AccountMapper accountMapper;
    private final AccountService accountService;
    private final LoginLogMapper loginLogMapper;
    private final LoginLogService loginLogService;

    @Override
    public ResponseEntity<Account> createAccount(AccountCreate accountCreate) {
        Account account = accountMapper.mapTo(accountService.createAccount(accountMapper.mapTo(accountCreate)));
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<Account>> listAccount(String username, String emailAddress) {
        if (Objects.nonNull(username) && Objects.nonNull(emailAddress)) {
            return ResponseEntity.ok(accountMapper.mapTo(accountService.getByUsernameAndEmail(username, emailAddress)));
        }

        SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(username) || Objects.nonNull(emailAddress)) {
            return ResponseEntity.ok(accountMapper.mapTo(accountService.getByUsernameOrEmail(username, emailAddress)));
        }

        return ResponseEntity.ok(accountMapper.mapTo(accountService.getAllAccounts()));
    }

    @Override
    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<LoginLogAudit>> listAccountLoginLogs(Integer accountId) {
        return ResponseEntity.ok(loginLogMapper.mapTo(loginLogService.getByAccountId(accountId)));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<Account> retrieveAccount(Integer accountId) {
        Account account = accountMapper.mapTo(accountService.getAccountById(accountId)
                .orElseThrow(() -> throwAccountNotFoundException(accountId)));
        return ResponseEntity.ok(account);
    }

}
