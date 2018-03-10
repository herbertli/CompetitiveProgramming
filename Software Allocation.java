import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int augment(ArrayList<Integer>[] adj, int[][] caps, int source, int sink) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] pred = new int[adj.length];
        Arrays.fill(pred, -1);
        int[] f = new int[adj.length];
        pred[source] = source;
        f[source] = Integer.MAX_VALUE;
        q.add(source);
        while (!q.isEmpty()) {
            int curr = q.poll();
            int currf = f[curr];
            if (curr == sink) {
                update(caps, pred, curr, f[curr]);
                return f[curr];
            }
            for (int i = 0; i < adj[curr].size(); i++) {
                int j = adj[curr].get(i);
                if (pred[j] != -1 || caps[curr][j] == 0) continue;
                pred[j] = curr;
                f[j] = Math.min(currf, caps[curr][j]);
                q.add(j);
            }
        }
        return 0;
    }

    static void update(int[][] caps, int[] pred, int curr, int f) {
        int p = pred[curr];
        if (p == curr) return;
        caps[p][curr] -= f;
        caps[curr][p] += f;
        update(caps, pred, p, f);
    }

    static int maxflow(ArrayList<Integer>[] adj, int[][] caps, int source, int sink) {
        int ret = 0;
        while (true) {
            int f = augment(adj, caps, source, sink);
            if (f == 0) break;
            ret += f;
        }
        return ret;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int source = 0;
        int sink = 37;

        int[][] caps = new int[38][38];
        int total_n = 0;
        ArrayList<Integer>[] adj = new ArrayList[38];
        Arrays.fill(adj, new ArrayList<>());

        while (true) {
            String s = in.readLine();
            if (s == null || s.length() == 0) {
                int mf = maxflow(adj, caps, source, sink);
                if (mf == total_n) {
                    for (int comp = 27; comp < 37; comp++) {
                        if (caps[sink][comp] == 1) {
                            for (int letter = 1; letter <= 26; letter++) {
                                if (caps[comp][letter] == 1) {
                                    System.out.printf("%s", (char) (64 + letter));
                                    break;
                                }
                            }
                        } else {
                            System.out.printf("_");
                        }
                    }
                    System.out.printf("\n");
                } else {
                    System.out.println("!");
                }

                if (s == null) break;

                adj = new ArrayList[38];
                caps = new int[38][38];
                total_n = 0;
                Arrays.fill(adj, new ArrayList<>());

            } else {
                String[] temp = s.split("\\s+");
                int c = temp[0].codePointAt(0) - 64;
                int n = Integer.parseInt(temp[0].charAt(1) + "");
                adj[0].add(c);
                caps[0][c] = n;
                total_n += n;
                for (int i = 0; i < temp[1].length() - 1; i++) {
                    int comp = Integer.parseInt(temp[1].charAt(i) + "");
                    caps[c][27 + comp] = 1;
                    adj[c].add(27 + comp);
                    caps[27 + comp][sink] = 1;
                    adj[27 + comp].add(sink);
                }
            }
        }
    }
}
