package gr.uom.port.util;

public class ArgValidator {

    private static final String FILE_NAME = new java.io.File(ArgValidator.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();

    private ArgValidator() {
    }

    public static int[] validateArguments(final String args[]) {
        int parameters[] = new int[args.length];
        boolean error = false;

        if (args.length > 2) {
            try {
                parameters[0] = Integer.parseInt(args[0]);
                parameters[1] = Integer.parseInt(args[1]);

                if (parameters[1] == args.length - 2) {
                    for (int i = 2; i < args.length; i++) {
                        try {
                            parameters[i] = Integer.parseInt(args[i]);
                        } catch (NumberFormatException e) {
                            error = true;
                            System.out.println("Error: \tOnly integers are allowed");
                            System.out.println();
                            break;
                        }
                    }
                } else {
                    error = true;
                    System.out.println("Error: \tIllegal number of arguments.");
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                error = true;
                System.out.println("Error: \tOnly integers are allowed");
                System.out.println();
            }
        } else {
            error = true;
            System.out.println("Error: \tIllegal number of arguments.");
            System.out.println();
        }
        return error ? null : parameters;
    }

    public static void printUsage() {
        System.out.println("Usage: \t" + FILE_NAME + " Cranes Docks Length_1 Length_2 ...");
        System.out.println();
        System.out.println("Details: \tCranes \t\t= Number of cranes");
        System.out.println("\t\tDocks \t\t= Number of docks");
        System.out.println("\t\tLength_1 \t= Length of the first dock");
        System.out.println("\t\tLength_2 \t= Length of the second dock");
        System.out.println("\t\t... \t\t= Length of the rest docks");
    }
}
