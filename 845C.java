import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        int[][] shows = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] temp = in.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            shows[i] = new int[]{a, b};
        }

        Arrays.sort(shows, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int tv1e = -1;
        int tv2e = -1;

        boolean isPossible = true;
        for (int[] show: shows) {
            int start = show[0];
            int end = show[1];
            if (start > tv1e) {
                tv1e = end;
            } else if (start > tv2e) {
                tv2e = end;
            } else {
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

}