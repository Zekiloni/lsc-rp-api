package net.lscrp.ucp.account;

import net.lscrp.ucp.account.log.LoginLogMapper;
import net.lscrp.ucp.account.log.LoginLogService;
import net.lscrp.ucp.server.model.Account;
import net.lscrp.ucp.server.model.AccountCreate;
import net.lscrp.ucp.server.model.LoginLogAudit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static net.lscrp.ucp.account.AccountUtil.throwAccountNotFoundException;

@RestController
@RequiredArgsConstructor
public class AccountController implements net.lscrp.ucp.server.api.AccountApi {

    public static final String X_TOTAL_COUNT = "X-Total-Count";
    public static final String X_RESULT_COUNT = "X-Result-Count";

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
    public ResponseEntity<List<Account>> listAccount(Integer offset, Integer limit, String username, String emailAddress) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        Page<AccountEntity> page;

        if (Objects.nonNull(username) && Objects.nonNull(emailAddress)) {
            page = accountService.getByUsernameAndEmail(username, emailAddress, pageRequest);
        } else if (Objects.nonNull(username) || Objects.nonNull(emailAddress)) {
            page = accountService.getByUsernameOrEmail(username, emailAddress, pageRequest);
        } else {
            page = accountService.getAllAccounts(pageRequest);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(X_TOTAL_COUNT, String.valueOf(page.getTotalElements()));
        responseHeaders.add(X_RESULT_COUNT, String.valueOf(page.getNumberOfElements()));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(accountMapper.mapTo(page.getContent()));
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
