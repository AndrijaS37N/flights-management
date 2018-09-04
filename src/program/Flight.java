package program;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Flight {

    private String id;
    private Date takeOff;
    private Date landingTime;
    private Airport startDest, endDest;
    private int flightTime;

    public Flight(String id, Airport startDest, Airport endDest, Date takeOff, Date landingTime) {

        this.id = id;
        this.startDest = startDest;
        this.endDest = endDest;
        this.takeOff = takeOff;
        this.landingTime = landingTime;
        this.flightTime = calculateFlightTime(takeOff, landingTime);
    }

    private int calculateFlightTime(Date d1, Date d2) {

        int diff = (int) d2.getTime() - (int) d1.getTime();
        int returnTime;
        returnTime = (int) TimeUnit.MILLISECONDS.toMinutes(diff);

        return returnTime;
    }

    public Airport getNeighbor(Airport current) {

        if (!(current.equals(startDest) || current.equals(endDest))) return null;

        return (current.equals(startDest)) ? endDest : startDest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(Date takeOff) {
        this.takeOff = takeOff;
    }

    public Date getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(Date landingTime) {
        this.landingTime = landingTime;
    }

    public Airport getStartDest() {
        return startDest;
    }

    public void setStartDest(Airport startDest) {
        this.startDest = startDest;
    }

    public Airport getEndDest() {
        return endDest;
    }

    public void setEndDest(Airport endDest) {
        this.endDest = endDest;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", takeOff=" + takeOff + ", landingTime=" + landingTime + ", startDest=" + startDest + ", endDest=" + endDest + ", flightTime=" + flightTime + '}';
    }
}
