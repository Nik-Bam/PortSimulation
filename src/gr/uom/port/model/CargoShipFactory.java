package gr.uom.port.model;

import gr.uom.port.model.enums.Priority;
import gr.uom.port.util.Generator;

import java.util.Random;

public class CargoShipFactory {

    private static final int LENGTH_MIN = 40;
    private static final int LENGTH_MAX = 120;

    private CargoShipFactory() {
    }

    public static CargoShip getCargoShip(final Random rand) {
        final double length = Generator.uniform(rand, LENGTH_MIN, LENGTH_MAX);
        final Priority priority = Priority.getRandomPriority(rand);

        return new CargoShip(length, priority);
    }
}
