package com.crp.ucp.character;

import com.crp.ucp.account.AccountEntity;
import com.crp.ucp.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    private final AccountService accountService;

    public List<CharacterEntity> getAllCharacter() {
        return this.characterRepository.findAll();
    }

    public Optional<CharacterEntity> getCharacterById(Long id) {
        return this.characterRepository.findById(id);
    }


    public Optional<CharacterEntity> getCharacterByName(String username) {
        return this.characterRepository.findByName(username);
    }

    public CharacterEntity createCharacter(CharacterEntity character, Long accountId) {
        AccountEntity account = accountService.getAccountById(accountId)
                .orElseThrow(() -> new NoSuchElementException(format("Account with ID {} does not exist", accountId)));

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
        character.setHours(0);
        character.setMinutes(0);
        character.setLevel(1);
        character.setInterior(0);
        character.setVirtualWorld(0);
        character.setPositionX(BigDecimal.valueOf(0.0));
        character.setPositionY(BigDecimal.valueOf(0.0));
        character.setPositionZ(BigDecimal.valueOf(0.0));
        character.setIsLeader(false);
        character.setJob(0);
        character.setMoney(5000);
        character.setSavings(0);
        character.setPaycheck(0);
        character.setState(0);

        characterRepository.save(character);

        return character;
    }
}
