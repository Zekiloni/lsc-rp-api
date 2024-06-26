package com.crp.ucp.account.ban;

import com.crp.ucp.account.AccountMapper;
import com.crp.ucp.server.model.Ban;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {AccountMapper.class}, componentModel = "spring")
public interface BanMapper {

    Ban mapTo(BanEntity ban);

    List<Ban> mapTo(List<BanEntity> bans);
}
