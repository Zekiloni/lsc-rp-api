package net.lscrp.ucp.property;

import net.lscrp.ucp.character.CharacterEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "properties")
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private PropertyType type;

    @Size(max = 128)
    @NotNull
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "owner_id")
    private CharacterEntity owner;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @Size(max = 64)
    @Column(name = "address", length = 64)
    private String address;

    @NotNull
    @Column(name = "position_x", nullable = false)
    private Float positionX;

    @NotNull
    @Column(name = "position_y", nullable = false)
    private Float positionY;

    @NotNull
    @Column(name = "position_z", nullable = false)
    private Float positionZ;

    @NotNull
    @Column(name = "exterior_virtual_world", nullable = false)
    private Integer exteriorVirtualWorld;

    @NotNull
    @Column(name = "exterior", nullable = false)
    private Integer exterior;

    @NotNull
    @Column(name = "interior_position_x", nullable = false)
    private Float interiorPositionX;

    @NotNull
    @Column(name = "interior_position_y", nullable = false)
    private Float interiorPositionY;

    @NotNull
    @Column(name = "interior_position_z", nullable = false)
    private Float interiorPositionZ;

    @NotNull
    @Column(name = "interior", nullable = false)
    private Integer interior;

    @NotNull
    @Column(name = "is_locked", nullable = false)
    private Boolean isLocked = false;

    @Column(name = "biz_type")
    @Convert(converter = BizTypeConverter.class)
    private BizType bizType;

    @Getter
    @RequiredArgsConstructor
    public enum PropertyType {
        HOUSE("HOUSE"),
        BUSINESS("BUSINESS"),
        APARTMENT("APARTMENT"),
        WAREHOUSE("WAREHOUSE"),
        GARAGE("GARAGE");

        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum BizType {
        GROCERY_STORE,
        GAS_STATION,
        CLOTHING_STORE,
        OTHER,
        BAR,
        RESTAURANT
    }
}