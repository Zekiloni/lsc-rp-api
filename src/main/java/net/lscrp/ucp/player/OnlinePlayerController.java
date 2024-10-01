package net.lscrp.ucp.player;

import net.lscrp.ucp.faction.FactionService;
import net.lscrp.ucp.server.api.OnlinePlayersApi;
import lombok.RequiredArgsConstructor;
import net.lscrp.ucp.server.model.OnlinePlayer;
import net.lscrp.ucp.server.model.OnlinePlayerStat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OnlinePlayerController implements OnlinePlayersApi {

    private final OnlinePlayerService onlinePlayerService;
    private final OnlinePlayerMapper onlinePlayerMapper;
    private final OnlinePlayerStatMapper onlinePlayerStatMapper;
    private final OnlinePlayerStatService onlinePlayerStatService;

    @Override
    public ResponseEntity<List<OnlinePlayer>> listOnlinePlayers() {
        return ResponseEntity.ok(onlinePlayerMapper.mapTo(onlinePlayerService.getOnlinePlayers()));
    }

    @Override
    public ResponseEntity<List<OnlinePlayerStat>> listOnlinePlayersStat(OffsetDateTime startingAt, OffsetDateTime endingAt) {
        return ResponseEntity.ok(onlinePlayerStatMapper.mapTo(onlinePlayerStatService.getPlayerStatInDateRange(startingAt, endingAt)));
    }
}
