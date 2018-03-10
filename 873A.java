import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        int x = Integer.parseInt(s[2]);

        int[] times = new int[n];
        s = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(s[i]);
        }

        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (times[i] > x) {
                times[i] = x;
                count++;
            }
            if (count == k) break;
        }

        int total = 0;
        for (int i : times) total += i;
        System.out.println(total);

    }

}