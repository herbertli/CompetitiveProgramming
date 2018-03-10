import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static void dfs(List<Integer>[] graph, boolean[] used, List<Integer> res, int u) {
        used[u] = true;
        for (int v : graph[u])
            if (!used[v])
                dfs(graph, used, res, v);
        res.add(u);
    }

    public static List<Integer> topologicalSort(List<Integer>[] graph) {
        int n = graph.length;
        boolean[] used = new boolean[n];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (!used[i])
                dfs(graph, used, res, i);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] temp = in.readLine().split(" ");
            int n = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);
            if (n == 0 && m == 0) break;
            List<Integer>[] g = new List[n];
            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                temp = in.readLine().split(" ");
                int a = Integer.parseInt(temp[0]) - 1;
                int b = Integer.parseInt(temp[1]) - 1;
                g[a].add(b);
            }

            List<Integer> res = topologicalSort(g);
            for (int i = 0; i < res.size(); i++) {
                if (i != res.size() - 1)
                    System.out.print((res.get(i) + 1) + " ");
                else
                    System.out.print(res.get(i) + 1);
            }
            System.out.print("\n");
        }


    }

}