package com.crp.ucp.character;

import com.crp.ucp.server.model.Character;
import com.crp.ucp.server.model.CharacterCreate;
import com.crp.ucp.server.model.CharacterGender;
import com.crp.ucp.vehicle.VehicleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

import java.util.List;

@Mapper(uses = {VehicleMapper.class}, componentModel = "spring")
public interface CharacterMapper {

    Character mapTo(CharacterEntity character);

    CharacterEntity mapTo(CharacterCreate characterCreate);

    CharacterEntity mapTo(Character character);

    List<Character> mapTo(List<CharacterEntity> characters);
}
