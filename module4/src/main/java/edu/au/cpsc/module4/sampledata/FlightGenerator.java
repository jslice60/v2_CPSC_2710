package edu.au.cpsc.module4.sampledata;

import edu.au.cpsc.module4.model.ScheduledFlight;
import edu.au.cpsc.module4.data.Db;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


public class FlightGenerator {
    public static void main(String[] args) {
        // Days of the week for the flight
        Set<String> daysOfWeek = new HashSet<>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("R");
        daysOfWeek.add("Friday");
        daysOfWeek.add("Saturday");
        daysOfWeek.add("U");

        ScheduledFlight c1 = new ScheduledFlight("DL123", "KPIT", LocalTime.of(10,30),
                "KLAX", LocalTime.of(12,45), daysOfWeek);
        ScheduledFlight c2 = new ScheduledFlight("AA456", "KMSP", LocalTime.of(13,25),
                "KDCA", LocalTime.of(18,15),daysOfWeek);


        Db.getDatabase().addScheduledFlight(c1);
        Db.getDatabase().addScheduledFlight(c2);
        Db.saveDatabase();
    }


}
