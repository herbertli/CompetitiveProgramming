import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int c = 1;
        while (true) {

            String[] temp = in.readLine().split("\\s+");

            int L = Integer.parseInt(temp[0]);
            int U = Integer.parseInt(temp[1]);
            int R = Integer.parseInt(temp[2]);

            if (L == 0 && U == 0 && R == 0) break;

            int[] dp = new int[10000];
            Arrays.fill(dp, Integer.MAX_VALUE);
            int[] locks = new int[R];

            temp = in.readLine().split("\\s+");
            for (int i = 0 ; i < R; i++) locks[i] = Integer.parseInt(temp[i]);

            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.push(L);
            dp[L] = 0;

            while (!q.isEmpty()) {
                int curr = q.poll();
                if (curr == U) break;
                for (int i: locks) {
                    int next = (curr + i) % 10000;
                    if (dp[curr] + 1 < dp[next]) {
                        q.add(next);
                        dp[next] = dp[curr] + 1;
                    }
                }
            }

            if (dp[U] != Integer.MAX_VALUE) {
                System.out.printf("Case %d: %d\n", c++, dp[U]);
            } else {
                System.out.printf("Case %d: Permanently Locked\n", c++);
            }
        }
    }
}
