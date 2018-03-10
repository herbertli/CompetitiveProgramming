import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] s;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        s = new int[N][3];

        for (int i = 0; i < N; i++) {
            String[] temp = in.readLine().split(" ");
            s[i][0] = Integer.parseInt(temp[0]);
            s[i][1] = Integer.parseInt(temp[1]);
            s[i][2] = Integer.parseInt(temp[2]);
        }

        for (int i = 0; i < N; i++) {
            int far = 0;

            visited = new boolean[N];
            dist = new int[N];
            for (int j = 0; j <= i; j++) {
                if (s[j][0] - s[j][2] <= 0)
                    far = Math.max(dfs(j, i), far);
            }

            if (far >= 200) {
                System.out.println(i);
                return;
            }
        }

    }

    static int dfs(int j, int n) {
        if (visited[j]) return dist[j];
        visited[j] = true;
        int res = s[j][0] + s[j][2];
        for (int i = 0; i <= n; i++) {
            if (dist_sq(s[i][0], s[i][1], s[j][0], s[j][1]) <= s[i][2] + s[j][2]) {
                res = Math.max(res, dfs(i, n));
            }
        }
        dist[j] = res;
        return res;
    }

    static double dist_sq(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}