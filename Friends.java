import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int find(int[] parent, int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    private static void union(int[] parent, int[] sizes, int p, int q) {
        int repP = find(parent, p);
        int repQ = find(parent, q);
        if (repP != repQ) {
            parent[repQ] = repP;
            sizes[repP] += sizes[repQ];
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {
            String[] s = in.readLine().split(" ");

            int people = Integer.parseInt(s[0]);
            int pairs = Integer.parseInt(s[1]);
            int[] relationships = new int[people + 1];
            int[] sizes = new int[people + 1];
            Arrays.fill(sizes, 1);
            for (int i = 0; i < relationships.length; i++) {
                relationships[i] = i;
            }

            for (int pair = 0; pair < pairs; pair++) {
                s = in.readLine().split(" ");
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                union(relationships, sizes, x, y);
            }
            int max = 0;
            for (int j: sizes) {
                if (j > max) {
                    max = j;
                }
            }

            System.out.println(max);

        }

        in.close();

    }

}
