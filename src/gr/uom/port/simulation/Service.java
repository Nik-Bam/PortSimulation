package gr.uom.port.simulation;

import gr.uom.port.model.CargoShip;
import gr.uom.port.model.Dock;

public class Service implements IEvent, Comparable<Service> {

    private CargoShip ship;
    private Dock dock;
    private double startTime, waitTime, finishTime;
    private int id;

    public Service(final int id, final CargoShip ship, final Dock dock, final double startTime, final double waitTime) {
        this.id = id;
        this.ship = ship;
        this.dock = dock;
        this.startTime = startTime;
        this.waitTime = waitTime;
        finishTime = startTime + waitTime;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public CargoShip getShip() {
        return ship;
    }

    public Dock getDock() {
        return dock;
    }

    @Override
    public double getStartTime() {
        return startTime;
    }

    @Override
    public double getWaitTime() {
        return waitTime;
    }

    @Override
    public double getFinishTime() {
        return finishTime;
    }

    @Override
    public int compareTo(Service service) {
        if (this.finishTime < service.finishTime) return -1;
        if (this.finishTime > service.finishTime) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return getId() + ": " +
                "Ship = " + getShip().getLength() +
                ", Dock = " + getDock().getLength() +
                ", StartTime = " + getStartTime() +
                ", FinishTime = " + getFinishTime();
    }
}
