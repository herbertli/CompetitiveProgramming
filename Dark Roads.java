import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int to;
    int from;
    int cost;

    public Edge(int to, int from, int cost) {
        this.to = to;
        this.from = from;
        this.cost = cost;
    }
}

public class Main {

    public static int find(int[] p, int x) {
        return x == p[x] ? x : (p[x] = find(p, p[x]));
    }

    public static void union(int[] p, int a, int b) {
        a = find(p, a);
        b = find(p, b);
        if (a != b)
            p[a] = b;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] temp = in.readLine().split(" ");
            int n = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);

            if (n == 0 && m == 0) break;

            int[] parents = new int[n];
            for (int i = 0; i < parents.length; i++) parents[i] = i;
            long min = 0;
            PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return Integer.compare(o1.cost, o2.cost);
                }
            });

            long total  = 0;
            for (int i = 0; i < m; i++) {
                temp = in.readLine().split(" ");
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);
                int cost = Integer.parseInt(temp[2]);
                pq.add(new Edge(a, b, cost));
                total += cost;
            }

            while (!pq.isEmpty()) {
                Edge e = pq.poll();
                if (find(parents, e.from) != find(parents, e.to)) {
                    min += e.cost;
                    union(parents, e.from, e.to);
                }
            }

            System.out.println(total - min);

        }

    }

}
