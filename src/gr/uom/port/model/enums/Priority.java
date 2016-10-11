package gr.uom.port.model.enums;

import java.util.Random;

public enum Priority {

    LOW(1),
    MID(2),
    HIGH(3) {
        @Override
        public Priority increasePriority() {
            return values()[ordinal()];
        }
    };

    private int value;

    Priority(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Priority increasePriority() {
        return values()[ordinal() + 1];
    }

    public static Priority getRandomPriority(final Random rand) {
        return values()[rand.nextInt(values().length)];
    }
}
