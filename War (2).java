import java.util.*;

public class Main {

    private static int find(int[] parent, int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    private static void union(int[] parent, int p, int q) {
        int repP = find(parent, p);
        int repQ = find(parent, q);
        if (repP != repQ) {
            parent[repQ] = repP;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numPeople = sc.nextInt();

        int[] relationships = new int[numPeople * 2];
        for (int i = 0; i < numPeople * 2; i++) {
            relationships[i] = i;
        }

        while (true) {
            int c = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (c == 0 && x == 0 && y == 0) {
                break;
            } else if (c == 1) {
                // set friends
                // check if x is friends with ys enemies or vice versa
                if (find(relationships, x) == find(relationships, y + numPeople) || find(relationships, x + numPeople) == find(relationships, y)) {
                    System.out.println(-1);
                } else {
                    // x y are friends
                    union(relationships, x, y);
                    // xs enemies and ys enemies are friends
                    union(relationships, x + numPeople, y + numPeople);
                }
            } else if (c == 2) {
                // set enemies
                // check if x y are friends
                if (find(relationships, x) == find(relationships, y)) {
                    System.out.println(-1);
                } else {
                    // x is friends with ys enemies and vice versa
                    union(relationships, x, y + numPeople);
                    union(relationships, x + numPeople, y);
                }
            } else if (c == 3) {
                // are friends
                if (find(relationships, x) == find(relationships, y)) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else {
                // are enemies
                // check if x is friends with ys enemies or vice versa
                if (find(relationships, x) == find(relationships, y + numPeople) || find(relationships, x + numPeople) == find(relationships, y)) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            }
        }
        sc.close();
    }

}