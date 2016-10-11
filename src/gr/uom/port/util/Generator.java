package gr.uom.port.util;

import java.util.Random;

public class Generator {

    private static int idCounter = 0;

    private Generator() {
    }

    public static double exponential(final Random rand, final double mean) {
        return -mean * Math.log(rand.nextDouble());
    }

    public static double uniform(final Random rand, final double min, final double max) {
        return min + (max - min) * rand.nextDouble();
    }

    public static boolean randomPos(final Random rand, final int possibility) {
        return rand.nextInt(100) < possibility;
    }

    public static int getUniqueID() {
        return idCounter++;
    }
}
