import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[][] marked;

    static int dfs(int[][] adj, int i, int j) {
        if (i > 99 || j > 99 || i < 0 || j < 0) return 0;
        if (marked[i][j] || adj[i][j] == 0) return 0;

        int sum = 1;
        marked[i][j] = true;
        if (adj[i][j] == 1) {
            sum += dfs(adj, i + 1, j);
            sum += dfs(adj, i, j + 1);
            sum += dfs(adj, i - 1, j);
            sum += dfs(adj, i, j - 1);
            sum += dfs(adj, i - 1, j - 1);
            sum += dfs(adj, i - 1, j + 1);
            sum += dfs(adj, i + 1, j + 1);
            sum += dfs(adj, i + 1, j - 1);
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());
        in.readLine();

        for (int c = 0; c < cases; c++) {

            int[][] adj = new int[100][100];
            String s;
            int row = 0;
            while (true) {
                s = in.readLine();
                if (s.charAt(0) != 'L' && s.charAt(0) != 'W') break;

                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == 'W') adj[row][i] = 1;
                }
                row++;
            }

            while (s != null) {
                if (s.length() == 0) break;
                int x = Integer.parseInt(s.split("\\s+")[0]);
                int y = Integer.parseInt(s.split("\\s+")[1]);
                marked = new boolean[100][100];
                System.out.println(dfs(adj, x - 1, y - 1));
                s = in.readLine();
            }
            if (c < cases - 1) System.out.println();
        }

    }

}
