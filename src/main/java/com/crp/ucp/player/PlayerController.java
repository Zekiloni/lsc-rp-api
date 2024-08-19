package com.crp.ucp.player;

import com.crp.ucp.server.api.OnlinePlayersApi;
import com.crp.ucp.server.api.StatsApi;
import com.crp.ucp.server.model.OnlinePlayerStat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerController implements StatsApi {

    private final OnlinePlayerStatMapper onlinePlayerStatMapper;
    private final OnlinePlayerStatService onlinePlayerStatService;

    @Override
    public ResponseEntity<List<OnlinePlayerStat>> listOnlinePlayersStat(OffsetDateTime startingAt, OffsetDateTime endingAt) {
        return ResponseEntity.ok(
                onlinePlayerStatMapper.mapTo(onlinePlayerStatService.getPlayerStatInDateRange(startingAt, endingAt))
        );
    }

}
