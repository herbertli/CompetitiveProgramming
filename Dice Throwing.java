import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {

            String[] temp = in.readLine().split("\\s+");

            int n = Integer.parseInt(temp[0]);
            int x = Integer.parseInt(temp[1]);
            if (n == 0 && x == 0) break;

            if (x > 6 * n) {
                System.out.println(0);
                continue;
            }

            long[][] rolls = new long[n + 1][n * 6 + 1];

            for (int i = 1; i <= 6; i++) rolls[1][i] = 1;

            for (int dice = 2; dice <= n; dice++) {
                for (int prev = 1; prev < rolls[dice - 1].length; prev++) {
                    if (rolls[dice- 1][prev] != 0) {
                        for (int curr = 1; curr <= 6; curr++) {
                            rolls[dice][prev + curr] += rolls[dice- 1][prev];
                        }
                    }
                }
            }

//            for (long[] i: rolls) System.out.println(Arrays.toString(i));

            long num = 0;
            long denom = 0;
            for (int sum = 0; sum < rolls[n].length; sum++) {
                denom += rolls[n][sum];
                if (sum >= x) num += rolls[n][sum];
            }

//            System.out.println(num);
//            System.out.println(denom);

            long d = gcd(denom, num);
            num /= d;
            denom /= d;

            if (num == 1 && denom == 1) System.out.println(1);
            else System.out.printf("%d/%d\n", num, denom);
        }

    }

}