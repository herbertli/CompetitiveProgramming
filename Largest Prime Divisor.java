import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            long n = Long.parseLong(in.readLine());
            if (n == 0) break;

            int found = 0;
            n = Math.abs(n);

            TreeSet<Long> factors = new TreeSet<>();

            for (long d = 2; d * d <= n; d++) {
                if (n % d == 0) {
                    while (n % d == 0) {
                        n /= d;
                    }
                    found++;
                    factors.add(d);
                }
            }

            if (n > 1) {
                found++;
                factors.add(n);
            }

            if (found < 2) {
                System.out.println(-1);
            } else {
                System.out.println(factors.last());
            }
        }
    }
}