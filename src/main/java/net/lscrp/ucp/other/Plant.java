package net.lscrp.ucp.other;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.lscrp.ucp.character.CharacterEntity;

@Getter
@Setter
@Entity
@Table(name = "plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private CharacterEntity owner;

    //TODO [JPA Buddy] generate columns from DB
}