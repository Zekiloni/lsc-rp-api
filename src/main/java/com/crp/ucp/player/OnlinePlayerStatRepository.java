package com.crp.ucp.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface OnlinePlayerStatRepository extends JpaRepository<OnlinePlayerStatEntity, Integer> {

    @Query("SELECT op FROM OnlinePlayerStatEntity op WHERE op.createdAt BETWEEN :startingAt AND :endingAt")
    List<OnlinePlayerStatEntity> findInDateRange(@Param("startingAt") OffsetDateTime startingAt,
                                                 @Param("endingAt") OffsetDateTime endingAt);
}
