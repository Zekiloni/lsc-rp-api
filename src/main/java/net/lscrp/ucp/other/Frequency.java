package net.lscrp.ucp.other;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.lscrp.ucp.character.CharacterEntity;

@Getter
@Setter
@Entity
@Table(name = "frequencies")
public class Frequency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private CharacterEntity author;

    //TODO [JPA Buddy] generate columns from DB
}