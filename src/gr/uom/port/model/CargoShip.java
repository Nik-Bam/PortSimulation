package gr.uom.port.model;

import gr.uom.port.model.enums.Priority;

public class CargoShip {

    private Priority priority;
    private double length;

    public CargoShip(final double length, final Priority priority) {
        this.length = length;
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(final Priority priority) {
        this.priority = priority;
    }

    public double getLength() {
        return length;
    }
}
