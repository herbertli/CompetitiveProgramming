import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for (int c = 0; c < T; c++) {

            int n = Integer.parseInt(in.readLine());
            int[] walls = new int[n];
            String[] temp = in.readLine().split(" ");
            int hi = 0;
            int low = 0;
            int prev = -1;
            int curr = -1;
            for (int i = 0; i < n; i++) {
                walls[i] = Integer.parseInt(temp[i]);
                if (i == 0) {
                    prev = walls[i];
                    continue;
                }
                curr = walls[i];
                if (curr > prev) hi++;
                if (curr < prev) low++;
                prev = curr;
            }
            System.out.printf("Case %d: %d %d\n", (c + 1), hi, low);
        }

    }

}