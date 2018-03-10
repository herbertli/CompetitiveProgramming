import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int augment(ArrayList<Integer>[] adj, int[][] caps, int source, int sink) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] pred = new int[adj.length];
        Arrays.fill(pred, -1);
        int[] f = new int[adj.length];
        pred[source] = source;
        f[source] = Integer.MAX_VALUE;
        q.add(source);
        while (!q.isEmpty()) {
            int curr = q.poll();
            int currf = f[curr];
            if (curr == sink) {
                update(caps, pred, curr, f[curr]);
                return f[curr];
            }
            for (int i = 0; i < adj[curr].size(); i++) {
                int j = adj[curr].get(i);
                if (pred[j] != -1 || caps[curr][j] == 0) continue;
                pred[j] = curr;
                f[j] = Math.min(currf, caps[curr][j]);
                q.add(j);
            }
        }
        return 0;
    }

    static void update(int[][] caps, int[] pred, int curr, int f) {
        int p = pred[curr];
        if (p == curr) return;
        caps[p][curr] -= f;
        caps[curr][p] += f;
        update(caps, pred, p, f);
    }

    static int maxflow(ArrayList<Integer>[] adj, int[][] caps, int source, int sink) {
        int ret = 0;
        while (true) {
            int f = augment(adj, caps, source, sink);
            if (f == 0) break;
            ret += f;
        }
        return ret;
    }

    static TreeSet<Integer> minCutSet;
    static boolean[] visited;

    static void dfs(ArrayList<Integer>[] adj, int[][] caps, int curr) {
        minCutSet.add(curr);
        visited[curr] = true;
        for (int n: adj[curr]) {
            if (caps[curr][n] > 0 && !visited[n]) {
                dfs(adj, caps, n);
            }
        }
    }

    static void minCut(ArrayList<Integer>[] adj, int[][] caps) {
        minCutSet = new TreeSet<>();
        visited = new boolean[adj.length];
        dfs(adj, caps, 1);

        for (int i: minCutSet) {
            for (int n: adj[i]) {
                if (!minCutSet.contains(n) && caps[i][n] <= 0) {
                    System.out.printf("%d %d\n", i, n);
                }
            }
        }
        System.out.println();

    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String[] temp = in.readLine().split("\\s+");
            int n = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);

            if (n == 0 && m == 0) break;

            int[][] caps = new int[n + 2][n + 2];
            ArrayList<Integer>[] adj = new ArrayList[n + 2];
            for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                temp = in.readLine().split("\\s+");
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);
                int c = Integer.parseInt(temp[2]);
                caps[a][b] = c;
                adj[a].add(b);
                caps[b][a] = c;
                adj[b].add(a);
            }

            int f = maxflow(adj, caps, 1, 2);
//            for (int[] i : caps) System.out.println(Arrays.toString(i));
            minCut(adj, caps);

        }

    }

}
