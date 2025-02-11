package net.lscrp.ucp.faction;

import net.lscrp.ucp.character.CharacterEntity;
import net.lscrp.ucp.character.CharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static net.lscrp.ucp.character.CharacterUtil.getCharacterDailyAverageActivity;
import static net.lscrp.ucp.character.CharacterUtil.throwCharacterNotFoundException;
import static net.lscrp.ucp.faction.FactionUtil.NO_FACTION_ID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FactionService {

    private final FactionRepository factionRepository;
    private final CharacterService characterService;

    public Optional<FactionEntity> getFactionById(Integer factionId) {
        return factionRepository.findById(factionId);
    }

    public List<FactionMemberProjection> getFactionMembers(FactionEntity faction) {
        return getFactionMembers(faction.getId());
    }

    public FactionMemberProjection kickFactionMember(Integer characterId) {
        CharacterEntity character = characterService.getCharacterById(characterId)
                .orElseThrow(throwCharacterNotFoundException(characterId));

        character.setFactionId(NO_FACTION_ID);
        character.setRankName(null);
        character.setIsLeader(0);
        return mapTo(characterService.patchCharacter(character));
    }

    public FactionMemberProjection updateFactionMemberRank(Integer characterId, String rankName) {
        CharacterEntity character = characterService.getCharacterById(characterId)
                .orElseThrow(throwCharacterNotFoundException(characterId));

        character.setRankName(rankName);

        return mapTo(characterService.patchCharacter(character));
    }

    public List<FactionMemberProjection> getFactionMembers(Integer factionId) {
        return characterService.getCharactersByFactionId(factionId).stream().map(this::mapTo).toList();
    }

    private FactionMemberProjection mapTo(CharacterEntity member) {
        return FactionMemberProjection.builder()
                .accountUsername(member.getAccount().getUsername())
                .characterId(member.getId())
                .characterName(member.getName())
                .characterSkin(member.getSkin())
                .rankName(member.getRankName())
                .isLeader(member.getIsLeader() == 1)
                .isInGame(member.getIsInGame() == 1)
                .averageActivity(getCharacterDailyAverageActivity(member))
                .build();
    }
}
