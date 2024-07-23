package com.crp.ucp.character;

import com.crp.ucp.property.PropertyMapper;
import com.crp.ucp.server.model.Character;
import com.crp.ucp.server.model.CharacterCreate;
import com.crp.ucp.vehicle.VehicleMapper;
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

    default Boolean mapTo(Integer i) {
        return i == 1;
    }

    default Integer mapTo(Boolean value) {
        return value ? 1 : 0;
    }
}
