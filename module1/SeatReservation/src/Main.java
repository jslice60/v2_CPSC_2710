import java.time.LocalDate;

public static void main(String[] args) {
    // Create an instance of SeatReservation
    SeatReservation reservation = new SeatReservation("AA123", LocalDate.of(2023, 8, 26), "John", "Doe");
    System.out.println(reservation.toString());

    // Testing my setters
    reservation.setLastName("run Smithy");
    reservation.setFlightDesignator("BB456");

    // Print modified reservation details
    System.out.println(reservation.toString());
}

