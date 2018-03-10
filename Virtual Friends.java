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

        for (int c = 0; c < cases; ++c) {

            HashMap<String, Integer> h = new HashMap<>();
            int f = Integer.parseInt(in.readLine());
            int i = 0;
            int[] relations = new int[200001];
            int[] sizes = new int[200001];

            for (int j = 0; j < relations.length; ++j) {
                relations[j] = j;
            }
            Arrays.fill(sizes, 1);

            for (int fn = 0; fn < f; fn++) {

                String[] s = in.readLine().split("\\s+");

                String p = s[0];
                String q = s[1];
                if (!h.containsKey(p)) {
                    h.put(p, i++);
                }

                if (!h.containsKey(q)) {
                    h.put(q, i++);
                }

                union(relations, sizes, h.get(p), h.get(q));
                System.out.println(sizes[find(relations, h.get(p))]);
            }

        }

    }

}
