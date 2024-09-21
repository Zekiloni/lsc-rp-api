package net.lscrp.ucp.character;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class RejectedCharacterCleaner {
    public static final int CLEANUP_THRESHOLD_IN_DAYS = 2;

    private final CharacterService characterService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void run() {
        characterService.getAllRejectedCharacters()
                .stream()
                .filter(RejectedCharacterCleaner::isCharacterApplicationOldEnough)
                .forEach(characterService::deleteCharacter);
    }

    private static boolean isCharacterApplicationOldEnough(CharacterEntity character) {
        boolean before = character.getApprovedAt()
                .isBefore(OffsetDateTime.now().minusDays(CLEANUP_THRESHOLD_IN_DAYS));

        log.info(String.format("Rejected character %s is older then %d days and will be deleted",
                character.getName(), CLEANUP_THRESHOLD_IN_DAYS));
        return before;
    }
}
