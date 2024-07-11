package com.crp.ucp.map;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gps")
public class GPSLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "category", nullable = false)
    private Integer category;

    @NotNull
    @Column(name = "position_x", nullable = false)
    private Float positionX;

    @NotNull
    @Column(name = "position_y", nullable = false)
    private Float positionY;

    @NotNull
    @Column(name = "position_z", nullable = false)
    private Float positionZ;

}