package net.lscrp.ucp.account;

import net.lscrp.ucp.character.CharacterMapper;
import net.lscrp.ucp.server.model.Account;
import net.lscrp.ucp.server.model.AccountCreate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {CharacterMapper.class})
public interface AccountMapper {

    AccountEntity mapTo(AccountCreate accountCreate);

    Account mapTo(AccountEntity account);

    List<Account> mapTo(List<AccountEntity> accounts);
}
