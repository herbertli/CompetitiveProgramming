import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Distance {
    double dist;
    int u;
    int v;
}

public class Main {

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
        for (int c = 0; c < cases; c++) {
            in.readLine();

            int ipoints = Integer.parseInt(in.readLine());

            double[][] points = new double[ipoints][2];

            for (int i = 0; i < ipoints; i++) {
                String[] temp = in.readLine().split("\\s+");
                points[i][0] = Double.parseDouble(temp[0]);
                points[i][1] = Double.parseDouble(temp[1]);
            }

            PriorityQueue<Distance> pq = new PriorityQueue<>(new Comparator<Distance>() {
                @Override
                public int compare(Distance o1, Distance o2) {
                    return Double.compare(o1.dist, o2.dist);
                }
            });

            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < points.length; j++) {
                    double dist = Math.pow(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2), .5);
                    Distance d = new Distance();
                    d.dist = dist;
                    d.u = i;
                    d.v = j;
                    pq.add(d);
                }
            }

            int[] parent = new int[ipoints];
            double length = 0;
            for (int i = 0; i < parent.length; i++) parent[i] = i;

            while (!pq.isEmpty()) {
                Distance d = pq.poll();
                if (find(parent, d.u) != find(parent, d.v)) {
                    length += d.dist;
                    union(parent, d.u, d.v);
                }
            }

            System.out.printf("%.2f\n", length);
            if (c < cases - 1) System.out.println();
        }
    }

}
