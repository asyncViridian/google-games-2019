import java.util.ArrayList;
import java.util.List;

public class P2_nonary {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            list.add(i);
        }

        int cnt = 0;
        List<List<List<Integer>>> parts = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            List<List<List<Integer>>> ret = helper(list, i);
            parts.addAll(ret);
        }
        System.out.println("Number of partitions: " + parts.size());
        // each group must have at least 3
        parts.removeIf(l -> {
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i).size() < 3) {
                    return true;
                }
            }
            return false;
        });
        // each group must have at most 5
        parts.removeIf(l -> {
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i).size() > 5) {
                    return true;
                }
            }
            return false;
        });
        // no two groups can enter the same door
        parts.removeIf(l -> {
            int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (int i = 0; i < l.size(); i++) {
                List<Integer> group = l.get(i);
                int sum = 0;
                for (int j = 0; j < group.size(); j++) {
                    sum += group.get(j);
                }
                while (sum > 9) {
                    int newsum = 0;
                    while (sum / 10 != 0) {
                        newsum += sum % 10;
                        sum = sum / 10;
                    }
                    sum = newsum + sum;
                }
                count[sum - 1]++;
                if (count[sum - 1] > 1) {
                    return true;
                }
            }
            return false;
        });
        System.out.println(parts);
        System.out.println(parts.size());
    }

    // partition f(n, m)
    private static List<List<List<Integer>>> helper(List<Integer> ori, int m) {
        List<List<List<Integer>>> ret = new ArrayList<>();
        if (ori.size() < m || m < 1) return ret;

        if (m == 1) {
            List<List<Integer>> partition = new ArrayList<>();
            partition.add(new ArrayList<>(ori));
            ret.add(partition);
            return ret;
        }

        // f(n-1, m)
        List<List<List<Integer>>> prev1 = helper(ori.subList(0, ori.size() - 1),
                                                 m);
        for (int i = 0; i < prev1.size(); i++) {
            for (int j = 0; j < prev1.get(i).size(); j++) {
                // Deep copy from prev1.get(i) to l
                List<List<Integer>> l = new ArrayList<>();
                for (List<Integer> inner : prev1.get(i)) {
                    l.add(new ArrayList<>(inner));
                }

                l.get(j).add(ori.get(ori.size() - 1));
                ret.add(l);
            }
        }

        List<Integer> set = new ArrayList<>();
        set.add(ori.get(ori.size() - 1));
        // f(n-1, m-1)
        List<List<List<Integer>>> prev2 = helper(ori.subList(0, ori.size() - 1),
                                                 m - 1);
        for (int i = 0; i < prev2.size(); i++) {
            List<List<Integer>> l = new ArrayList<>(prev2.get(i));
            l.add(set);
            ret.add(l);
        }

        return ret;
    }
}
