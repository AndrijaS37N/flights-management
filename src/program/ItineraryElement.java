package program;

import java.util.*;

public class ItineraryElement {

    private Map<String, Airport> vertices;
    private Map<String, Flight> edges;
    private String result;

    public ItineraryElement() {
        this.vertices = new HashMap<String, Airport>();
        this.edges = new HashMap<String, Flight>();
    }

    public String readyToCompute(String startPos, String endPos, ItineraryElement graph) {
        try {
            // send a copy of the graph again and the id of the starting position
            Planner planner = new Planner(graph, graph.getAirport(startPos).getId());
            result = String.valueOf(planner.getPathTo(endPos)) + " ETA: " + String.valueOf(planner.getDistanceTo(endPos) + planner.airportTime()) + " min";
        } catch (Exception e) {
            System.out.println("Whoops! Something went wrong: " + e.toString());
        }
        return result;
    }

    public void addFlight(Flight f) {
        edges.put(f.getId(), f);
        f.getStartDest().addNeighbor(f);
        f.getEndDest().addNeighbor(f);
    }

    public Airport getAirport(String id) {
        return vertices.get(id);
    }

    public void addAirport(Airport vertex) {
        vertices.put(vertex.getId(), vertex);
    }

    public Set<String> vertexKeys() {
        return vertices.keySet();
    }

    public Set<Airport> getAirports() {
        return new HashSet<Airport>(vertices.values());
    }

    public Set<Flight> getFlights() {
        return new HashSet<Flight>(edges.values());
    }

    public Map<String, Airport> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        return "ItineraryElement{" + "vertices=" + vertices + ", edges=" + edges + '}';
    }
}
