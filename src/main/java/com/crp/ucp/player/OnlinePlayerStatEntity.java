package com.crp.ucp.player;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "online_player_stats")
public class OnlinePlayerStatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "player_count", nullable = false)
    private Integer playerCount;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}