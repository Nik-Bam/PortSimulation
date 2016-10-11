package gr.uom.port.simulation;

import gr.uom.port.model.CargoShip;
import gr.uom.port.model.CargoShipFactory;
import gr.uom.port.util.Generator;

import java.util.Random;

public class ArrivalFactory {

    private static final double ARRIVAL_MEAN = 12;  // Mean of arrivals: One ship arrives every 12 hours

    private ArrivalFactory() {
    }

    public static Arrival getArrival(final Random rand, final double offset) {
        final CargoShip ship = CargoShipFactory.getCargoShip(rand);
        final double time = Generator.exponential(rand, ARRIVAL_MEAN);

        return new Arrival(ship, offset + time);
    }
}
