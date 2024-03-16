package com.crp.ucp.character;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    public List<CharacterEntity> getAllCharacter() {
        return this.characterRepository.findAll();
    }

    public Optional<CharacterEntity> getCharacterById(Long id) {
        return this.characterRepository.findById(id);
    }


    public Optional<CharacterEntity> getCharacterByName(String username) {
        return this.characterRepository.findByName(username);
    }

    public CharacterEntity createCharacter(CharacterEntity character) {
        return null;
    }
}
