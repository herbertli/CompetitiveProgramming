import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {

            int total = 0;
            String[] temp = in.readLine().split("\\s+");

            int[] bags = new int[temp.length + 1];
            for (int i = 1; i < bags.length; i++) {
                bags[i] = Integer.parseInt(temp[i - 1]);
                total += bags[i];
            }

            boolean[][] dp = new boolean[bags.length][201];

            dp[0][0] = true;

            for (int i = 1; i < bags.length; i++) {
                for (int cap = 0; cap < dp[i - 1].length; cap++) {
                    if (dp[i - 1][cap]) {
                        dp[i][cap] = true;
                        dp[i][cap + bags[i]] = true;
                    }
                }
            }

            if (total % 2 == 1 || !dp[bags.length - 1][total / 2]) System.out.println("NO");
            else System.out.println("YES");
        }

    }

}
