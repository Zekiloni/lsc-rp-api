package com.crp.ucp.account.kick;

import com.crp.ucp.server.model.Kick;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface KickMapper {
    @Mappings({
            @Mapping(source = "account.username", target = "account"),
            @Mapping(source = "adminAccount.username", target = "adminAccount")
    })
    Kick mapTo(KickEntity ban);

    List<Kick> mapTo(List<KickEntity> bans);
}
