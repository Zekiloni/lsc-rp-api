package net.lscrp.ucp.player;

import lombok.RequiredArgsConstructor;
import net.lscrp.ucp.character.CharacterEntity;
import net.lscrp.ucp.character.CharacterService;
import net.lscrp.ucp.faction.FactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OnlinePlayerService {

    private final CharacterService characterService;
    private final FactionService factionService;

    public List<CharacterEntity> getOnlinePlayers() {
        return characterService.getOnlineCharacters()
                .stream()
                .peek(this::resolveCharacterFaction)
                .toList();
    }

    private void resolveCharacterFaction(CharacterEntity character) {
        if (character.getFactionId() != 0) {
            factionService.getFactionById(character.getFactionId())
                    .ifPresent(character::setFaction);
        }
    }
}
