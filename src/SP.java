import javafx.util.Pair;

import java.awt.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SP {
    public static void main(String[] args) {
        System.out.print(p10());
    }

    public static int p2() {
        int[] a = {
                -5, -19, 0, -20, -11, 12, 27, -16, -2, -2, 23, 0, -3, 4, 7,
                -1, -28,
                18, 21, 17, -23, 9, 2, -19, 8};
        int max = 0;
        for (int i = 0; i < a.length - 5; i++) {
            int sum = 0;
            for (int j = 0; j < 5; j++) {
                sum += a[i + j];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    public static int p4() {
        int[] a = {3, 2, 4, 6, 3, 4, 5, 8, 6, 7, 9, 6, 3, 6, 8, 4, 2, 2, 4, 6
                , 2, 1, 3, 4, 5, 6, 7, 4, 5, 7, 4, 5, 3, 2, 5, 6, 3, 7, 8, 3,
                2, 9, 0, 4, 3, 6, 8, 9};
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (!m.containsKey(a[i])) {
                m.put(a[i], 0);
            }
            m.put(a[i], 1 + m.get(a[i]));
        }
        int count = 0;
        for (Integer k : m.keySet()) {
            if (m.get(k) % 2 == 1) {
                count++;
            }
        }
        return count;
    }

    public static String p6() {
        String input = "ALFIHKRVHRMWRXZGVZURHHSLIGLUDZGVI";
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char l = (char) ('A' + (25 - (input.charAt(i) - 'A')));
            output += l;
        }
        return output;
    }

    public static int p8() {
        Set<BigInteger> num = new HashSet<>();
        for (int i = 1; i <= 224; i++) {
            for (int j = 1; j <= 16; j++) {
                BigInteger s = BigInteger.valueOf(i);
                for (int a = 0; a < j; a++) {
                    s = s.multiply(BigInteger.valueOf(i));
                }
                num.add(s);
            }
        }
        num.removeIf(bi -> bi.compareTo(BigInteger.valueOf(50000)) > 0);
        num.removeIf(bi -> bi.compareTo(BigInteger.valueOf(5000)) < 0);
        return num.size();
    }

    public static Set<Point> p10() {
        Point A = new Point(-853388, -797447);
        Point B = new Point(-442839, 721091);
        Point C = new Point(-406140, 987734);
        Point D = new Point(-55842, -980970);
        Point E = new Point(-28064, -960562);
        Point F = new Point(240773, -871287);
        Point G = new Point(273637, 851940);
        Point H = new Point(320461, 997514);
        Point I = new Point(495045, -667013);
        Point J = new Point(757135, -861866);
        Point K = new Point(1148386, -439206);
        Point L = new Point(1220903, 239470);
        Point[] a = {A, B, C, D, E, F, G, H, I, J, K, L};
        Map<Pair<Point, Point>, Double> dist = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                dist.put(new Pair<>(a[i], a[j]), a[i].distance(a[j]));
            }
        }

        return null;
    }
}
