package program;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Planner {

    private ItineraryElement graph;
    private String initialAirportLabel;

    private HashMap<String, String> followers;
    private HashMap<String, Integer> distances;

    private PriorityQueue<Airport> availableAirports;
    private LinkedList<Airport> path = new LinkedList<Airport>();

    public Planner(ItineraryElement graph, String initialAirportLabel) {

        this.graph = graph;
        Set<String> vertexKeys = this.graph.vertexKeys();

        this.initialAirportLabel = initialAirportLabel;
        this.followers = new HashMap<String, String>();
        this.distances = new HashMap<String, Integer>();

        this.availableAirports = new PriorityQueue<Airport>(vertexKeys.size(), new Comparator<Airport>() {

            public int compare(Airport startDest, Airport endDest) {

                return Planner.this.distances.get(endDest.getId());
            }
        });

        // for each Airport in the graph assume that it has a distance of infinity
        for (String key : vertexKeys) {

            followers.put(key, null);
            distances.put(key, Integer.MAX_VALUE);
        }

        // seeding of the initialAirport's neighbors
        Airport initialAirport = this.graph.getAirport(initialAirportLabel);
        ArrayList<Flight> initialAirportNeighbors = initialAirport.getNeighborhood();

        for (Flight e : initialAirportNeighbors) {

            Airport other = e.getNeighbor(initialAirport);
            followers.put(other.getId(), initialAirportLabel);
            distances.put(other.getId(), e.getFlightTime());
            availableAirports.add(other);
        }

        processItineraryElement();
    }

    private void processItineraryElement() {

        while (availableAirports.size() > 0) {

            Airport next = availableAirports.poll();
            int distanceToNext = distances.get(next.getId());

            // for each available neighbor of the chosen vertex
            List<Flight> nextNeighbors = next.getNeighborhood();

            for (Flight e : nextNeighbors) {

                Airport other = e.getNeighbor(next);

                // we check if a shorter path exists and update to point to a new shortest found path in the graph
                int currentWeight = distances.get(other.getId());
                int newWeight = distanceToNext + e.getFlightTime();

                if (newWeight < currentWeight) {

                    followers.put(other.getId(), next.getId());
                    distances.put(other.getId(), newWeight);
                    availableAirports.remove(other);
                    availableAirports.add(other);
                }
            }
        }
    }

    public List<Airport> getPathTo(String destinationLabel) {

        path.add(graph.getAirport(destinationLabel));

        while (!destinationLabel.equals(initialAirportLabel)) {

            Airport predecessor = graph.getAirport(followers.get(destinationLabel));
            destinationLabel = predecessor.getId();
            path.add(0, predecessor);
        }

        return path;
    }

    public int getDistanceTo(String destinationLabel) {
        return distances.get(destinationLabel);
    }

    public int airportTime() {

        int mct_time = 0;

        for (Airport a : path)
            mct_time = mct_time + a.getMinConnectionTime();

        return mct_time;
    }
}
