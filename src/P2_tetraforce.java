import com.google.gson.*;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class P2_tetraforce {
    static String start = "{\"WEST\": \"eXxUbSM7fWY=\", \"EAST\": " +
            "\"eXxebSM7fWY=\", " +
            "\"NORTH\": \"eXwlbSM7LWY=\", \"SOUTH\": \"eXwlbSM7U2Y=\"}";

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Location m = gson.fromJson(start, Location.class);
        Map<Pair<Integer, Integer>, Location> map = new HashMap<>();
        map.put(new Pair<>(0, 0), m);
        Queue<Pair<Integer, Integer>> explore = new LinkedList<>();
        explore.add(new Pair<>(0, 0));
        while (explore.peek() != null) {
            Pair<Integer, Integer> c = explore.poll();
            Location l = map.get(c);
            if (l.NORTH != null) {
                String out = new Scanner(
                        new URL("https://techchallenge.withgoogle" +
                                        ".com/midrule/" + l.NORTH).openStream(),
                        "UTF-8").useDelimiter("\\A").next();
                Object r = map.put(new Pair<>(c.getKey(), c.getValue() + 1),
                                   gson.fromJson(out, Location.class));
                if (r == null) {
                    explore.add(new Pair<>(c.getKey(), c.getValue() + 1));
                }
            }
            if (l.SOUTH != null) {
                String out = new Scanner(
                        new URL("https://techchallenge.withgoogle" +
                                        ".com/midrule/" + l.SOUTH).openStream(),
                        "UTF-8").useDelimiter("\\A").next();
                Object r = map.put(new Pair<>(c.getKey(), c.getValue() - 1),
                                   gson.fromJson(out, Location.class));
                if (r == null) {
                    explore.add(new Pair<>(c.getKey(), c.getValue() - 1));
                }
            }
            if (l.EAST != null) {
                String out = new Scanner(
                        new URL("https://techchallenge.withgoogle" +
                                        ".com/midrule/" + l.EAST).openStream(),
                        "UTF-8").useDelimiter("\\A").next();
                Object r = map.put(new Pair<>(c.getKey() + 1, c.getValue()),
                                   gson.fromJson(out, Location.class));
                if (r == null) {
                    explore.add(new Pair<>(c.getKey() + 1, c.getValue()));
                }
            }
            if (l.WEST != null) {
                String out = new Scanner(
                        new URL("https://techchallenge.withgoogle" +
                                        ".com/midrule/" + l.WEST).openStream(),
                        "UTF-8").useDelimiter("\\A").next();
                Object r = map.put(new Pair<>(c.getKey() - 1, c.getValue()),
                                   gson.fromJson(out, Location.class));
                if (r == null) {
                    explore.add(new Pair<>(c.getKey() - 1, c.getValue()));
                }
            }
        }
        // Get corners
        Pair<Integer, Integer> topLeft = new Pair<>(0, 0);
        Pair<Integer, Integer> topRight = new Pair<>(0, 0);
        Pair<Integer, Integer> botLeft = new Pair<>(0, 0);
        Pair<Integer, Integer> botRight = new Pair<>(0, 0);
        for (Pair<Integer, Integer> p : map.keySet()) {
            if (p.getValue() > topLeft.getValue() || p.getKey() < topLeft.getKey()) {
                topLeft = p;
            }
            if (p.getValue() > topRight.getValue() || p.getKey() > topRight.getKey()) {
                topRight = p;
            }
            if (p.getValue() < botLeft.getValue() || p.getKey() < botLeft.getKey()) {
                botLeft = p;
            }
            if (p.getValue() < botRight.getValue() || p.getKey() > botRight.getKey()) {
                botRight = p;
            }
        }

        System.out.println(map.get(botLeft));
        System.out.println(map.get(botRight));
        System.out.println(map.get(topLeft));
        System.out.println(map.get(topRight));
    }

    public class Location {
        String NORTH;
        String SOUTH;
        String WEST;
        String EAST;
        String TETRAFORCE;

        public String toString() {
            return TETRAFORCE;
        }
    }
}
