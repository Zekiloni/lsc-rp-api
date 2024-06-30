package com.crp.ucp.character;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
@Slf4j
public class CharacterUtil {

    public static double getCharacterDailyAverageActivity(CharacterEntity member) {
        long totalDaysRegistered = ChronoUnit.DAYS.between(member.getCreatedAt(), OffsetDateTime.now());
        int totalHoursPlayed = member.getHours() + (member.getMinutes() / 60);

        if (totalDaysRegistered == 0) {
            totalDaysRegistered = 1;
        }

        return (double) totalHoursPlayed / totalDaysRegistered;
    }
}
