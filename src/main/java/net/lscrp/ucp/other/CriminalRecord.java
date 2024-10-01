package net.lscrp.ucp.other;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.lscrp.ucp.character.CharacterEntity;

@Getter
@Setter
@Entity
@Table(name = "criminal_records")
public class CriminalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private CharacterEntity character;

    //TODO [JPA Buddy] generate columns from DB
}