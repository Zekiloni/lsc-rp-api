package net.lscrp.ucp.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OnlinePlayerStatService {

    private static final OffsetDateTime DEFAULT_STARTING_AT = OffsetDateTime.of(1000, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    private static final OffsetDateTime DEFAULT_ENDING_AT = OffsetDateTime.of(9999, 12, 31, 23, 59, 59, 999999999, ZoneOffset.UTC);


    private final OnlinePlayerStatRepository onlinePlayerStatRepository;

    public List<OnlinePlayerStatEntity> getPlayerStatInDateRange(OffsetDateTime startingAt, OffsetDateTime endingAt) {
        return onlinePlayerStatRepository.findInDateRange(
                Objects.isNull(startingAt) ? DEFAULT_STARTING_AT : startingAt,
                Objects.isNull(endingAt) ? DEFAULT_ENDING_AT : endingAt);
    }
}
