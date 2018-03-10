import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class D {

    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] s = in.readLine().split("\\s+");

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        TreeMap<String, Integer> map = new TreeMap<>();

        s = in.readLine().split("\\s+");

        map.put("English", 0);
        for (int i = 1; i <= n; i++) {
            map.put(s[i - 1], i);
        }

        ArrayList<Edge>[] adj = new ArrayList[n + 1];
        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            s = in.readLine().split("\\s+");
            int a = map.get(s[0]);
            int b = map.get(s[1]);
            int cost = Integer.parseInt(s[2]);
            adj[a].add(new Edge(b, cost));
            adj[b].add(new Edge(a, cost));
        }

        PriorityQueue<Edge> q = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });

        int total = 0;
        TreeSet<Integer> seen = new TreeSet<>();
        q.add(new Edge(0, 0));
        while (true) {
            PriorityQueue<Edge> incoming = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return Integer.compare(o1.cost, o2.cost);
                }
            });

            while (!q.isEmpty()) {
                Edge e = q.poll();
                int to = e.to;
                int cost = e.cost;

                if (!seen.contains(to)) {
                    seen.add(to);
                    total += cost;

                    for (Edge c: adj[to]) {
                        if (!seen.contains(c.to)) incoming.add(c);
                    }
                }
            }

            if (incoming.size() == 0 || seen.size() == n + 1) break;
            else q = incoming;
        }

        System.out.println((seen.size() == n + 1) ? total : "Impossible");

    }

}