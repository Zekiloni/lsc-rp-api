package net.lscrp.ucp.character;

import net.lscrp.ucp.property.PropertyMapper;
import net.lscrp.ucp.server.model.Character;
import net.lscrp.ucp.server.model.CharacterCreate;
import net.lscrp.ucp.vehicle.VehicleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(uses = {VehicleMapper.class, PropertyMapper.class})
public interface CharacterMapper {

    @Mappings({
            @Mapping(source = "account.username", target = "accountUsername"),
            @Mapping(source = "account.id", target = "accountId"),
    })
    Character mapTo(CharacterEntity character);

    CharacterEntity mapTo(CharacterCreate characterCreate);

    CharacterEntity mapTo(Character character);

    List<Character> mapTo(List<CharacterEntity> characters);
}
