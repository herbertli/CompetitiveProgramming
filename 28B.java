import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int root(int[] p, int x) {
        return x == p[x] ? x : (p[x] = root(p, p[x]));
    }

    static void unite(int[] p, int a, int b) {
//        System.out.printf("UNION: %d %d\n", a, b);
        a = root(p, a);
        b = root(p, b);
        if (a != b)
            p[a] = b;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());

        int[] desired = new int[n + 1];
        int[] favorite = new int[n + 1];

        String[] temp = in.readLine().split(" ");
        for (int i = 0; i < n; i++) desired[i] = Integer.parseInt(temp[i]);
        temp = in.readLine().split(" ");
        for (int i = 0; i < n; i++) favorite[i] = Integer.parseInt(temp[i]);

        int[] parent = new int[n + 1];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n; i++) {
            if (i + favorite[i] < n)
                unite(parent, i, i + favorite[i]);
            if (i - favorite[i] >= 0)
                unite(parent, i, i - favorite[i]);
        }

        for (int i = 0; i < n; i++) {
            if (root(parent, desired[i] - 1) != root(parent, i)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");

    }

}