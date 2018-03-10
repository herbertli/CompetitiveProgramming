import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static double[][] probs;
    static double p;

    static void fill() {
        for (int games = 0; games < probs.length; games++) {
            for (int maxStreak = games; maxStreak < probs[games].length; maxStreak++) {
                probs[games][maxStreak] = 1;
            }
        }

        for (int games = 1; games < probs.length; games++) {
            probs[games][games - 1] = 1 - Math.pow(p, games);
        }
    }

    // probability to have a streak of at most j games out of i total
    static double solve(int games, int maxStreak) {
        if (maxStreak >= games) return 1;
        if (maxStreak == 0) return Math.pow(1 - p, games);
        if (maxStreak == games - 1) return 1 - Math.pow(p, games);
        if (maxStreak < 0) return 0;
        if (probs[games][maxStreak] != 0) return probs[games][maxStreak];

        double ret = 0;

        ret += solve(games - 1, maxStreak);
        ret -= solve(games - maxStreak - 2, maxStreak) * (1 - p) * Math.pow(p, maxStreak + 1);

        probs[games][maxStreak] = ret;
        return ret;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String[] temp = in.readLine().split("\\s+");

            int n = Integer.parseInt(temp[0]);
            p = Double.parseDouble(temp[1]);
            if (n == 0) break;

            if (p == 1) {
                System.out.println(n);
                continue;
            }
            if (p == 0) {
                System.out.println(0);
                continue;
            }

            // probs[games played][max win streak]
            probs = new double[n + 1][n + 1];

            fill();
//            for (double[] d: probs) System.out.println(Arrays.toString(d));

            double ans = 0;
            for (int streak = 0; streak < probs[n].length; streak++) {
                ans += streak * (solve(n, streak) - solve(n, streak - 1));
            }
            System.out.println(ans);
        }

    }

}