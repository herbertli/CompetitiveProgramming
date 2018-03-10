import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

class Distance {
    int to;
    long dist;

    public Distance(int to, long dist) {
        this.to = to;
        this.dist = dist;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(in.readLine());

        for (int c = 1; c <= C; c++) {
            String[] temp = in.readLine().split(" ");
            int n = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);
            int S = Integer.parseInt(temp[2]);
            int T = Integer.parseInt(temp[3]);

            ArrayList<Edge>[] adj = new ArrayList[n];
            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE);

            for (int i = 0; i < m; i++) {
                temp = in.readLine().split(" ");
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);
                int cost = Integer.parseInt(temp[2]);
                adj[a].add(new Edge(b, cost));
                adj[b].add(new Edge(a, cost));
            }

            PriorityQueue<Distance> pq = new PriorityQueue<>(new Comparator<Distance>() {
                @Override
                public int compare(Distance o1, Distance o2) {
                    return Long.compare(o1.dist, o2.dist);
                }
            });

            pq.add(new Distance(S, 0));
            while (!pq.isEmpty()) {
                Distance to = pq.poll();
                dist[to.to] = to.dist;

                if (to.to == T) {
                    break;
                }

                for (Edge e: adj[to.to]) {
                    if (dist[e.to] == Long.MAX_VALUE) {
                        pq.add(new Distance(e.to, dist[to.to] + e.cost));
                    }
                }
            }

            if (dist[T] != Long.MAX_VALUE)
                System.out.printf("Case #%d: %d\n", c, dist[T]);
            else
                System.out.printf("Case #%d: unreachable\n", c);

        }

    }

}