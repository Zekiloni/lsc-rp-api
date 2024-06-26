package com.crp.ucp.account;

import com.crp.ucp.server.model.Account;
import com.crp.ucp.server.model.AccountCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.text.MessageFormat.format;

@RestController
@RequiredArgsConstructor
public class AccountApi implements com.crp.ucp.server.api.AccountApi {

    private final AccountService accountService;

    private final AccountMapper accountMapper;

    @Override
    public ResponseEntity<Account> createAccount(AccountCreate accountCreate) {
        Account account = accountMapper.mapTo(accountService.createAccount(accountMapper.mapTo(accountCreate)));
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @Override
    public ResponseEntity<List<Account>> listAccount() {
        return ResponseEntity.ok(accountMapper.mapTo(accountService.getAllAccounts()));
    }

    @Override
    public ResponseEntity<Account> retrieveAccount(Long accountId) {
        Account account = accountMapper.mapTo(accountService.getAccountById(accountId)
                .orElseThrow(() -> new AccountException(format("Account with ID {0} not found", accountId))));
        return ResponseEntity.ok(account);
    }


}
