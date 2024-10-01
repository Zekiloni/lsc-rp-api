package net.lscrp.ucp.character;

import net.lscrp.ucp.account.AccountEntity;
import net.lscrp.ucp.account.AccountException;
import net.lscrp.ucp.account.AccountService;
import net.lscrp.ucp.account.RoleType;
import net.lscrp.ucp.server.model.CharacterUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

import static net.lscrp.ucp.account.AccountUtil.throwAccountNotFoundException;
import static net.lscrp.ucp.character.CharacterUtil.throwCharacterNotFoundException;
import static net.lscrp.ucp.config.SecurityUtil.createSimpleGrantedAuthority;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private static final Integer DEFAULT_BANK_MONEY = 5000;
    public static final int DEFAULT_PLAYER_MONEY = 5000;
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

    public void deleteCharacterById(Integer characterId) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AccountEntity account) {
            boolean isSuperAdmin = account.getAuthorities().contains(createSimpleGrantedAuthority(RoleType.SUPER_ADMIN));

            CharacterEntity character = getCharacterById(characterId)
                    .orElseThrow(throwCharacterNotFoundException(characterId));

            if (!isSuperAdmin && !Objects.equals(character.getAccount().getId(), account.getId())) {
                throw new AccountException("Niste autorizovani.");
            }

            characterRepository.delete(character);
        }
    }

    public void deleteCharacter(CharacterEntity character) {
        characterRepository.delete(character);
    }

    public CharacterEntity createCharacter(CharacterEntity character, Integer accountId) {
        AccountEntity account = accountService.getAccountById(accountId)
                .orElseThrow(() -> throwAccountNotFoundException(accountId));

        character.setAccount(account);
        character.setMaskId(UUID.randomUUID().toString().substring(0, 6));
        character.setRespawnTime(0);
        character.setBank(DEFAULT_BANK_MONEY);
        character.setMoney(DEFAULT_PLAYER_MONEY);
        character.setCreatedAt(OffsetDateTime.now());

        return characterRepository.save(character);
    }

    public CharacterEntity patchCharacter(CharacterEntity character) {
        return characterRepository.save(character);
    }

    public CharacterEntity patchCharacter(Integer characterId, CharacterUpdate characterUpdate) {
        CharacterEntity character = getCharacterById(characterId)
                .orElseThrow(throwCharacterNotFoundException(characterId));

        if (Objects.nonNull(characterUpdate.getIsApproved())) {
            character.setIsApproved(characterUpdate.getIsApproved());
            character.setApprovedAt(OffsetDateTime.now());
            AccountEntity administrator = (AccountEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            character.setApprovedBy(administrator.getUsername());
        }

        if (Objects.nonNull(characterUpdate.getSkin())) {
            character.setSkin(characterUpdate.getSkin());
        }

        return characterRepository.save(character);
    }

    public List<CharacterEntity> getOnlineCharacters() {
        return characterRepository.findAllOnlineCharacters();
    }

    public List<CharacterEntity> getAllUnapprovedCharacters() {
        return characterRepository.findAllUnapprovedCharacters();
    }

    public List<CharacterEntity> getAllRejectedCharacters() {
        return characterRepository.findAllRejectedCharacters();
    }
}
