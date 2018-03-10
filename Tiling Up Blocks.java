import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[][] blocks;
    private static int[][] lookup;

    private static int countNumber(int currL, int currM) {
        if (lookup[currL][currM] != -1) return lookup[currL][currM];
        if (currL <= 0 || currM <= 0) {
            lookup[currL][currM] = 0;
            return 0;
        }
        int max = blocks[currL][currM] + Math.max(countNumber(currL - 1, currM), countNumber(currL, currM - 1));
        lookup[currL][currM] = max;
        return max;
    }

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        while (true) {

            int n = in.nextInt();
            if (n == 0) break;

            blocks = new int[101][101];
            lookup = new int[101][101];
            for (int[] i : lookup) Arrays.fill(i, -1);

            for (int i = 0; i < n; i++) {
                int l = in.nextInt();
                int m = in.nextInt();
                blocks[l][m]++;
            }

            System.out.println(countNumber(100, 100));
        }
        System.out.println("*");
    }

}
