import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static boolean isPossible(int[] ladder, int strength) {
        int curr = strength;
        int start = 0;
        for (int i: ladder) {
            if (i - start > curr) return false;
            else if (i - start == curr) --curr;
            start = i;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());
        for (int c = 1; c <= cases; c++) {

            int rungs = Integer.parseInt(in.readLine());

            int[] ladder = new int[rungs];

            String[] s = in.readLine().split(" ");

            for (int i = 0; i < s.length; i++) {
                ladder[i] = Integer.parseInt(s[i]);
            }

            int low = 0;
            int high = 10000000;

            while (low <= high) {
                int mid = (low + high) / 2;
                if (isPossible(ladder, mid)) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            System.out.printf("Case %d: %d\n", c, low);


        }


    }

}
