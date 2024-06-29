package com.crp.ucp.account.warn;

import com.crp.ucp.server.model.Warn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface WarnMapper {
    @Mappings({
            @Mapping(source = "account.username", target = "account"),
            @Mapping(source = "adminAccount.username", target = "adminAccount")
    })
    Warn mapTo(WarnEntity warn);

    List<Warn> mapTo(List<WarnEntity> bans);
}
