import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static long dfs(int p, int[] endings, TreeMap<Integer, Long> lookup, ArrayList<Integer>[] adj) {
        if (lookup.containsKey(p)) return lookup.get(p);
        if (adj[p].size() == 0) {
            return endings[p];
        }

        long count = 0;
        for (int i: adj[p]) {
            count += dfs(i, endings, lookup, adj);
        }

        lookup.put(p, count);
        return count;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long cases = Integer.parseInt(in.readLine());

        for (long c = 0; c < cases; c++) {

            int[] endings;
            ArrayList<Integer>[] adj = new ArrayList[401];
            TreeMap<Integer, Long> lookup = new TreeMap<>();

            long sections = Integer.parseInt(in.readLine());

            for (int i = 0; i < adj.length; i++) {
                adj[i] = new ArrayList<Integer>();
            }
            endings = new int[401];

            for (int s = 0; s < sections; s++) {


                String[] temp = in.readLine().split("\\s+");

                if (temp.length == 4) {
                    int name = Integer.parseInt(temp[0]);
                    for (int child = 1; child <= 3; child++) {
                        adj[name].add(Integer.parseInt(temp[child]));
                    }
                } else {
                    int name = Integer.parseInt(temp[0]);
                    if (temp[1].equals("favourably")) {
                        endings[name] = 1;
                    }
                }
            }

            System.out.println(dfs(1, endings, lookup, adj));
        }

    }

}
