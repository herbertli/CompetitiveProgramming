import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE / 2;
    static boolean[] marked;

    static int dfs(int[][] d, int n) {
        int res = 0;
        marked[n] = true;
        for (int j = 0; j < d[n].length; j++) {
            if (!marked[j] && j != n && d[n][j] != INF) {
                res = Math.max(dfs(d, j), res);
            }
        }

        return 1 + res;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> nameMap = new HashMap<>();
        int j = 0;
        int n = Integer.parseInt(in.readLine());

        int[][] d = new int[n * 2][n * 2];
        for (int[] i : d) Arrays.fill(i, INF);
        for (int i = 0; i < n; i++) {
            String[] temp = in.readLine().split(" reposted ");
            String a = temp[0].toLowerCase();
            String b = temp[1].toLowerCase();

            if (!nameMap.containsKey(a)) {
                nameMap.put(a, j++);
            }

            if (!nameMap.containsKey(b)) {
                nameMap.put(b, j++);
            }

            int ai = nameMap.get(a);
            int bi = nameMap.get(b);
            d[ai][bi] = 1;
        }

        int res = -1;
        for (int i = 0; i < d.length; i++) {
            marked = new boolean[d.length];
            res = Math.max(dfs(d, i), res);
        }
        System.out.println(res);

    }

}