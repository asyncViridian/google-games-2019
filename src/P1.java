import javafx.util.Pair;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P1 {
    static String input = "R R H 0\n" +
            "Y R V 1\n" +
            "Y Y V 0\n" +
            "G Y V 0\n" +
            "B G H 2\n" +
            "R B V 2\n" +
            "G B H 1\n" +
            "G R V 1\n" +
            "G R H 1\n" +
            "B G V 3\n" +
            "Y G V 4\n" +
            "Y Y V 5\n" +
            "G Y H 3\n" +
            "Y R H 4\n" +
            "R R V 4\n" +
            "R B H 4\n" +
            "B B H 4\n" +
            "Y B H 3\n" +
            "Y Y H 2\n" +
            "R R V 2\n" +
            "B R H 0\n" +
            "B B V 0\n" +
            "Y B V 3\n" +
            "Y R V 1\n" +
            "Y Y H 2\n" +
            "G G V 4\n" +
            "R B V 5\n" +
            "G G V 4\n" +
            "R G H 1\n" +
            "R Y H 1\n" +
            "G B H 3\n" +
            "G R H 3\n" +
            "G B H 0\n" +
            "B R V 2\n" +
            "Y G V 3\n" +
            "R Y V 4\n" +
            "Y B H 4\n" +
            "B Y V 4\n" +
            "Y Y V 3\n" +
            "G R H 0\n" +
            "G R H 0\n" +
            "R G V 1\n" +
            "G Y V 4\n" +
            "B B V 3\n" +
            "B R H 3\n" +
            "R B V 4\n" +
            "R Y H 1\n" +
            "G R V 3\n" +
            "G Y H 2\n" +
            "R Y H 4\n" +
            "B Y H 3\n" +
            "B G V 3\n" +
            "G B H 3\n" +
            "B G V 4\n" +
            "Y R V 5\n" +
            "B R H 4\n" +
            "Y Y V 4\n" +
            "G B V 3\n" +
            "Y G H 1\n" +
            "R R V 3\n" +
            "G B H 2\n" +
            "Y Y V 2\n" +
            "R B H 2\n" +
            "B R H 1\n" +
            "R G H 0\n" +
            "G R H 1\n" +
            "B B V 0\n" +
            "G Y V 1\n" +
            "Y G H 4\n" +
            "G Y V 5\n" +
            "B Y H 0\n" +
            "R R H 1\n" +
            "B R V 0\n" +
            "G G H 1\n" +
            "R G H 0\n" +
            "Y Y H 1\n" +
            "B B V 1\n" +
            "R G H 3\n" +
            "Y G V 2\n" +
            "G Y V 3\n" +
            "R Y H 1\n" +
            "B B V 0\n" +
            "B R H 3\n" +
            "Y G H 2\n" +
            "R B H 4\n" +
            "R B H 4\n" +
            "R R H 4\n" +
            "B R H 4\n" +
            "Y G H 2\n" +
            "G Y H 3\n" +
            "Y G V 3\n" +
            "B B V 4\n" +
            "G Y H 3\n" +
            "R Y V 2\n" +
            "B R H 4\n" +
            "G G V 3\n" +
            "B R H 4\n" +
            "B G V 4\n" +
            "B Y H 2\n" +
            "Y G H 0\n" +
            "R Y V 4\n" +
            "Y R V 0\n" +
            "R R V 0\n" +
            "B B V 5\n" +
            "G R H 3\n" +
            "B G H 2\n" +
            "G Y V 3\n" +
            "Y B V 5\n" +
            "Y G V 3\n" +
            "B G H 4\n" +
            "B Y H 4\n" +
            "R G H 3\n" +
            "Y G H 3\n" +
            "R Y V 2\n" +
            "Y R V 2\n" +
            "G B H 4\n" +
            "B R V 4\n" +
            "R B H 4\n" +
            "Y G H 3\n" +
            "R B H 4\n" +
            "Y G H 3\n" +
            "G R V 1\n" +
            "R B H 4\n" +
            "B G H 3\n" +
            "Y Y V 1\n" +
            "Y B H 4\n" +
            "R R V 5\n" +
            "G Y H 2\n" +
            "B R H 0\n" +
            "G Y H 1\n" +
            "G B V 1\n" +
            "G R H 2\n" +
            "B Y V 4\n" +
            "R Y V 2\n" +
            "G B V 5\n" +
            "R Y H 3\n" +
            "G B H 4\n" +
            "Y R V 3\n" +
            "R G V 3\n" +
            "B B V 5";

    public static void main(String[] args) {
        // null is blank
        // column-first l->r, top-first
        String[][] b = new String[6][12];
        String[] inputs = input.split("\n");
        BigInteger score = BigInteger.ZERO;
        for (int i = 0; i < inputs.length; i++) {
            // ADD INPUT
            b = add(b, inputs[i]);
            printBoard(b);
            // DROP
            while (canDrop(b)) {
                b = drop(b);
            }
            printBoard(b);
            // MERGE
            score = score.add(merge(b));
        }
        System.out.println(score);
    }

    static void printBoard(String[][] b) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print((b[j][i] == null ? "_" : b[j][i]) + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    static String[][] add(String[][] b, String s) {
        String[] chars = s.split(" ");
        if (chars[2].equals("H")) {
            // horizontal insert
            b[Integer.valueOf(chars[3])][0] = "" + chars[0];
            b[Integer.valueOf(chars[3]) + 1][0] = "" + chars[1];
        } else {
            // vertical answer
            b[Integer.valueOf(chars[3])][1] = "" + chars[0];
            b[Integer.valueOf(chars[3])][0] = "" + chars[1];
        }
        return b;
    }

    static boolean canDrop(String[][] b) {
        for (int i = 0; i < b.length; i++) {
            boolean block = false;
            for (int j = 0; j < b[i].length; j++) {
                if (b[i][j] != null) {
                    block = true;
                }
                if (block && b[i][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    static String[][] drop(String[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = b[i].length - 1; j > 0; j--) {
                if (b[i][j] == null) {
                    b[i][j] = b[i][j - 1];
                    b[i][j - 1] = null;
                }
            }
        }
        return b;
    }

    static BigInteger merge(String[][] b) {
        BigInteger score = BigInteger.ZERO;
        int C = 1;
        Map<Pair<Integer, Integer>, Set<Pair<Integer, Integer>>> mergePoints
                = canMerge(
                b);
        while (mergePoints.size() >= 1) {
            // do the merge
            int count=0;
            for (Pair<Integer, Integer> start : mergePoints.keySet()) {
                // remove blocks
                Set<Pair<Integer, Integer>> set = mergePoints.get(start);
                for (Pair<Integer, Integer> p : set) {
                    b[p.getKey()][p.getValue()] = null;
                }
                count += set.size();
            }
            // increment score
            BigInteger newScore = BigInteger.valueOf(
                    C * C * count * count);
            score = score.add(newScore);
            printBoard(b);
            // drop
            while (canDrop(b)) {
                b = drop(b);
            }
            printBoard(b);
            // get merge points
            mergePoints = canMerge(b);
            C++;
        }
        return score;
    }

    static Map<Pair<Integer, Integer>, Set<Pair<Integer, Integer>>> canMerge(
            String[][] b) {
        Map<Pair<Integer, Integer>, Set<Pair<Integer, Integer>>> result =
                new HashMap<>();
        for (int i = 0; i < b.length; i++) {
            for (int j = b[i].length - 1; j >= 0; j--) {
                String color = b[i][j];
                if (color == null) {
                    continue;
                }
                Set<Pair<Integer, Integer>> adjacent = new HashSet<>();
                Set<Pair<Integer, Integer>> toCheck = new HashSet<>();
                for (int di = -1; di <= 1; di++) {
                    toCheck.add(
                            new Pair<>(i + di, j));
                }
                for (int dj = -1; dj <= 1; dj++) {
                    toCheck.add(
                            new Pair<>(i, j + dj));
                }
                adjacent.add(new Pair<>(i, j));
                while (toCheck.size() > 0) {
                    Set<Pair<Integer, Integer>> newToCheck =
                            new HashSet<>();
                    for (Pair<Integer, Integer> point : toCheck) {
                        try {
                            if (!adjacent.contains(
                                    point) && b[point.getKey()][point.getValue()].equals(
                                    color)) {
                                adjacent.add(point);
                                for (int di = -1; di <= 1; di++) {
                                    if (!adjacent.contains(
                                            new Pair<>(point.getKey() + di,
                                                       point.getValue()))) {
                                        newToCheck.add(
                                                new Pair<>(
                                                        point.getKey() + di,
                                                        point.getValue()));
                                    }
                                }
                                for (int dj = -1; dj <= 1; dj++) {
                                    if (!adjacent.contains(
                                            new Pair<>(point.getKey(),
                                                       point.getValue() + dj))) {
                                        newToCheck.add(
                                                new Pair<>(
                                                        point.getKey(),
                                                        point.getValue() + dj));
                                    }
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                    toCheck = newToCheck;
                }
                if (adjacent.size() >= 4) {
                    boolean enter = true;
                    for (Pair<Integer, Integer> p : adjacent) {
                        if (result.containsKey(p)) {
                            enter = false;
                        }
                    }
                    if (enter) {
                        result.put(new Pair<>(i, j), adjacent);
                    }
                }
            }
        }
        return result;
    }
}
