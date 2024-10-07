package net.lscrp.ucp.player;

import net.lscrp.ucp.character.CharacterEntity;
import net.lscrp.ucp.character.CharacterMapper;
import net.lscrp.ucp.server.model.OnlinePlayer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(uses = {CharacterMapper.class})
public interface OnlinePlayerMapper {

    @Mappings({
            @Mapping(source = "account.username", target = "accountUsername"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "skin", target = "skin"),
            @Mapping(source = "account.admin", target = "administrator"),
            @Mapping(source = "faction.name", target = "faction"),
    })
    OnlinePlayer mapTo(CharacterEntity character);

    List<OnlinePlayer> mapTo(List<CharacterEntity> character);
}
