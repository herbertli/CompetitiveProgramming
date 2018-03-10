import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int INF = 1000000000;

    static class Point {
        int x, y;
        int dist;
        int value;

        public Point(int x, int y, int dist, int value) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            s = in.readLine().split("");
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(s[j]);
            }
        }

        int[][] dist = new int[n][m];
        for (int[] i: dist) Arrays.fill(i, INF);

        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.dist, o2.dist);
            }
        });
        pq.add(new Point(0, 0, 0, grid[0][0]));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (p.x == n - 1 && p.y == m - 1) break;

            int x = p.x;
            int y = p.y;
            int d = p.dist;
            int value = p.value;
            int[][] ds = {{-value, 0}, {value, 0}, {0, -value}, {0, value}};

            for (int[] dir: ds) {
                if (checkBound(dir, n, m, x, y) && dist[dir[0] + x][dir[1] + y] == INF) {
                    dist[dir[0] + x][dir[1] + y] = dist[x][y] + 1;
                    pq.add(new Point(dir[0] + x, dir[1] + y, dist[x][y] + 1, grid[dir[0] + x][dir[1] + y]));
                }
            }

        }
        if (dist[n - 1][m - 1] == INF) {
            System.out.println(-1);
        } else {
            System.out.println(dist[n - 1][m - 1]);
        }

    }

    static boolean checkBound(int[] d, int n, int m, int x, int y) {
        if (d[0] + x < 0 || d[0] + x >= n) return false;
        if (d[1] + y < 0 || d[1] + y >= m) return false;
        return true;
    }

}