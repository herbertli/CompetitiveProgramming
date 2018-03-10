import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] incoming;
    static int[] order;
    static String[] letters;
    static int[] visited;
    static int printed;

    static void topSortRec(int[][] adj, int depth) {
        if (depth == adj.length) {
            for (int i = 0; i < order.length; i++) {
                System.out.printf(letters[order[i]]);
                if (i < order.length - 1) System.out.printf(" ");
            }
            System.out.println();
            printed++;
            return;
        }
        for (int i = 0; i < adj.length; i++) {
            if (incoming[i] == 0 && visited[i] == 0) {
                visited[i] = 1;
                order[depth] = i;
                for (int j = 0; j < adj.length; j++) {
                    if (adj[i][j] == 1) incoming[j]--;
                }
                topSortRec(adj, depth + 1);
                for (int j = 0; j < adj.length; j++) {
                    if (adj[i][j] == 1) incoming[j]++;
                }
                visited[i] = 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {
            in.readLine();
            HashMap<String, Integer> letterToInt = new HashMap<>();

            letters = in.readLine().split("\\s+");
            Arrays.sort(letters);

            int[][] adj = new int[letters.length][letters.length];

            for (int i = 0; i < letters.length; i++) {
                letterToInt.put(letters[i], i);
            }

            String[] constraints = in.readLine().split("\\s+");

            for (String s: constraints) {
                String a = s.split("<")[0];
                String b = s.split("<")[1];
                adj[letterToInt.get(a)][letterToInt.get(b)] = 1;
            }

            incoming = new int[letters.length];
            visited = new int[letters.length];
            order = new int[letters.length];

            for (int i = 0; i < adj.length; i++) {
                for (int j = 0; j < adj.length; j++) {
                    if (adj[i][j] == 1) incoming[j]++;
                }
            }

            printed = 0;
            topSortRec(adj, 0);
            if (printed == 0) System.out.println("NO");
            if (c < cases - 1) System.out.println();
        }
    }
}
