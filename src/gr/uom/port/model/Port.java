package gr.uom.port.model;

import java.util.stream.IntStream;

public class Port {

    public static final int PRIORITY_AGING = 3; // After 2 ships of higher priority pass a ship of lower priority then increase its priority

    private int numCranes, numDocks;
    private Dock docks[];

    public Port(final int dockLengths[], final int cranes[]) {
        numCranes = IntStream.of(cranes).sum();
        numDocks = cranes.length;
        docks = new Dock[numDocks];

        for (int i = 0; i < numDocks; i++) {
            docks[i] = new Dock(dockLengths[i], cranes[i]);
        }
    }

    public Dock getAvailableDock(final CargoShip ship) {
        Dock dock = null;
        double min = Double.MAX_VALUE;

        for (Dock d : docks) {
            double bestFit = d.getBestFit(ship);
            if (bestFit >= 0 && bestFit < min) {
                dock = d;
                min = bestFit;
            }
        }

        return dock;
    }

    public boolean dockShip(final CargoShip ship) {
        boolean success = false;
        Dock dock = getAvailableDock(ship);

        if (dock != null) {
            success = dock.addShip(ship);
        }

        return success;
    }

    public boolean undockShip(final CargoShip ship) {
        boolean success = false;

        for (Dock d : docks) {
            if (success = d.removeShip(ship)) {
                break;
            }
        }

        return success;
    }

    public int getNumCranes() {
        return numCranes;
    }

    public int getNumDocks() {
        return numDocks;
    }

    public Dock[] getDocks() {
        return docks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Port{\tDocks = [");
        String prefix = "\t";

        for (Dock d : docks) {
            sb.append(prefix).append(d.toString()).append(",").append("\n");
            prefix = "\t\t\t\t\t";
        }

        sb.setLength(sb.length() - 2);
        sb.append("]").append("\n").append("\t}");
        return sb.toString();
    }
}
