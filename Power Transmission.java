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

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {

            int N = in.nextInt();

            ArrayList<Integer>[] adj = new ArrayList[2 * N + 2];
            Arrays.fill(adj, new ArrayList<>());

            int[][] caps = new int[2 * N + 2][2 * N + 2];

            for (int i = 1; i <= N; i++) {
                adj[i].add(i + N);
                caps[i][i + N] = in.nextInt();
                caps[i + N][i] = caps[i][i + N];
                adj[i + N].add(i);
            }

            int links = in.nextInt();
            for (int l = 0; l < links; l++) {
                int i = in.nextInt();
                int j = in.nextInt();
                int c = in.nextInt();
                caps[i + N][j] = c;
                adj[i + N].add(j);
            }

            int sources = in.nextInt();
            int sinks = in.nextInt();

            for (int i = 0; i < sources + sinks; i++) {
                int n = in.nextInt();
                if (i < sources) {
                    adj[0].add(n);
                    caps[0][n] = Integer.MAX_VALUE;
                } else {
                    adj[n + N].add(2 * N + 1);
                    caps[n + N][2 * N + 1] = Integer.MAX_VALUE;
                }
            }

            System.out.println(maxflow(adj, caps, 0, 2 * N + 1));

        }

    }

}
