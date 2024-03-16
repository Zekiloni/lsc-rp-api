package com.crp.ucp.vehicle;

import com.crp.ucp.character.CharacterEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "model", nullable = false)
    private Integer model;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotNull
    @Column(name = "health", nullable = false)
    private Integer health;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "owner_id")
    private CharacterEntity owner;

    @Column(name = "numberplate")
    private String numberplate;

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
    @Column(name = "rotation", nullable = false)
    private Float rotation;

    @NotNull
    @Column(name = "park_position_x", nullable = false)
    private Float parkPositionX;

    @NotNull
    @Column(name = "park_position_y", nullable = false)
    private Float parkPositionY;

    @NotNull
    @Column(name = "park_position_z", nullable = false)
    private Float parkPositionZ;

    @NotNull
    @Column(name = "park_rotation", nullable = false)
    private Float parkRotation;

    @NotNull
    @Column(name = "primary_color", nullable = false)
    private Integer primaryColor;

    @NotNull
    @Column(name = "secondary_color", nullable = false)
    private Integer secondaryColor;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}