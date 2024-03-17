package com.crp.ucp.property;

import com.crp.ucp.character.CharacterEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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

    public enum PropertyType {
        HOUSE("HOUSE"),
        BUSINESS("BUSINESS"),
        APARTMENT("APARTMENT"),
        WAREHOUSE("WAREHOUSE");

        PropertyType(String value) {
        }
    }

}