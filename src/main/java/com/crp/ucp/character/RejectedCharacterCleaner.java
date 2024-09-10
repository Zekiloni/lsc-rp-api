package com.crp.ucp.character;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class RejectedCharacterCleaner {
    public static final OffsetDateTime CLEANUP_THRESHOLD = OffsetDateTime.now().minusDays(2);

    private final CharacterService characterService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void run() {
        characterService.getAllRejectedCharacters()
                .stream()
                .filter(RejectedCharacterCleaner::isCharacterApplicationOldEnough)
                .forEach(characterService::deleteCharacter);
    }

    private static boolean isCharacterApplicationOldEnough(CharacterEntity a) {
        return a.getApprovedAt().isBefore(CLEANUP_THRESHOLD);
    }
}
