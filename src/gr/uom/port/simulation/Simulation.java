package gr.uom.port.simulation;

import gr.uom.port.model.Dock;
import gr.uom.port.model.Port;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Simulation {

    private static final int TIME_LIMIT = 100000;   // Run the simulation for 100,000 hours   //TODO Change

    private Port port;
    private int parameters[];
    private final Random rand = new Random();
    private final Queue queue = new Queue();
    private final ArrayList<Service> services = new ArrayList<>();
    private final SimReport report = new SimReport(this);
    private double currentTime = 0;

    public Simulation(final Port port, final long seed, final int parameters[]) {
        this.port = port;
        this.parameters = parameters;
        rand.setSeed(seed);
    }

    public void runSimulation() {

        while (currentTime < TIME_LIMIT) {
            final IEvent nextEvent = getNextEvent();

            if (nextEvent instanceof Arrival) {
                Arrival arrival = (Arrival) nextEvent;

                searchAndAddArrival(arrival, currentTime);

                final Arrival newArrival = ArrivalFactory.getArrival(rand, currentTime);
                queue.add(newArrival);
                currentTime = newArrival.getStartTime();
            } else if (nextEvent instanceof Service) {
                final Service departure = (Service) nextEvent;
                Arrival candidate = queue.getLast();

                services.remove(departure);
                port.undockShip(departure.getShip());
                report.addShipServiced(departure.getShip());

                searchAndAddArrival(candidate, departure.getFinishTime());
            }
//            System.out.println(report.getCurrentStatus());  //TODO Remove
        }

        emptyRemainingDepartures();
        report.setTotalTime(currentTime);
    }

    private void searchAndAddArrival(Arrival arrival, final double startTime) {
        int index = queue.getSize() - 1;
        boolean done = false;

        while (!done) {
            final Dock dock = port.getAvailableDock(arrival.getShip());

            if (dock != null && arrival.getStartTime() <= startTime) {
                queue.remove(arrival);
                arrival.setFinishTime(startTime);
                services.add(ServiceFactory.getService(arrival.getId(), rand, arrival.getShip(), dock, startTime));
                port.dockShip(arrival.getShip());
                done = true;
            } else {
                index--;
                if (index < 0) {
                    done = true;
                } else {
                    arrival = queue.get(index);
                }
            }
        }
    }

    private IEvent getNextEvent() {
        IEvent event;

        if (queue.isEmpty()) {
            event = ArrivalFactory.getArrival(rand, currentTime);
            queue.add((Arrival) event);
            currentTime = event.getStartTime();
        } else {
            if (services.isEmpty()) {
                event = queue.getLast();
            } else {
                final Service minService = Collections.min(services);

                event = (currentTime < minService.getFinishTime()) ? queue.getLast() : minService;
            }
        }

        return event;
    }

    private void emptyRemainingDepartures() {
        Iterator<Service> it = services.iterator();

        while (it.hasNext()) {
            Service departure = it.next();

            it.remove();
            port.undockShip(departure.getShip());
            report.addShipServiced(departure.getShip());
        }
    }

    public int[] getParameters() {
        return parameters;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public SimReport getReport() {
        return report;
    }

    public int getTimeLimit() {
        return TIME_LIMIT;
    }

    @Override
    public String toString() {
        String portString = port.toString();

        for (Service s : services) {
            String ship = s.getShip().toString();
            String details = "ID = " + String.valueOf(s.getId()) + ", StartTime = " + (int) s.getStartTime() + ", FinishTime = " + (int) s.getFinishTime(); //TODO Remove cast
            portString = portString.replace(ship, details);
        }
        return "----------------------------------------------------------------------------------------------------" + "\n" +
                "CurrentTime: " + (int) currentTime + "\n" +    //TODO Remove cast
                queue.toString() + "\n" +
                "QueueSize: " + queue.getSize() + "\n" +
                portString + "\n" +
                "----------------------------------------------------------------------------------------------------" + "\n";
    }
}
