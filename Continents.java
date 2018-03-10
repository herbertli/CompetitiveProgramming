import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[][] marked;

    static int dfs(int[][] adj, int i, int j, int ch) {
        if (i >= adj.length || i < 0) return 0;
        if (marked[i][j] || adj[i][j] != ch) return 0;

        int sum = 1;
        marked[i][j] = true;
        if (adj[i][j] == ch) {
            sum += dfs(adj, i + 1, j, ch);
            sum += dfs(adj, i, (j + 1) % adj[i].length, ch);
            sum += dfs(adj, i - 1, j, ch);
            sum += dfs(adj, i, (j - 1 + adj[i].length) % adj[i].length, ch);
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String s;

        while ((s = in.readLine()) != null) {

            int rows = Integer.parseInt(s.split("\\s+")[0]);
            int cols = Integer.parseInt(s.split("\\s+")[1]);

            int[][] map = new int[rows][cols];
            marked = new boolean[rows][cols];

            String[] temp;
            String sch = "";
            for (int r = 0; r < rows; r++) {
                temp = in.readLine().split("");

                for (int c = 0; c < cols; c++) {
                    if (sch.equals("")) sch = temp[c];
                    if (temp[c].equals(sch)) map[r][c] = 1;
                    else map[r][c] = 0;
                }
            }

            temp = in.readLine().split("\\s+");

            int start_x = Integer.parseInt(temp[0]);
            int start_y = Integer.parseInt(temp[1]);
            int ch = map[start_x][start_y];

            dfs(map, start_x, start_y, ch);

            int max = Integer.MIN_VALUE;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    max = Math.max(max, dfs(map, i, j, ch));
                }
            }
            System.out.println(max);
            in.readLine();
        }
    }
}
