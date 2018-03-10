import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static void topSort(ArrayList<Integer>[] adj) {
        Queue<Integer> q = new LinkedList<>();

        int[] incoming = new int[adj.length];
        TreeSet<Integer> finished = new TreeSet<>();
        int[] order = new int[adj.length - 1];

        for (int i = 1; i < adj.length; i++) {
            for (int j : adj[i]) {
                incoming[j]++;
            }
        }

        for (int i = 1; i < incoming.length; i++) {
            if (incoming[i] == 0) q.add(i);
        }

        int processed = 0;
        while (!q.isEmpty()) {
            int i = q.remove();
            finished.add(i);
            order[processed] = i;
            processed++;
            for (int j: adj[i]) {
                incoming[j]--;
                if (incoming[j] == 0 && !finished.contains(j)) q.add(j);
            }
        }

        if (processed != adj.length - 1) System.out.println("IMPOSSIBLE");
        else {
            for (int i: order) System.out.println(i);
        }

    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String[] s = in.readLine().split("\\s+");

            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            if (n == 0 && m == 0) break;

            ArrayList<Integer>[] adj = new ArrayList[n + 1];
            for (int i = 1; i < adj.length; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                String[] temp = in.readLine().split("\\s+");
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);
                adj[a].add(b);
            }

            topSort(adj);
        }

    }

}
