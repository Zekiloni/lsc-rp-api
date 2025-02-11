package net.lscrp.ucp.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Integer> {

    Optional<CharacterEntity> findByName(String name);

    List<CharacterEntity> findByFactionId(Integer factionId);

    List<CharacterEntity> findByNameContainingOrAccountUsernameContaining(String name, String accountUsername);

    List<CharacterEntity> findByNameContainingAndAccountUsernameContaining(String name, String accountUsername);

    @Query("SELECT ch FROM CharacterEntity ch WHERE ch.isInGame = 1")
    List<CharacterEntity> findAllOnlineCharacters();

    @Query("SELECT ch FROM CharacterEntity ch WHERE ch.isApproved IS NULL")
    List<CharacterEntity> findAllUnapprovedCharacters();

    @Query("SELECT ch FROM CharacterEntity ch WHERE ch.isApproved = false")
    List<CharacterEntity> findAllRejectedCharacters();
}
