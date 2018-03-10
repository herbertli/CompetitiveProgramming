import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class C {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // N choose K
    public static long comb(int n, int k) {
        if (k > n) return 0;

        int a = Math.min(k, n - k);
        long res = 1;
        for (int i = 1; i <= a; i++) {
            res *= n--;
            res /= i;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());

        TreeMap<Integer, Integer> x = new TreeMap<>();
        TreeMap<Integer, Integer> y = new TreeMap<>();
        TreeMap<Point, Integer> pts = new TreeMap<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.x == o2.x && o1.y == o2.y) return 0;
                if (o1.x == o2.x) return Integer.compare(o1.y, o2.y);
                return Integer.compare(o1.x, o2.x);
            }
        });

        for (int i = 0; i < n; i++) {
            String[] temp = in.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            Point p = new Point(a, b);
            if (pts.containsKey(p)) {
                pts.put(p, pts.get(p) + 1);
            } else {
                pts.put(p, 1);
            }


            if (!x.containsKey(a)) x.put(a, 0);
            if (!y.containsKey(b)) y.put(b, 0);

            x.put(a, x.get(a) + 1);
            y.put(b, y.get(b) + 1);
        }

        long ans = 0;
        for (int i: x.keySet()) {
            ans += comb(x.get(i), 2);
        }
        for (int i: y.keySet()) {
            ans += comb(y.get(i), 2);
        }

        for (Point p: pts.keySet()) {
            ans -= comb(pts.get(p), 2);
        }

        System.out.println(ans);




    }

}