package com.crp.ucp.faction;

import lombok.experimental.UtilityClass;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

import static java.text.MessageFormat.format;

@UtilityClass
public class FactionUtil {

    public static int NO_FACTION_ID = 0;

    public static Supplier<NoSuchElementException> throwFactionNotFoundException(Integer factionId) {
        return () -> new NoSuchElementException(format("Faction with ID {0} not found", factionId));
    }
}
