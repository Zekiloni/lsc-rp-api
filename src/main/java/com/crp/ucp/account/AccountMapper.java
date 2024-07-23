package com.crp.ucp.account;

import com.crp.ucp.character.CharacterMapper;
import com.crp.ucp.server.model.Account;
import com.crp.ucp.server.model.AccountCreate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {CharacterMapper.class})
public interface AccountMapper {

    AccountEntity mapTo(AccountCreate accountCreate);

    Account mapTo(AccountEntity account);

    List<Account> mapTo(List<AccountEntity> accounts);
}
