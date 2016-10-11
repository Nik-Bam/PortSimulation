package gr.uom.port.model;

import java.util.ArrayList;

public class Dock {

    private int length, numCranes;
    private final ArrayList<CargoShip> ships = new ArrayList<>();

    public Dock(final int length, final int numCranes) {
        this.length = length;
        this.numCranes = numCranes;
    }

    public double getBestFit(final CargoShip ship) {
        double space = length - ship.getLength();

        for (final CargoShip s : ships) {
            space -= s.getLength();
        }

        return space;
    }

    public boolean addShip(final CargoShip ship) {
        boolean success = false;

        if (getBestFit(ship) >= 0) {
            ships.add(ship);
            success = true;
        }

        return success;
    }

    public boolean removeShip(final CargoShip ship) {
        return ships.remove(ship);
    }

    public ArrayList<CargoShip> getShips() {
        return ships;
    }

    public int getLength() {
        return length;
    }

    public int getNumCranes() {
        return numCranes;
    }

    @Override
    public String toString() {
        StringBuilder ships = new StringBuilder("| ");

        for (CargoShip c : this.ships) {
            ships.append((int) c.getLength()).append(" (").append(c.toString()).append(")").append(" | ");    //TODO Remove cast
        }
        ships = (ships.toString().equals("| ")) ? new StringBuilder("- ") : ships;

        return getLength() + ": " + ships.toString();
    }
}
