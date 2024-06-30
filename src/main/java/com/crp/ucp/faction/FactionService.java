package com.crp.ucp.faction;

import com.crp.ucp.character.CharacterEntity;
import com.crp.ucp.character.CharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.crp.ucp.character.CharacterUtil.getCharacterDailyAverageActivity;

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

    public List<FactionMemberProjection> getFactionMembers(Integer factionId) {
        return characterService.getCharactersByFactionId(factionId)
                .stream()
                .map(this::mapTo)
                .toList();
    }

    private FactionMemberProjection mapTo(CharacterEntity member) {
        log.info("{} is leader {}", member.getName(), member.getIsLeader());
        return FactionMemberProjection
                .builder()
                .accountUsername(member.getAccount().getUsername())
                .characterId(member.getId())
                .characterName(member.getName())
                .characterSkin(member.getSkin())
                .rankName(member.getRankName())
                .isLeader(member.getIsLeader() == 1)
                .averageActivity(getCharacterDailyAverageActivity(member))
                .build();
    }
}
