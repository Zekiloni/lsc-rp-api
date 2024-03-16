package com.crp.ucp.character;

import com.crp.ucp.server.api.CharactersApi;
import com.crp.ucp.server.model.Character;
import com.crp.ucp.server.model.CharacterCreate;
import com.crp.ucp.server.model.CharacterUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CharacterApi implements CharactersApi {

    private final CharacterService characterService;

    private final CharacterMapper characterMapper = CharacterMapper.getInstance();

    @Override
    public ResponseEntity<Character> createCharacter(CharacterCreate characterCreate) {
        Character character = characterMapper.mapTo(
                characterService.createCharacter(characterMapper.mapTo(characterCreate)));

        return ResponseEntity.status(HttpStatus.CREATED).body(character);
    }

    @Override
    public ResponseEntity<Void> deleteCharacter(Long id) {
        return CharactersApi.super.deleteCharacter(id);
    }

    @Override
    public ResponseEntity<List<Character>> listCharacters() {
        return CharactersApi.super.listCharacters();
    }

    @Override
    public ResponseEntity<Character> patchCharacter(Long id, CharacterUpdate characterUpdate) {
        return CharactersApi.super.patchCharacter(id, characterUpdate);
    }

    @Override
    public ResponseEntity<Character> retrieveCharacter(Long id) {
        return CharactersApi.super.retrieveCharacter(id);
    }
}
