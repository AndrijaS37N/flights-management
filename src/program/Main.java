package program;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    private static ItineraryElement graph = new ItineraryElement();

    public static void main(String[] args) {

        String airportId;
        int mct;

        System.out.println("Testing with pre-made airports and flights.");

        for (int i = 0; i < 10; i++) {
            airportId = "A-ID-" + String.valueOf(i);
            mct = 20 + i; // 21, 23, 25 ...
            Airport a = new Airport(airportId, mct);
            graph.addAirport(a);
        }

        Date d1 = null;
        Date d2 = null;

        DateFormat df = new SimpleDateFormat("dd.MM. | HH:mm");

        String takeOffTime = "01.01. | 10:00";
        String landingTime = "01.01. | 11:00";

        try {
            // placeholders
            d1 = df.parse(takeOffTime);
            d2 = df.parse(landingTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            Flight f = new Flight(String.valueOf(i), graph.getVertices().get("A-ID-" + String.valueOf(i)), graph.getVertices().get("A-ID-" + String.valueOf(i + 1)), d1, d2);
            graph.addFlight(f);
        }

        Airport q1 = graph.getVertices().get("A-ID-" + String.valueOf(0));
        Airport q2 = graph.getVertices().get("A-ID-" + String.valueOf(3));

        // lets have a flight from airport 0 to airport 3
        Flight specialFlight = new Flight("5", q1, q2, d1, d2);
        graph.addFlight(specialFlight);

        System.out.println("Itinerary components are:");
        System.out.println("Airports: " + graph.getAirports());
        System.out.println("Flights: " + graph.getFlights());

        // the results should be from airport 0 to airport 3 with the clock showing <=>
        // 60min flight time + waiting at airport 0 20min + waiting at airport 3 23min <=>
        // 60 + 20 + 23 <=> 103min
        System.out.println("Computing...\nThe result is:\n" + graph.readyToCompute(q1.getId(), q2.getId(), graph));
    }
}
