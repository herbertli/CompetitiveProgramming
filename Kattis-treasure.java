import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][][] ans;
    static char[][] grid;
    static int startX;
    static int startY;
    static int endX;
    static int endY;
    static int N;
    static int M;
    static int K;
    static int INF = 10000000;

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = in.readLine().split("\\s+");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);

        grid = new char[N][M];

        for (int i = 0; i < N; i++) {
            temp = in.readLine().split("");
            for (int j = 0; j < M; j++) {
                grid[i][j] = temp[j].charAt(0);
                if (temp[j].charAt(0) == 'G') {
                    endX = i;
                    endY = j;
                } else if (temp[j].charAt(0) == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        ans = new int[N][M][K + 1];
        for (int[][] i : ans) {
            for (int[] j : i) {
                Arrays.fill(j, -1);
            }
        }

        Queue<Integer[]> q = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.compare(o1[3], o2[3]);
            }
        });
        Integer[] start = {startX, startY, K, 1};
        q.add(start);
        ans[startX][startY][K] = 1;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty()) {
            Integer[] s = q.poll();
            int x = s[0];
            int y = s[1];
            int k = s[2];
            int step = s[3];

            if (ans[x][y][K] == -1) {
                ans[x][y][K] = step + 1;
                q.add(new Integer[]{x, y, K, step + 1});
//                System.out.printf("%d %d %d %d\n", x, y, k, step + 1);
            }

            for (int[] d: directions) {
                int newX = x + d[0];
                int newY = y + d[1];
                if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;
                char c = grid[newX][newY];

                if (c == '.' || c == 'S' || c == 'G') {
                    if (k >= 1) {
                        if (ans[newX][newY][k - 1] == -1) {
                            ans[newX][newY][k - 1] = ans[x][y][k];
                            q.add(new Integer[]{newX, newY, k - 1, step});
//                            System.out.printf("%d %d %d %d\n", x, y, k, step);
                        }
                    }
                } else if (c == 'F') {
                    if (k >= 2) {
                        if (ans[newX][newY][k - 2] == -1) {
                            ans[newX][newY][k - 2] = ans[x][y][k];
                            q.add(new Integer[]{newX, newY, k - 2, step});
//                            System.out.printf("%d %d %d %d\n", x, y, k, step);
                        }
                    }
                } else if (c == 'M') {
                    if (k >= 3) {
                        if (ans[newX][newY][k - 3] == -1) {
                            ans[newX][newY][k - 3] = ans[x][y][k];
                            q.add(new Integer[]{newX, newY, k - 3, step});
//                            System.out.printf("%d %d %d %d\n", x, y, k, step);
                        }
                    }
                } else if (c == '#') {
                    if (ans[newX][newY][k] == -1) {
                        ans[newX][newY][k] = INF;
//                        System.out.printf("%d %d %d %d\n", x, y, k, INF);
                    }
                }
            }
        }

        int best = INF;
        for (int left = 0; left <= K; left++) {
            if (ans[endX][endY][left] != -1) best = Math.min(best, ans[endX][endY][left]);
        }

        if (best == INF) {
            System.out.println(-1);
        } else {
            System.out.println(best);
        }
    }
}