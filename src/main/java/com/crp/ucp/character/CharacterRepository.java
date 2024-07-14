package com.crp.ucp.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Integer> {

    Optional<CharacterEntity> findByName(String name);

    List<CharacterEntity> findByFactionId(Integer factionId);

    List<CharacterEntity> findByNameContaining(String name);
}
