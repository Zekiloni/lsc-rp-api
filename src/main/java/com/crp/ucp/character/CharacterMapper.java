package com.crp.ucp.character;

import com.crp.ucp.server.model.Character;
import com.crp.ucp.server.model.CharacterCreate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CharacterMapper {

    static CharacterMapper getInstance() {
        return Mappers.getMapper(CharacterMapper.class);
    }

    Character mapTo(CharacterEntity character);

    CharacterEntity mapTo(CharacterCreate characterCreate);

    CharacterEntity mapTo(Character character);

    List<Character> mapTo(List<CharacterEntity> characters);

}
