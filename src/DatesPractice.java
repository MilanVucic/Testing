import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DatesPractice {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        for (String zoneId : ZoneId.getAvailableZoneIds()) {
            System.out.println(zoneId);
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Belgrade"));
    }

    private static void daysBetween2Dates() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2023, Month.APRIL, 27);
        System.out.println(ChronoUnit.DAYS.between(startDate, endDate));
    }
}
