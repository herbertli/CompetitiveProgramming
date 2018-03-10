import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] s = in.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]) - 1;
        int k = Integer.parseInt(s[2]);

        int[] prices = new int[n];
        s = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(s[i]);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (m - i >= 0) {
                if (prices[m - i] != 0 && prices[m - i] <= k) {
                    min = i;
                    break;
                }
            }
            if (m + i < n) {
                if (prices[m + i] != 0 && prices[m + i] <= k) {
                    min = i;
                    break;
                }
            }
        }
        System.out.println(min * 10);

    }

}