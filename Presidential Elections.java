import java.io.*;
import java.util.*;

public class Main {
  
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(in.readLine());
    
    int[][] districts = new int[N][4];

    int total_del = 0;
    for (int i = 0; i < N; i++) {
        String[] s = in.readLine().split(" ");
        int D = Integer.parseInt(s[0]);
        int C = Integer.parseInt(s[1]);
        int F = Integer.parseInt(s[2]);
        int U = Integer.parseInt(s[3]);
        total_del += D;
        districts[i][0] = D;
        districts[i][1] = C;
        districts[i][2] = F;
        districts[i][3] = U;
    }
    final int needed = total_del / 2 + 1;
    final int INF = 1000000000;
    int[][] lookup = new int[N + 1][2017];
    for (int[] i: lookup) Arrays.fill(i, INF);
    lookup[0][0] = 0;

    for (int i = 0; i < N; i++) {
        int D = districts[i][0];
        int C = districts[i][1];
        int F = districts[i][2];
        int U = districts[i][3];
        int votes_needed = (C + F + U) / 2 - C + 1;
        for (int j = 0; j <= 2016; j++) {
            if (lookup[i][j] != INF) {
                lookup[i + 1][j] = Math.min(lookup[i + 1][j], lookup[i][j]);

                if (votes_needed > U) {
                    lookup[i + 1][j + D] = lookup[i + 1][j + D];
                } else if (votes_needed <= 0 && j + D <= 2016) {
                    lookup[i + 1][j + D] = Math.min(lookup[i + 1][j + D], lookup[i][j]);
                } else if (j + D <= 2016) {
                    lookup[i + 1][j + D] = Math.min(lookup[i + 1][j + D], lookup[i][j] + votes_needed);
                }
            }
        }
    }

    int min = INF;
    for (int i = needed; i <= 2016; i++)
        min = Math.min(min, lookup[N][i]);

    if (min == INF)
        System.out.println("impossible");
    else
        System.out.println(min);
    

  }
}
