package gr.uom.port.simulation;

import gr.uom.port.model.CargoShip;
import gr.uom.port.model.Dock;
import gr.uom.port.util.Generator;

import java.util.Random;

public class ServiceFactory {

    private ServiceFactory() {
    }

    public static Service getService(final int id, final Random rand, final CargoShip ship, final Dock dock, final double startTime) {
        final double serviceTime = ship.getLength() * Generator.uniform(rand, 0, 1) + 20 / dock.getNumCranes();

        return new Service(id, ship, dock, startTime, serviceTime);
    }
}
