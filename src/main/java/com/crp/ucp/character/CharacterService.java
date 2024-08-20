package com.crp.ucp.character;

import com.crp.ucp.account.AccountEntity;
import com.crp.ucp.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.crp.ucp.account.AccountUtil.throwAccountNotFoundException;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final AccountService accountService;

    public List<CharacterEntity> getAllCharacter() {
        return characterRepository.findAll();
    }

    public Optional<CharacterEntity> getCharacterById(Integer id) {
        return characterRepository.findById(id);
    }

    public Optional<CharacterEntity> getCharacterByName(String username) {
        return characterRepository.findByName(username);
    }

    public List<CharacterEntity> getByNameOrAccountUsername(String username, String accountUsername) {
        return characterRepository.findByNameContainingOrAccountUsernameContaining(username, accountUsername);
    }

    public List<CharacterEntity> getByNameAndAccountUsername(String username, String accountUsername) {
        return characterRepository.findByNameContainingAndAccountUsernameContaining(username, accountUsername);
    }

    public List<CharacterEntity> getCharactersByFactionId(Integer factionId) {
        return characterRepository.findByFactionId(factionId);
    }

    public CharacterEntity createCharacter(CharacterEntity character, Integer accountId) {
        AccountEntity account = accountService.getAccountById(accountId)
                .orElseThrow(() -> throwAccountNotFoundException(accountId));

        character.setAccount(account);
        character.setMaskId(UUID.randomUUID().toString().substring(0, 6));
        character.setRespawnTime(0);
        character.setWalkingStyle(0);
        character.setFightingStyle(0);
        character.setAngle(BigDecimal.valueOf(0.00));
        character.setArmour(0);
        character.setBank(0);
        character.setDrugAddiction(0);
        character.setHealth(100);
        character.setIsInGame(0);
        character.setHours(0);
        character.setMinutes(0);
        character.setLevel(1);
        character.setInterior(0);
        character.setVirtualWorld(0);
        character.setPositionX(BigDecimal.valueOf(0.0));
        character.setPositionY(BigDecimal.valueOf(0.0));
        character.setPositionZ(BigDecimal.valueOf(0.0));
        character.setIsLeader(0);
        character.setJob(0);
        character.setMoney(5000);
        character.setSavings(0);
        character.setPaycheck(0);
        character.setState(0);
        character.setCreatedAt(OffsetDateTime.now());

        return characterRepository.save(character);
    }

    public CharacterEntity updateCharacter(CharacterEntity character) {
        return characterRepository.save(character);
    }

    public List<CharacterEntity> getOnlineCharacters() {
        return characterRepository.findAllOnlineCharacters();
    }

    public List<CharacterEntity> getAllUnapprovedCharacters() {
        return characterRepository.findAllUnapprovedCharacters();
    }
}
