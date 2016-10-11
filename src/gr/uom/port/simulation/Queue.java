package gr.uom.port.simulation;

import gr.uom.port.model.Port;
import gr.uom.port.util.ArrivalComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Queue {

    private final ArrayList<Arrival> queue = new ArrayList<>();
    private final ArrivalComparator comparator = new ArrivalComparator();

    private void addAndSort(final Arrival arrival) {
        queue.add(arrival);
        Collections.sort(queue, comparator);
    }

    public void add(final Arrival arrival) {
        addAndSort(arrival);
        queue.stream().filter(a -> a.getPriority().getValue() < arrival.getPriority().getValue()).forEach(Arrival::incrAge);
        update();
    }

    public boolean remove(final Arrival arrival) {
        return queue.remove(arrival);
    }

    private void update() {
        ArrayList<Arrival> temp = queue.stream().filter(a -> a.getAge() >= Port.PRIORITY_AGING).collect(Collectors.toCollection(ArrayList::new));

        for (Arrival a : temp) {
            remove(a);
            a.increasePriority();
            addAndSort(a);
        }
    }

    public Arrival getAndRemove() {
        Arrival arrival = !queue.isEmpty() ? queue.get(queue.size() - 1) : null;
        queue.remove(arrival);
        return arrival;
    }

    public Arrival getLast() {
        return !queue.isEmpty() ? queue.get(queue.size() - 1) : null;
    }

    public Arrival get(int index) {
        return queue.get(index);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getSize() {
        return queue.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue{");
        String prefix = "\t";

        for (Arrival a : queue) {
            sb.append(prefix).append(a.toString()).append(",").append("\n");
            prefix = "\t\t";
        }

        sb.setLength(sb.length() - 2);
        sb.append("\n").append("\t}");
        return sb.toString();
    }
}
