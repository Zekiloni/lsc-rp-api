package net.lscrp.ucp.account;

import net.lscrp.ucp.account.log.LoginLogMapper;
import net.lscrp.ucp.account.log.LoginLogService;
import net.lscrp.ucp.server.api.AccountApi;
import net.lscrp.ucp.server.model.Account;
import net.lscrp.ucp.server.model.AccountCreate;
import net.lscrp.ucp.server.model.LoginLogAudit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static net.lscrp.ucp.account.AccountUtil.throwAccountNotFoundException;

@RestController
@RequiredArgsConstructor
public class AccountController implements net.lscrp.ucp.server.api.AccountApi {

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

        if (Objects.nonNull(username) || Objects.nonNull(emailAddress)) {
            return ResponseEntity.ok(accountMapper.mapTo(accountService.getByUsernameOrEmail(username, emailAddress)));
        }

        return ResponseEntity.ok(accountMapper.mapTo(accountService.getAllAccounts()));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<LoginLogAudit>> listAccountLoginLogs(Integer accountId) {
        return ResponseEntity.ok(loginLogMapper.mapTo(loginLogService.getByAccountId(accountId)));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<Account> retrieveAccount(Integer accountId) {
        Account account = accountMapper.mapTo(accountService.getAccountById(accountId)
                .orElseThrow(() -> throwAccountNotFoundException(accountId)));
        return ResponseEntity.ok(account);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Void> deleteAccount(Integer accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}
