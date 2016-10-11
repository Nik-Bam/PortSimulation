package gr.uom.port.simulation;

import gr.uom.port.model.Port;
import gr.uom.port.util.Combinations;

import java.util.ArrayList;

public class SimManager {

    private static final int SIM_LOOPS = 10; // Total number of reruns
    private static final long SEEDS[] = new long[SIM_LOOPS];

    private int parameters[];
    private int numCranes, numDocks;
    private int docks[];

    public SimManager(int parameters[]) {
        this.parameters = parameters;
        parseParameters();
    }

    public void start() {
        final ArrayList<int[]> combinations = Combinations.getCombinationsNoZeros(numCranes, numDocks);

        for (int i = 0; i < SIM_LOOPS; i++) {
            long seed = Double.doubleToLongBits(Math.random());
            SEEDS[i] = seed;

            String s = i + 1 < 10 ? "0" + String.valueOf(i + 1) : String.valueOf(i + 1);    //TODO Remove
            System.out.println("\n*************************************************" + s + "*************************************************");    //TODO Remove

            for (int[] comb : combinations) {
                final Port port = new Port(docks, comb);

                Simulation sim = new Simulation(port, seed, comb);
                sim.runSimulation();

                System.out.println(sim.getReport().getSmallReport());   //TODO Remove
            }
        }
//        System.out.println("SEEDS: " + Arrays.toString(SEEDS));   //TODO Remove
    }

    private void parseParameters() {
        numCranes = parameters[0];
        numDocks = parameters[1];
        docks = new int[numDocks];

        System.arraycopy(parameters, 2, docks, 0, numDocks);
    }
}
