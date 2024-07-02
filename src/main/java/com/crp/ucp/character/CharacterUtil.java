package com.crp.ucp.character;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import static java.text.MessageFormat.format;

@UtilityClass
@Slf4j
public class CharacterUtil {

    public static Supplier<NoSuchElementException> throwCharacterNotFoundException(Integer characterId) {
        return () -> new NoSuchElementException(format("Character with ID {0} not found", characterId));
    }

    public static double getCharacterDailyAverageActivity(CharacterEntity member) {
        long totalDaysRegistered = ChronoUnit.DAYS.between(member.getCreatedAt(), OffsetDateTime.now());
        int totalHoursPlayed = member.getHours() + (member.getMinutes() / 60);

        if (totalDaysRegistered == 0) {
            totalDaysRegistered = 1;
        }

        return (double) totalHoursPlayed / totalDaysRegistered;
    }
}
