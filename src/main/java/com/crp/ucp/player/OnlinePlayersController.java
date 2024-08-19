package com.crp.ucp.player;

import com.crp.ucp.character.CharacterEntity;
import com.crp.ucp.character.CharacterService;
import com.crp.ucp.server.api.OnlinePlayersApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OnlinePlayersController implements OnlinePlayersApi {

    private final CharacterService characterService;

    @Override
    public ResponseEntity<List<String>> getOnlinePlayers() {
        return ResponseEntity.ok(
                characterService.getOnlineCharacters()
                        .stream()
                        .map(CharacterEntity::getName)
                        .toList());
    }
}
