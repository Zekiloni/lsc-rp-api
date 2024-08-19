package com.crp.ucp.character;

import com.crp.ucp.account.AccountException;
import com.crp.ucp.server.api.CharacterApi;
import com.crp.ucp.server.model.Character;
import com.crp.ucp.server.model.CharacterCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static java.text.MessageFormat.format;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CharacterController implements com.crp.ucp.server.api.CharacterApi {

    private final CharacterService characterService;

    private final CharacterMapper characterMapper;

    @Override
    public ResponseEntity<List<Character>> listUnapprovedCharacter() {
        return ResponseEntity.ok(characterMapper.mapTo(characterService.getAllUnapprovedCharacters()));
    }

    @Override
    public ResponseEntity<Character> createCharacter(CharacterCreate characterCreate) {
        Character character = characterMapper.mapTo(
                characterService.createCharacter(characterMapper.mapTo(characterCreate), characterCreate.getAccountId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(character);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<Character>> listCharacters(String name, String accountUsername) {
        if (Objects.nonNull(name) && Objects.nonNull(accountUsername)) {
            return ResponseEntity.ok(characterMapper.mapTo(
                    characterService.getByNameAndAccountUsername(name, accountUsername)));
        }

        if (Objects.nonNull(name) || Objects.nonNull(accountUsername)) {
            return ResponseEntity.ok(characterMapper.mapTo(
                    characterService.getByNameOrAccountUsername(name, accountUsername)));
        }

        return ResponseEntity.ok(characterMapper.mapTo(characterService.getAllCharacter()));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Character> retrieveCharacter(Integer id) {
        Character character = characterMapper.mapTo(characterService.getCharacterById(id)
                .orElseThrow(() -> new AccountException(format("Character with ID {0} not found", id))));
        return ResponseEntity.ok(character);
    }
}
