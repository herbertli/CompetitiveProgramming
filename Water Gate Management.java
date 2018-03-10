import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        int maxFlow = 0;
        int[][] dams = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] temp = in.readLine().split("\\s+");
            int flow = Integer.parseInt(temp[0]);
            int cost = Integer.parseInt(temp[1]);
            dams[i][0] = cost;
            dams[i][1] = flow;
            maxFlow += flow;

        }

        Comparator<int[]> damCompare = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        };

        java.util.Arrays.sort(dams, damCompare);

        int cases = Integer.parseInt(in.readLine());
        for (int c = 1; c <= cases; c++) {
            String[] temp = in.readLine().split("\\s+");
            int volumeNeeded = Integer.parseInt(temp[0]);
            int hours = Integer.parseInt(temp[1]);

            if (maxFlow * hours < volumeNeeded) {
                System.out.println("Case " + c + ": IMPOSSIBLE");
            } else {
                int minCost = Integer.MAX_VALUE;

                for (int i = 1; i <= n; i++) {

                    int start = (0x1 << i) - 1;
                    int limit = (0x1 << n) - 1;

                    while (start <= limit) {

                        int currCost = 0;
                        int currFlow = 0;

                        for (int j = 0; j < n; j++) {

                            if ((start & (0x1 << j)) > 0) {
                                currCost += dams[j][0];
                                currFlow += dams[j][1];
                            }
                        }

                        if (currFlow * hours >= volumeNeeded) {
                            minCost = (currCost < minCost) ? currCost : minCost;
                        }

                        int y = start & -start;
                        int c1 = start + y;
                        start = (((start ^ c1) >>> 2) / y) | c1;
                    }
                }
                System.out.println("Case " + c + ": " + minCost);
            }
        }
    }
}