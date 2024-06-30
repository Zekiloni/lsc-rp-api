package com.crp.ucp.character;

import com.crp.ucp.account.AccountEntity;
import com.crp.ucp.property.PropertyEntity;
import com.crp.ucp.server.model.CharacterGender;
import com.crp.ucp.vehicle.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Column(name = "is_leader")
    private Integer isLeader;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private CharacterGender gender;

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

    @Column(name = "interior")
    private Integer interior;

    @Column(name = "faction_id")
    private Integer factionId;

    @Column(name = "rank_name")
    private String rankName;

    private Integer job;

    private Integer state;

    @Column(name = "mask_id", unique = true)
    private String maskId;

    @Column(name = "drug_addiction")
    private Integer drugAddiction;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "respawn_time", nullable = false)
    private Integer respawnTime;

    @Column(name = "walking_style", nullable = false)
    private Integer walkingStyle;

    @Column(name = "fighting_style", nullable = false)
    private Integer fightingStyle;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VehicleEntity> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PropertyEntity> properties = new ArrayList<>();
}
