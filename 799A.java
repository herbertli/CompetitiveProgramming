import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int t = Integer.parseInt(temp[1]);
        int k = Integer.parseInt(temp[2]);
        int d = Integer.parseInt(temp[3]);

        int total = (int) (Math.ceil((n + 0.0) / k) * t);

        int t1 = 0;
        int t2 = d;
        int curr = 0;
        int made = 0;

        while (made < n) {
            if (curr >= d && curr >= t2) {
                t2 += t;
                made += k;
            }
            if (curr >= t1) {
                t1 += t;
                made += k;
            }
            curr++;
        }

        if (Math.max(t1, t2) < total) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

}