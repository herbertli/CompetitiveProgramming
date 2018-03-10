import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(in.readLine());
    int[] n = new int[N];

    String[] s = in.readLine().split(" ");
    for (int i = 0; i < N; i++)
      n[i] = Integer.parseInt(s[i]);

    int[] a = new int[N];
    int sum = 0;
    for (int i = 0; i < N; i++) {
      sum += n[i];
      a[i] = sum;
    }

    int[] b = new int[N];
    sum = 0;
    for (int i = N - 1; i >= 0; i--) {
      sum += n[i];
      b[i] = sum;
    }

    int count = 0;
    for (int i = 0; i < N - 1; i++)
      if (a[i] == b[i + 1])
        count++;

    System.out.println(count);

  }

}
