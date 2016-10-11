package gr.uom.port.util;

import gr.uom.port.model.enums.Priority;
import gr.uom.port.simulation.Arrival;

import java.util.Comparator;

public class ArrivalComparator implements Comparator<Arrival> {
    @Override
    public int compare(final Arrival arrival1, final Arrival arrival2) {
        int result;
        final Priority priority1 = arrival1.getPriority();
        final Priority priority2 = arrival2.getPriority();

        if (arrival1.getId() == arrival2.getId()) {
            result = 0;
        } else if (priority1 == priority2) {
            result = arrival1.compareTo(arrival2);
        } else {
            result = priority1.compareTo(priority2);
        }
        return result;
    }
}
