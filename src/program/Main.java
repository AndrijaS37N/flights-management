package program;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    // the network
    private static ItineraryElement graph = new ItineraryElement();

    /*
        Note ⚠️: No A* algorithm, only Dijkstra's because of lack of influencing heuristics
        in the graph (mct variable is only added to the final ETA result).
    */
    public static void main(String[] args) {

        String airportId;
        int mct;
        System.out.println("Testing with pre-made airports, times and flights.");

        // make the airports
        for (int i = 0; i < 10; i++) {
            airportId = "A-ID-" + String.valueOf(i);
            mct = 20 + i;
            Airport a = new Airport(airportId, mct);
            graph.addAirport(a);
        }

        // make the dates
        Date d1 = null;
        Date d2 = null;

        DateFormat df = new SimpleDateFormat("dd.MM. | HH:mm");

        // 1 hour = 60 minutes times difference
        String takeOffTime = "01.01. | 10:00";
        String landingTime = "01.01. | 11:00";

        try {
            d1 = df.parse(takeOffTime);
            d2 = df.parse(landingTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // make the flights and put them in the graph
        for (int i = 0; i < 5; i++) {
            Flight f = new Flight(String.valueOf(i), graph.getVertices().get("A-ID-" + String.valueOf(i)), graph.getVertices().get("A-ID-" + String.valueOf(i + 1)), d1, d2);
            graph.addFlight(f);
        }

        // get two airports from the graph
        Airport a1 = graph.getVertices().get("A-ID-" + String.valueOf(0));
        Airport a2 = graph.getVertices().get("A-ID-" + String.valueOf(3));

        // let's have a flight from airport 0 to airport 3
        Flight specialFlight = new Flight("5", a1, a2, d1, d2);
        graph.addFlight(specialFlight);

        System.out.println("Itinerary components are:");
        System.out.println("Airports: " + graph.getAirports());
        System.out.println("Flights: " + graph.getFlights());
        /*
            Note: The results from airport 0 to airport 3 with the clock showing should be <=>
            60min flight time + waiting at airport 0 20min + waiting at airport 3 23min <=>
            60 + 20 + 23 <=>
            103min ✔️✔️✔️
        */
        System.out.println("Computing...\nThe result is:\n" + graph.readyToCompute(a1.getId(), a2.getId(), graph));
    }
}
