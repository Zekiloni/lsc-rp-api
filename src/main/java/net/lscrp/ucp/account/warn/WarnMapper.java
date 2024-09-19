package net.lscrp.ucp.account.warn;

import net.lscrp.ucp.server.model.Warn;
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
