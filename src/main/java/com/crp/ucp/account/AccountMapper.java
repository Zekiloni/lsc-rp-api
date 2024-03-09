package com.crp.ucp.account;

import com.crp.ucp.server.model.Account;
import com.crp.ucp.server.model.AccountCreate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {

    static AccountMapper getInstance() {
        return Mappers.getMapper(AccountMapper.class);
    }

    AccountEntity mapTo(AccountCreate accountCreate);

    Account mapTo(AccountEntity account);

    List<Account> mapTo(List<AccountEntity> accounts);
}
