import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());

        for (int c = 1; c <= cases; c++) {
            int res = Integer.MAX_VALUE;

            String[] temp = in.readLine().split("\\s+");
            int m = Integer.parseInt(temp[0]);
            int n = Integer.parseInt(temp[1]);

            HashMap<Integer, Integer> factorsM = new HashMap<>();

            for (int d = 2; d * d <= m; d++) {
                if (m % d == 0) {
                    int a = 0;
                    while (m % d == 0) {
                        a++;
                        m /= d;
                    }
                    factorsM.put(d, a);
                }
            }
            if (m > 1) {
                factorsM.put(m, 1);
            }

            HashMap<Integer, Integer> factorsN = new HashMap<>();

            for (int i = 2; i <= n; i++) {
                int t = i;
                for (int d = 2; d * d <= t; d++) {
                    if (t % d == 0) {
                        int a = 0;
                        while (t % d == 0) {
                            a++;
                            t /= d;
                        }
                        if (!factorsN.containsKey(d)) factorsN.put(d, a);
                        else factorsN.put(d, a + factorsN.get(d));
                    }
                }
                if (t > 1) {
                    if (!factorsN.containsKey(t)) factorsN.put(t, 1);
                    else factorsN.put(t, 1 + factorsN.get(t));
                }
            }

            for (int d: factorsM.keySet()) {
                if (factorsN.containsKey(d)) {
                    res = Math.min(factorsN.get(d) / factorsM.get(d), res);
                } else {
                    res = Integer.MAX_VALUE;
                    break;
                }
            }

            System.out.printf("Case %d:\n", c);
            if (res == Integer.MAX_VALUE) System.out.println("Impossible to divide");
            else System.out.println(res);
        }

    }

}