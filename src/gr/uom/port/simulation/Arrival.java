package gr.uom.port.simulation;

import gr.uom.port.model.CargoShip;
import gr.uom.port.model.enums.Priority;
import gr.uom.port.util.Generator;

public class Arrival implements IEvent, Comparable<Arrival> {

    private CargoShip ship;
    private double startTime;
    private double waitTime = IEvent.UNDEFINED_TIME;
    private double finishTime = IEvent.UNDEFINED_TIME;
    private Priority priority;
    private int age = 1;
    private int id = Generator.getUniqueID();

    public Arrival(final CargoShip ship, final double startTime) {
        this.ship = ship;
        this.startTime = startTime;
        priority = ship.getPriority();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public CargoShip getShip() {
        return ship;
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

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
        waitTime = finishTime - startTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public void increasePriority() {
        priority = priority.increasePriority();
        ship.setPriority(priority);
        resetAge();
    }

    public int getAge() {
        return age;
    }

    public void incrAge() {
        age++;
    }

    private void resetAge() {
        age = 1;
    }

    @Override
    public int compareTo(Arrival arrival) {
        if (this.startTime > arrival.startTime) return -1;
        if (this.startTime < arrival.startTime) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return getId() + ": " +
                "Age = " + getAge() +
                ", Priority = " + getPriority() +
                ", ShipLength = " + (int) getShip().getLength() +   //TODO Remove cast
                ", StartTime = " + (int) getStartTime();    //TODO Remove cast
    }
}
