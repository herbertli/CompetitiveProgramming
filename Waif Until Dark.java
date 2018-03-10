import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int t, rev, cap, f;

        public Edge(int t, int rev, int cap) {
            this.t = t;
            this.rev = rev;
            this.cap = cap;
        }
    }

    static List<Edge>[] createGraph(int nodes) {
        graph = new List[nodes];
        for (int i = 0; i < nodes; i++)
            graph[i] = new ArrayList<>();
        return graph;
    }

    static void addEdge(int s, int t, int cap) {
        graph[s].add(new Edge(t, graph[t].size(), cap));
        graph[t].add(new Edge(s, graph[s].size() - 1, 0));
    }

    static boolean dinicBfs(int src, int dest, int[] dist) {
        Arrays.fill(dist, -1);
        dist[src] = 0;
        int[] Q = new int[graph.length];
        int sizeQ = 0;
        Q[sizeQ++] = src;
        for (int i = 0; i < sizeQ; i++) {
            int u = Q[i];
            for (Edge e : graph[u]) {
                if (dist[e.t] < 0 && e.f < e.cap) {
                    dist[e.t] = dist[u] + 1;
                    Q[sizeQ++] = e.t;
                }
            }
        }
        return dist[dest] >= 0;
    }

    static int dinicDfs(int[] ptr, int[] dist, int dest, int u, int f) {
        if (u == dest)
            return f;
        for (; ptr[u] < graph[u].size(); ++ptr[u]) {
            Edge e = graph[u].get(ptr[u]);
            if (dist[e.t] == dist[u] + 1 && e.f < e.cap) {
                int df = dinicDfs(ptr, dist, dest, e.t, Math.min(f, e.cap - e.f));
                if (df > 0) {
                    e.f += df;
                    graph[e.t].get(e.rev).f -= df;
                    return df;
                }
            }
        }
        return 0;
    }

    public static int maxFlow(int src, int dest) {
        int flow = 0;
        int[] dist = new int[graph.length];
        while (dinicBfs(src, dest, dist)) {
            int[] ptr = new int[graph.length];
            while (true) {
                int df = dinicDfs(ptr, dist, dest, src, Integer.MAX_VALUE);
                if (df == 0)
                    break;
                flow += df;
            }
        }
        return flow;
    }

    static List<Edge>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int m = Integer.parseInt(s[1]);
        final int p = Integer.parseInt(s[2]);

        final int numNodes = 2 + n + m + p;
        final int source = 0;
        final int dest = numNodes - 1;

        createGraph(numNodes);

        // source to kid and kid to toy
        for (int i = 1; i <= n; i++) {
            s = in.readLine().split(" ");
            int numToys = Integer.parseInt(s[0]);
            addEdge(source, i, 1);
            for (int j = 0; j < numToys; j++) {
                int t = Integer.parseInt(s[j + 1]);
                int ti = n + t;
                addEdge(i, ti, 1);
            }
        }

        TreeSet<Integer> used = new TreeSet<>();
        //toy to toy group to dest
        for (int i = 1; i <= p; i++) {
            s = in.readLine().split(" ");
            int numToys = Integer.parseInt(s[0]);
            int most = Integer.parseInt(s[s.length - 1]);
            int groupId = i + n + m;
            for (int j = 0; j < numToys; j++) {
                int toyId = Integer.parseInt(s[j + 1]) + n;
                used.add(Integer.parseInt(s[j + 1]));
                addEdge(toyId, groupId, 1);
            }
            addEdge(groupId, dest, most);
        }

        for (int i = 1; i <= m; i++) {
            if (!used.contains(i)) {
                addEdge(i + n, dest, 1);
            }
        }

        System.out.println(maxFlow(source, dest));
    }
}