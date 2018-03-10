import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long[] dp;

    static long solve(int n) {
        if (n <= 0) return 0;
        if (dp[n] != 0) return dp[n];

        long res = 0;
        res += solve(n - 1) * (n - 1) + solve(n - 2) * (n - 1);
        dp[n] = res;

        return dp[n];
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        long[] fac = new long[13];
        fac[0] = 1;
        for (int i = 1; i < fac.length; i++) fac[i] = fac[i - 1] * i;

        dp = new long[13];
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 9;
        solve(12);

        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {

            int n = Integer.parseInt(in.readLine());

            System.out.printf("%d/%d\n", dp[n], fac[n]);
        }

    }

}