package net.lscrp.ucp.faction;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "factions")
public class FactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 64)
    @NotNull
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Size(max = 64)
    @NotNull
    @Column(name = "short_name", nullable = false, length = 64)
    private String shortName;

    @NotNull
    @Column(name = "type", nullable = false)
    private Integer type;

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
    @Column(name = "virtual_world", nullable = false)
    private Integer virtualWorld;

    @NotNull
    @Column(name = "interior", nullable = false)
    private Integer interior;

    @NotNull
    @Column(name = "budget", nullable = false)
    private Integer budget;

}