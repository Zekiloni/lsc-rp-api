package com.crp.ucp.character;

import com.crp.ucp.account.AccountEntity;
import com.crp.ucp.server.model.Character;
import com.crp.ucp.vehicle.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "characters")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Column(name = "is_leader")
    private Boolean isLeader;

    @Column(name = "gender")
    private Character.GenderEnum gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    private Integer health;

    private Integer armour;

    private Integer skin;

    private Integer money;

    private Integer paycheck;

    private Integer bank;

    private Integer savings;

    private Integer hours;

    private Integer minutes;

    private Integer level;

    @Column(name = "position_x")
    private BigDecimal positionX;

    @Column(name = "position_y")
    private BigDecimal positionY;

    @Column(name = "position_z")
    private BigDecimal positionZ;

    @Column(name = "angle")
    private BigDecimal angle;

    @Column(name = "virtual_world")
    private Integer virtualWorld;

    private Integer interior;

    // private Integer factionId;

    // private Integer rankId;

    private Integer job;

    private Integer state;

    @Column(name = "drug_addiction")
    private Integer drugAddiction;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VehicleEntity> vehicles = new ArrayList<>();
}
