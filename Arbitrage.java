import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // floyd-warshall with the addition constraint of having the number of intermediate vertices as small as possible
    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String s;

        while ((s = in.readLine()) != null) {

            int n = Integer.parseInt(s.trim());

            double[][] conv = new double[n][n];

            for (int i = 0; i < n; i++) {
                String[] temp = in.readLine().split("\\s+");
                int p = 0;
                for (int j = 0; j < n; j++) {
                    if (i == j) conv[i][j] = 1;
                    else conv[i][j] = Double.parseDouble(temp[p++]);
                }
            }

            double[][][] res = new double[n][n][n];
            int[][][] parent = new int[n][n][n];
            for (int[][] j: parent)
                for (int[] i : j)
                    Arrays.fill(i, -1);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    res[0][i][j] = conv[i][j];
                    parent[0][i][j] = i;
                }
            }

            for (int steps = 1; steps < n; steps++) {
                for (int k = 0; k < n; k++) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (res[steps - 1][i][k] * conv[k][j] > res[steps][i][j]) {
                                res[steps][i][j] = res[steps - 1][i][k] * conv[k][j];
                                parent[steps][i][j] = k;
                            }
                        }
                    }
                }
            }

            int minstart = -1;
            int mininter = Integer.MAX_VALUE;
            for (int start = 0; start < n; start++) {
                for (int inter = 1; inter < n; inter++) {
                    if (res[inter][start][start] > 1.01 && inter < mininter) {
                        minstart = start;
                        mininter = inter;
                    }
                }
            }

//            System.out.println(minstart);
//            System.out.println(mininter);
            if (mininter == Integer.MAX_VALUE) {
                System.out.println("no arbitrage sequence exists");
            }
            else {
                int curr = minstart;
                String output = "" + (curr + 1);
                while (mininter >= 0) {
                    curr = parent[mininter][minstart][curr];
                    output = (curr + 1) + " " + output;
                    mininter--;
                }
                System.out.println(output);
            }
        }
    }
}
