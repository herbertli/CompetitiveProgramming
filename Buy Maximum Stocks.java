import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        String[] s = in.readLine().split("\\s+");

        TreeMap<Long, TreeSet<Integer>> h = new TreeMap<>();

        for (int i = 0; i < s.length; i++) {
            long l = Long.parseLong(s[i]);
            if (h.containsKey(l)) {
                h.get(l).add(i + 1);
            } else {
                h.put(l, new TreeSet<>());
                h.get(l).add(i + 1);
            }
        }

        long k = Long.parseLong(in.readLine());
        long bought = 0;

        for (long l: h.keySet()) {
            TreeSet<Integer> maxPoss = h.get(l);
            for (int i: maxPoss.descendingSet()) {
                long temp = Math.floorDiv(k, l);
                bought += Math.min(i, temp);
                k -= l * Math.min(i, temp);
            }
        }
        System.out.println(bought);

    }

}