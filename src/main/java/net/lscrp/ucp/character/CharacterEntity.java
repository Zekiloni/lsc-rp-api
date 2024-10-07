package net.lscrp.ucp.character;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.lscrp.ucp.account.AccountEntity;
import net.lscrp.ucp.common.BooleanIntegerConverter;
import net.lscrp.ucp.faction.FactionEntity;
import net.lscrp.ucp.other.*;
import net.lscrp.ucp.property.PropertyEntity;
import net.lscrp.ucp.server.model.CharacterGender;
import net.lscrp.ucp.vehicle.VehicleEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "characters")
public class CharacterEntity {
    public static final int DEFAULT_PLAYER_FIGHT_STYLE = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    private AccountEntity account;

    @Builder.Default
    @Convert(converter = BooleanIntegerConverter.class)
    @Column(name = "is_leader")
    private Boolean isLeader = false;

    @Builder.Default
    @Convert(converter = BooleanIntegerConverter.class)
    @Column(name = "is_in_game")
    private Boolean isInGame = false;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private CharacterGender gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Builder.Default
    @Column(name = "position_x")
    private BigDecimal positionX = BigDecimal.valueOf(0.0);

    @Builder.Default
    @Column(name = "position_y")
    private BigDecimal positionY = BigDecimal.valueOf(0.0);

    @Builder.Default
    @Column(name = "position_z")
    private BigDecimal positionZ = BigDecimal.valueOf(0.0);

    @Builder.Default
    @Column(name = "angle")
    private BigDecimal angle = BigDecimal.valueOf(0.0);

    @Builder.Default
    @Column(name = "virtual_world")
    private Integer virtualWorld = 0;

    @Builder.Default
    @Column(name = "interior")
    private Integer interior = 0;

    @Column(name = "faction_id")
    private Integer factionId;

    @Transient
    private FactionEntity faction;

    @Column(name = "rank_name")
    private String rankName;

    @Builder.Default
    @Column(name = "job")
    private Integer job = -1;

    @Builder.Default
    @Column(name = "state")
    private Integer state = 0;

    @Column(name = "mask_id", unique = true)
    private String maskId;

    @Builder.Default
    @Column(name = "drug_addiction")
    private Integer drugAddiction = 0;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Builder.Default
    @Column(name = "respawn_time", nullable = false)
    private Integer respawnTime = -1;

    @Builder.Default
    @Column(name = "walking_style", nullable = false)
    private Integer walkingStyle = 0;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VehicleEntity> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "owner", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PropertyEntity> properties = new ArrayList<>();

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "approved_at")
    private OffsetDateTime approvedAt;

    @Column(name = "approved_by")
    private String approvedBy;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Advertisement> advertisements = new LinkedHashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CriminalRecord> criminalRecords = new LinkedHashSet<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Frequency> frequencies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Plant> plants = new LinkedHashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets = new LinkedHashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Warrant> warrants = new LinkedHashSet<>();

    @Builder.Default
    @Size(max = 128)
    @Column(name = "appearance", length = 128)
    private String appearance = null;

    @Builder.Default
    @Column(name = "hunger", nullable = false)
    private Integer hunger = 0;

    @Builder.Default
    @Column(name = "thirsty", nullable = false)
    private Integer thirsty = 0;

    @Builder.Default
    @Column(name = "drunk_level", nullable = false)
    private Integer drunkLevel = 0;

    @NotNull
    @Builder.Default
    @Column(name = "default_spawn", nullable = false)
    private Integer defaultSpawn = 0;

    @NotNull
    @Builder.Default
    @Column(name = "rented_room", nullable = false)
    private Integer rentedRoom = 0;

    @NotNull
    @Builder.Default
    @Column(name = "cuffed", nullable = false)
    private Boolean cuffed = false;

    @NotNull
    @Builder.Default
    @Column(name = "fight_style", nullable = false)
    private Integer fightStyle = DEFAULT_PLAYER_FIGHT_STYLE;

    @NotNull
    @Builder.Default
    @Column(name = "ajail_timeleft", nullable = false)
    private Integer adminJail = 0;

    @NotNull
    @Builder.Default
    @Column(name = "prison_cell_id", nullable = false)
    private Integer prisonCellId = -1;

    @NotNull
    @Builder.Default
    @Column(name = "prison_time", nullable = false)
    private Integer prisonTime = 0;

    @NotNull
    @Builder.Default
    @Column(name = "inside_property", nullable = false)
    private Integer insideProperty = 0;

    @Column(name = "last_seen_at")
    private OffsetDateTime lastSeenAt;

    @NotNull
    @Builder.Default
    @Column(name = "health", nullable = false)
    private Integer health = 100;

    @NotNull
    @Builder.Default
    @Column(name = "armour", nullable = false)
    private Integer armour = 0;

    @NotNull
    @Builder.Default
    @Column(name = "skin", nullable = false)
    private Integer skin = 0;

    @NotNull
    @Builder.Default
    @Column(name = "money", nullable = false)
    private Integer money = 0;

    @NotNull
    @Builder.Default
    @Column(name = "paycheck", nullable = false)
    private Integer paycheck = 0;

    @NotNull
    @Builder.Default
    @Column(name = "bank", nullable = false)
    private Integer bank = 0;

    @NotNull
    @Builder.Default
    @Column(name = "savings", nullable = false)
    private Integer savings = 0;

    @NotNull
    @Builder.Default
    @Column(name = "hours", nullable = false)
    private Integer hours = 0;

    @NotNull
    @Builder.Default
    @Column(name = "minutes", nullable = false)
    private Integer minutes = 0;

    @NotNull
    @Builder.Default
    @Column(name = "level", nullable = false)
    private Integer level = 1;
}
