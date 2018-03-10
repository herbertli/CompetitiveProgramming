import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String[] s = in.readLine().split("\\s+");
            int nheads = Integer.parseInt(s[0]);
            int nknights = Integer.parseInt(s[1]);

            if (nheads == 0 && nknights == 0) break;

            int[] heads = new int[nheads];
            int[] knights = new int[nknights];

            for (int i = 0; i < nheads; i++) {
                heads[i] = Integer.parseInt(in.readLine());
            }
            Arrays.sort(heads);

            for (int i = 0; i < nknights; i++) {
                knights[i] = Integer.parseInt(in.readLine());
            }
            Arrays.sort(knights);

            int cost = 0;
            int k = 0;
            int h = 0;
            while (h < nheads && k < nknights) {
                if (heads[h] <= knights[k]) {
                    cost += knights[k];
                    h++;
                    k++;
                } else if (heads[h] > knights[k]) {
                    k++;
                }
            }

            if (h < nheads) {
                System.out.println("Loowater is doomed!");
            } else {
                System.out.println(cost);
            }

        }

    }
}
