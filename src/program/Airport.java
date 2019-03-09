package program;

import java.util.ArrayList;

public class Airport {

    private String id;
    private int minConnectionTime;

    private ArrayList<Flight> neighborhood;

    public Airport(String id, int minConnectionTime) {

        this.id = id;
        this.minConnectionTime = minConnectionTime;
        this.neighborhood = new ArrayList<Flight>();
    }

    public void addNeighbor(Flight edge) {
        neighborhood.add(edge);
    }

    public Flight getNeighbor(int index) {
        return neighborhood.get(index);
    }

    public boolean containsNeighbor(Flight other) {
        return neighborhood.contains(other);
    }

    public void removeNeighbor(Flight e) {
        neighborhood.remove(e);
    }

    public ArrayList<Flight> getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(ArrayList<Flight> neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMinConnectionTime() {
        return minConnectionTime;
    }

    public void setMinConnectionTime(int minConnectionTime) {
        this.minConnectionTime = minConnectionTime;
    }

    @Override
    public String toString() {
        return "Airport " + id;
    }
}
