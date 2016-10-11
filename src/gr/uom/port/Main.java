package gr.uom.port;

import gr.uom.port.simulation.SimManager;
import gr.uom.port.util.ArgValidator;

public class Main {

    public static void main(final String args[]) {
        int[] parameters = ArgValidator.validateArguments(args);

        if (parameters == null) {
            ArgValidator.printUsage();
            return;
        }

        SimManager simManager = new SimManager(parameters);
        simManager.start();
    }
}
