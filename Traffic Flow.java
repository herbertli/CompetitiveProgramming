import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge {
        int dist;
        int u;
        int v;
    }

    private static int find(int[] parent, int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    private static void union(int[] parent, int p, int q) {
        int repP = find(parent, p);
        int repQ = find(parent, q);
        if (repP != repQ) {
            parent[repQ] = repP;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());
        for (int c = 1; c <= cases; c++) {
            String[] temp = in.readLine().split("\\s+");

            int intersections = Integer.parseInt(temp[0]);
            int edges = Integer.parseInt(temp[1]);

            PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return Double.compare(o2.dist, o1.dist);
                }
            });

            for (int i = 0; i < edges; i++) {
                temp = in.readLine().split("\\s+");
                Edge d = new Edge();
                d.dist = Integer.parseInt(temp[2]);
                d.u = Integer.parseInt(temp[0]);
                d.v = Integer.parseInt(temp[1]);
                pq.add(d);
                Edge e = new Edge();
                e.dist = Integer.parseInt(temp[2]);
                e.u = Integer.parseInt(temp[1]);
                e.v = Integer.parseInt(temp[0]);
                pq.add(e);
            }

            int[] parent = new int[intersections];
            long length = Long.MAX_VALUE;
            for (int i = 0; i < parent.length; i++) parent[i] = i;

            while (!pq.isEmpty()) {
                Edge d = pq.poll();
                if (find(parent, d.u) != find(parent, d.v)) {
                    length = Math.min(d.dist, length);
                    union(parent, d.u, d.v);
                }
            }
            if (length == Long.MAX_VALUE) length = 0;
            System.out.printf("Case #%d: %d\n", c, length);
        }
    }

}
