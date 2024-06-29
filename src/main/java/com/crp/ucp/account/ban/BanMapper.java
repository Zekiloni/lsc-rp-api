package com.crp.ucp.account.ban;

import com.crp.ucp.server.model.Ban;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface BanMapper {

    @Mappings({
            @Mapping(source = "account.username", target = "account"),
            @Mapping(source = "adminAccount.username", target = "adminAccount"),
            @Mapping(expression = "java(ban.getIsExpired())", target = "isExpired")
    })
    Ban mapTo(BanEntity ban);

    List<Ban> mapTo(List<BanEntity> bans);
}
