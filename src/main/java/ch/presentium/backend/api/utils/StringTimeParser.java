package ch.presentium.backend.api.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Objects;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;

/**
 * Utility class to parse string time to LocalDateTime
 */
public class StringTimeParser {

    /**
     * Get start and end dates from string
     * @param startDateStr start date string
     * @param endDateStr   end date string
     * @return Pair of start and end dates
     */
    public static Pair<LocalDateTime, LocalDateTime> getStartEndDates(String startDateStr, String endDateStr) {
        LocalDateTime startDate = Objects.requireNonNull(
            StringTimeParser.parseStringToLocalDate(startDateStr)
        ).atStartOfDay();
        LocalDateTime endDate = Objects.requireNonNull(StringTimeParser.parseStringToLocalDate(endDateStr)).atTime(
            LocalTime.MAX
        );
        check(startDate, "Invalid start date");
        check(endDate, "Invalid end date");
        return Pair.of(startDate, endDate);
    }

    /**
     * Check if object is null
     * @param obj     object to check
     * @param message message to return if object is null
     */
    private static void check(Object obj, String message) {
        if (obj == null) {
            ResponseEntity.badRequest().body(Collections.singletonMap("message", message));
        }
    }

    /**
     * Parse string to LocalDate
     * @param date string to parse
     * @return LocalDate object
     */
    private static LocalDate parseStringToLocalDate(String date) {
        LocalDate objectDate;
        try {
            objectDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            return null;
        }
        return objectDate;
    }
}
