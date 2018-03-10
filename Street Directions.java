import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dfs_num;
    static int[] dfs_low;

    static int[] parent;

    static int[] status;
    static final int STARTED = 1;
    static final int FINISHED = 2;
    static final int UNSTARTED = 0;

    static ArrayList<Integer>[] adj;

    static void dfs(int u, int counter) {
        status[u] = STARTED;
        dfs_num[u] = counter;
        dfs_low[u] = counter;
        for (int j : adj[u]) {
            if (status[j] != FINISHED && parent[u] != j)
                System.out.println(u + " " + j);
            if (status[j] == UNSTARTED) {
                parent[j] = u;
                dfs(j, counter + 1);
                if (dfs_low[j] > dfs_num[u]) {
                    System.out.println(j + " " + u);
                }
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[j]);
            }
            else if (j != parent[u])
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[j]);
        }
        status[u] = FINISHED;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int c = 1;
        while (true) {

            String[] s = in.readLine().split("\\s+");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            if (n == 0 && m == 0) break;

            adj = new ArrayList[n + 1];
            for (int i = 0; i < adj.length; i++)
                adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                String[] temp = in.readLine().split("\\s+");
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);
                adj[a].add(b);
                adj[b].add(a);
            }

            dfs_num = new int[adj.length];
            dfs_low = new int[adj.length];
            status = new int[adj.length];
            parent = new int[adj.length];

            System.out.println(c);
            System.out.println();
            dfs(1, 0);

            System.out.println("#");
            c++;
        }

    }
}
