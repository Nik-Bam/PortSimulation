package gr.uom.port.util;

import java.util.ArrayList;

public class Combinations {

    private Combinations() {
    }

    public static ArrayList<int[]> getCombinationsNoZeros(final int n, final int r) {
        final ArrayList<int[]> combinations = new ArrayList<>();
        if (r < 2) {
            combinations.add(new int[]{n});
            return combinations;
        }
        final ArrayList<int[]> separators = getSeparators(n - 1, r - 1);

        for (int[] entry : separators) {
            final int[] combination = new int[r];
            int temp = 0;

            for (int i = 0; i < combination.length; i++) {
                combination[i] = (i == entry.length) ? n - temp : entry[i] - temp;
                temp += combination[i];
            }
            combinations.add(combination);
        }
        return combinations;
    }

    public static ArrayList<int[]> getCombinationsWithZeros(final int n, final int r) {
        final ArrayList<int[]> combinations = new ArrayList<>();
        if (r < 2) {
            combinations.add(new int[]{n});
            return combinations;
        }
        final ArrayList<int[]> separators = getSeparators(n + 2, r - 1);

        for (int[] entry : separators) {
            final int[] combination = new int[r];
            int temp = 0;

            for (int i = 0; i < combination.length; i++) {
                if (i < entry.length && entry[i] > n + 1) {
                    entry[i] = (i - 1 >= 0) ? entry[i - 1] : entry[i];
                }
                combination[i] = (i == entry.length) ? n - temp : entry[i] - temp - 1;
                temp += combination[i];
            }
            combinations.add(combination);
        }
        return combinations;
    }

    private static ArrayList<int[]> getSeparators(final int n, final int r) {
        final ArrayList<int[]> separators = new ArrayList<>();
        final int[] res = new int[r];
        boolean done = false;

        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }
        while (!done) {
            separators.add(res.clone());
            done = getNext(res, n, r);
        }
        if (r < 2) {
            separators.remove(separators.size() - 1);
        }
        return separators;
    }

    private static boolean getNext(final int[] num, final int n, final int r) {
        int target = r - 1;
        num[target]++;

        if (num[target] > ((n - (r - target)) + 1)) {
            while (num[target] > ((n - (r - target)))) {
                target--;
                if (target < 0) {
                    break;
                }
            }
            if (target < 0) {
                return true;
            }
            num[target]++;

            for (int i = target + 1; i < num.length; i++) {
                num[i] = num[i - 1] + 1;
            }
        }
        return false;
    }
}
