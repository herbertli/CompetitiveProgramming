import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static int[] dp;
    static int[] coins;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        coins = lineToIntArray(br.readLine());

        int upper = coins[n - 1] + coins[n - 2] + 1;
        dp = new int[upper];
        int[] ways = new int[upper];
        Arrays.fill(ways, Integer.MAX_VALUE);

        ways[0] = 0;
        for (int i = 0; i < ways.length; i++) {
            for (int c: coins) {
                if (i + c < ways.length) {
                    ways[i + c] = Math.min(ways[i + c], ways[i] + 1);
                }
            }
        }

        boolean canonical = true;
        for(int x = upper - 1; x > 0; x--){
            if(ways[x] < greedy(x)){
                canonical = false;
                break;
            }
        }
        System.out.println(canonical ? "canonical" : "non-canonical");

    }

    static int greedy(int x){
        int ways = 0;
        int i = coins.length - 1;
        while (x != 0){
            if (x < coins[i]) i--;
            else {
                int most = x / coins[i];
                x -= (coins[i] * most);
                ways += most;
            }
        }
        return ways;
    }

    static int[] lineToIntArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        int[] res = new int[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Integer.parseInt(token[i]);
        }
        return res;
    }


}