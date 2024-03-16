package com.crp.ucp.item;

import com.crp.ucp.character.CharacterEntity;
import com.crp.ucp.vehicle.VehicleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 64)
    @NotNull
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private CharacterEntity owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trunk_id")
    private VehicleEntity trunk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_touched")
    private CharacterEntity lastTouched;

    @NotNull
    @Column(name = "ammo", nullable = false)
    private Integer ammo;

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
    @Column(name = "rotation_x", nullable = false)
    private Float rotationX;

    @NotNull
    @Column(name = "rotation_y", nullable = false)
    private Float rotationY;

    @NotNull
    @Column(name = "rotation_z", nullable = false)
    private Float rotationZ;

    @NotNull
    @Column(name = "virtual_world", nullable = false)
    private Integer virtualWorld;

    @NotNull
    @Column(name = "interior", nullable = false)
    private Integer interior;

}