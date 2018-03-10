import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static int longestPath(int currentNode, int[][] adj, int[][] marked) {
        boolean isLeaf = true;

        int max = 0;
        for (int i = 0; i < adj[currentNode].length; i++) {
            int pathLength = 0;

            if (adj[currentNode][i] == 1 && marked[currentNode][i] != 1) {

                marked[currentNode][i] = 1;
                marked[i][currentNode] = 1;

                pathLength = 1 + longestPath(i, adj, marked);
                marked[currentNode][i] = 0;
                marked[i][currentNode] = 0;
                isLeaf = false;
            }
            if (pathLength > max) max = pathLength;
        }

        if (isLeaf) return 0;
        return max;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null) {

            String[] split = s.split(" ");

            int nodes = Integer.parseInt(split[0]);
            int edges = Integer.parseInt(split[1]);

            if (nodes == 0 && edges == 0) break;

            int[][] adj = new int[nodes][nodes];
            int[][] marked = new int[nodes][nodes];

            for (int e = 0; e < edges; e++) {

                String[] temp = in.readLine().split(" ");
                int x = Integer.parseInt(temp[0]);
                int y = Integer.parseInt(temp[1]);

                adj[x][y] = 1;
                adj[y][x] = 1;

            }

            int longest = 0;
            for (int start = 0; start < nodes; start++) {
                if (longestPath(start, adj, marked) > longest) longest = longestPath(start, adj, marked);

            }
            System.out.println(longest);

        }



    }

}
