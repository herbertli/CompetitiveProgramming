import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] marked;

    static void dfs(int[][] adj, int start) {
        for (int c = 1; c < adj[start].length; c++) {
            if (adj[start][c] == 1 && marked[c] != 1) {
                marked[c] = 1;
                dfs(adj, c);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            int vertices = Integer.parseInt(in.readLine());

            if (vertices == 0) break;

            int[][] adj = new int[vertices + 1][vertices + 1];

            while (true) {
                String temp = in.readLine();
                if (temp.equals("0")) break;

                String[] split = temp.split("\\s+");
                int x = Integer.parseInt(split[0]);
                for (int i = 1; i < split.length; i++) {
                    int y = Integer.parseInt(split[i]);
                    if (y == 0) break;
                    adj[x][y] = 1;
                }
            }

            String[] test = in.readLine().split("\\s+");
            int cases = Integer.parseInt(test[0]);

            for (int c = 1; c <= cases; c++) {
                marked = new int[vertices + 1];
                dfs(adj, Integer.parseInt(test[c]));
                String res = "";
                int size = 0;
                for (int i = 1; i <= vertices; i++) {
                    if (marked[i] != 1) {
                        res += " " + i;
                        size++;
                    }
                }
                System.out.println(size + res);
            }
        }
    }
}
