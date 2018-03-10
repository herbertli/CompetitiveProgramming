import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Line {
        Point p1, p2;

        Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    // taken from http://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
    static boolean onSegment(Point p, Point q, Point r) {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;

        return false;
    }

    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;  // colinear
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and p2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
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

        for (int c = 0; c < cases; c++) {
            in.readLine();

            int n = Integer.parseInt(in.readLine().trim());

            ArrayList<Line> straws = new ArrayList<>();
            int[] parents = new int[n];
            for (int i = 0; i < parents.length; i++) parents[i] = i;

            for (int i = 0; i < n; i++) {
                String[] temp = in.readLine().split("\\s+");
                Point a = new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                Point b = new Point(Integer.parseInt(temp[2]), Integer.parseInt(temp[3]));
                straws.add(new Line(a, b));
            }

            for (int i = 0; i < straws.size(); i++) {
                for (int j = 0; j < straws.size(); j++) {
                    if (i == j) continue;
                    Line s = straws.get(i);
                    Line t = straws.get(j);
                    if (doIntersect(s.p1, s.p2, t.p1, t.p2)) union(parents, i, j);
                }
            }

            while (true) {
                String[] temp = in.readLine().split("\\s+");
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);
                if (a == 0 && b == 0) break;

                if (find(parents, a - 1) == find(parents, b - 1)) {
                    System.out.println("CONNECTED");
                } else {
                    System.out.println("NOT CONNECTED");
                }
            }

            if (c != cases - 1) System.out.println();
        }
    }
}