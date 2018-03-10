import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String word;
    static char[][] grid;

    static boolean find(int index, int i, int j) {
        if (index >= word.length()) return true;
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) return false;
        if (grid[i][j] != word.charAt(index)) return false;
        int[][] directions = {
                {0,1},
                {1,0},
                {-1,0},
                {0,-1},
                {1,1},
                {1,-1},
                {-1,1},
                {-1,-1}
        };

        boolean found = false;
        for (int[] direction: directions) {
             found = found || find(index + 1, i + direction[0], j + direction[1], direction);
        }
        return found;
    }

    static boolean find(int index, int i, int j, int[] direction) {
        if (index >= word.length()) return true;
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) return false;
        if (grid[i][j] != word.charAt(index)) return false;
        return find(index + 1, i + direction[0], j + direction[1], direction);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {
            in.readLine();

            String[] temp = in.readLine().split("\\s+");

            int m = Integer.parseInt(temp[0]);
            int n = Integer.parseInt(temp[1]);

            grid = new char[m][n];

            for (int i = 0; i < m; i++) {
                String s = in.readLine();
                s = s.toLowerCase();
                for (int j = 0; j < n; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }

//            for (char[] t: grid) System.out.println(Arrays.toString(t));

            int Q = Integer.parseInt(in.readLine());

            for (int q = 0; q < Q; q++) {
                boolean found = false;
                word = in.readLine();
                word = word.toLowerCase();
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (find( 0, i, j)) {
                            System.out.printf("%d %d\n", i + 1, j + 1);
                            found = true;
                            break;
                        }
                    }
                    if (found) break;
                }
            }
            if (c < cases - 1) System.out.println();
        }
    }

}