import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);

        long ans = 0;
        long[] a = new long[5];
        for (int i = 1; i <= n; i++) {
            a[i % 5]++;
        }
        long[] b = new long[5];
        for (int i = 1; i <= m; i++) {
            b[i % 5]++;
        }
        ans += a[0] * b[0];
        ans += a[1] * b[4];
        ans += a[2] * b[3];
        ans += a[3] * b[2];
        ans += a[4] * b[1];
        System.out.println(ans);
    }

}