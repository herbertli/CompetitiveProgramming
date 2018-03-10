import java.io.*;
import java.util.*;

public class Main {

  static long binomial(final int N, final int K) {
    long ret = 1;
    for (int k = 0; k < K; k++) {
        ret = ret * (N-k) / (k+1);
    }
    return ret;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(in.readLine());
    String[] s = in.readLine().split(" ");
    long[] n = new long[N];

    TreeMap<Long, Integer> t = new TreeMap<>();

    long res = 1;
    for (int i = 0; i < N; i++) {
      n[i] = Long.parseLong(s[i]);
      if (t.containsKey(n[i])) {
        t.put(n[i], t.get(n[i]) + 1);
      } else {
        t.put(n[i], 1);
      }
    }

    int used = 0;
    while (used < 3) {
      for (long l: t.keySet()) {
        int K = Math.min(t.get(l), 3 - used);
        res *= binomial(t.get(l), K);
        used += K;
      }
    }
    System.out.println(res);

  }

}
