package com.crp.ucp.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OnlinePlayerStatService {

    private final OnlinePlayerStatRepository onlinePlayerStatRepository;

    public List<OnlinePlayerStatEntity> getPlayerStatInDateRange(OffsetDateTime startingAt, OffsetDateTime endingAt) {
        return onlinePlayerStatRepository.findInDateRange(
                Objects.isNull(startingAt) ? OffsetDateTime.MIN : startingAt,
                Objects.isNull(endingAt) ? OffsetDateTime.MAX : endingAt);
    }
}
