package edu.au.cpsc.module6.sampledata;

import edu.au.cpsc.module6.model.ScheduledFlight;
import edu.au.cpsc.module6.data.Db;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


public class FlightGenerator {
    public static void main(String[] args) {
        // Days of the week for the flight
        Set<DayOfWeek> daysOfWeek = new HashSet<>();
        daysOfWeek.add(DayOfWeek.MONDAY);
        daysOfWeek.add(DayOfWeek.TUESDAY);
        daysOfWeek.add(DayOfWeek.WEDNESDAY);
        daysOfWeek.add(DayOfWeek.THURSDAY);  // 'R' corresponds to Thursday
        daysOfWeek.add(DayOfWeek.FRIDAY);
        daysOfWeek.add(DayOfWeek.SATURDAY);
        daysOfWeek.add(DayOfWeek.SUNDAY);  // 'U' corresponds to Sunday

        ScheduledFlight c1 = new ScheduledFlight("DL123", "KPIT", LocalTime.of(10, 30),
                "KLAX", LocalTime.of(12, 45), (HashSet<DayOfWeek>) daysOfWeek);
        ScheduledFlight c2 = new ScheduledFlight("AA456", "KMSP", LocalTime.of(13, 25),
                "KDCA", LocalTime.of(18, 15), (HashSet<DayOfWeek>) daysOfWeek);


        Db.getDatabase().addScheduledFlight(c1);
        Db.getDatabase().addScheduledFlight(c2);
        Db.saveDatabase();
    }


}
