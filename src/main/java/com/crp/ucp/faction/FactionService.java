package com.crp.ucp.faction;

import com.crp.ucp.character.CharacterEntity;
import com.crp.ucp.character.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactionService {

    private final CharacterService characterService;

    public List<FactionMemberProjection> getFactionMembers(FactionEntity faction) {
        return getFactionMembers(faction.getId());
    }

    public List<FactionMemberProjection> getFactionMembers(Integer factionId) {
        return characterService.getCharactersByFactionId(factionId)
                .stream()
                .map(this::mapTo)
                .toList();
    }

    private FactionMemberProjection mapTo(CharacterEntity member) {
        return FactionMemberProjection
                .builder()
                .characterId(member.getId())
                .characterName(member.getName())
                .rankName(member.getRankName())
                .build();
    }

}
