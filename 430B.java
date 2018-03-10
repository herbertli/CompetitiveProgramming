import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split("\\s+");

        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        int x = Integer.parseInt(s[2]);

        s = in.readLine().split("\\s+");

        int[] balls = new int[n];
        for (int i = 0; i < n; i++)
            balls[i] = Integer.parseInt(s[i]);

        int[][] groups = new int[n][2];
        int numG = -1;
        int kG = -1;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (kG == -1) {
                kG = balls[i];
                numG = 1;
            } else if (balls[i] == kG) {
                numG++;
            } else {
                groups[j][0] = kG;
                groups[j][1] = numG;
                j++;
                kG = balls[i];
                numG = 1;
            }
        }
        groups[j][0] = kG;
        groups[j][1] = numG;

        int best = 0;
        for (int i = 0; i < groups.length; i++) {
            if (groups[i][0] == x && groups[i][1] == 2) {
                best = Math.max(solve(i, groups) + 2, best);
            }
        }
        System.out.println(best);
    }

    private static int solve(int i, int[][] groups) {
        int removed = 0;

        int left = i - 1;
        int right = i + 1;
        int iter = 0;

        while (iter < 100 && left >= 0 && right < groups.length) {
            int lkg = groups[left][0];
            int lng = groups[left][1];

            int rkg = groups[right][0];
            int rng = groups[right][1];
            if (lkg == rkg && rng + lng >= 3) {
                left--;
                right++;
                removed += rng + lng;
            }
            iter++;
        }

        return removed;
    }

}