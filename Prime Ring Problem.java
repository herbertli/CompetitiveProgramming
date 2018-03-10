import java.util.*;

public class Main {

    static TreeSet<Integer> primes;
    static int n;
    static HashMap<Integer, String> solved = new HashMap<>();
    static StringBuilder b;

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);

        primes = new TreeSet<>();
        for (int i : new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31}) {
            primes.add(i);
        }

        int c = 1;
        while (in.hasNext()) {
            if (c != 1)
                System.out.println();

            n = in.nextInt();
            System.out.printf("Case %d:\n", c);

            int[] ans = new int[n];
            ans[0] = 1;
            if (!solved.containsKey(n)) {
                b = new StringBuilder();
                rec(ans, 1);
                solved.put(n, b.toString());
                System.out.print(b.toString());
            } else {
                System.out.print(solved.get(n));
            }
            c++;

        }
    }

    static void rec(int[] curr, int currIn) {
        if (currIn == n) {
            if (primes.contains(curr[currIn - 1] + 1)) {
                for (int i = 0; i < curr.length; i++) {
                    if (i != curr.length - 1) b.append(curr[i]).append(" ");
                    else b.append(curr[i]);
                }
                b.append("\n");
            }
            return;
        }

        for (int i = 2; i <= n; i++) {
            boolean used = false;
            for (int j = 0; j < currIn; j++) {
                if (i == curr[j]) {
                    used = true;
                    break;
                }
            }
            if (used) continue;

            if (primes.contains(i + curr[currIn - 1])) {
                curr[currIn] = i;
                rec(curr, currIn + 1);
            }

        }
    }
}