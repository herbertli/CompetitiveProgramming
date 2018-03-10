import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] s = in.readLine().split("\\s+");
            int length = Integer.parseInt(s[0]);
            int stations = Integer.parseInt(s[1]);
            if (length == 0 && stations == 0) break;

            int[][] t = new int[stations][2];

            for (int st = 0; st < stations; st++) {
                String[] temp = in.readLine().split("\\s+");
                int center = Integer.parseInt(temp[0]);
                int radius = Integer.parseInt(temp[1]);
                t[st][0] = center - radius;
                t[st][1] = center + radius;
            }

            Arrays.sort(t, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] > o2[0]) return 1;
                    else if (o1[0] < o2[0]) return -1;
                    else return Integer.compare(o1[1], o2[1]);
                }
            });

//            for (int[] i: t) System.out.println(Arrays.toString(i));

            int chosen = 0;
            int end = 0;
            boolean isPossible = true;

            int i = 0;
            int tries = 0;
            while (i < t.length && end < length && tries < t.length) {
                int best = i;
                int bestRight = Integer.MIN_VALUE;
                while (best < t.length && t[best][0] <= end) {
                    int right = t[best][1];
                    bestRight = Math.max(right, bestRight);
                    best++;
                }
                chosen++;
                end = bestRight;
                i = best;
                tries++;
            }
//            System.out.println("End at: " + end);

//            if (end < length) {
//                if (!(t[t.length - 1][0] <= end && t[t.length - 1][1] >= length)) isPossible = false;
//                else chosen++;
//            }

            if (end >= length) System.out.println(stations - chosen);
            else System.out.println(-1);

        }

    }

}
