import java.io.*;
import java.util.*;

public class Main {

  static boolean solve(int[] n, int total) {
    for (int i = 0; i < 6; i++) {
      for (int j = i+1; j < 6; j++) {
        for (int k = j+1; k < 6; k++) {
          int curr = n[i] + n[j] + n[k];
          if (total - curr == curr) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String[] s = in.readLine().split(" ");
      int[] n = new int[s.length];

      int total = 0;
      for (int i = 0; i < n.length; i++) {
        n[i] = Integer.parseInt(s[i]);
        total += n[i];
      }

      if (total % 2 != 0) {
        System.out.println("NO");
      } else {
        Arrays.sort(n);
        boolean yes = solve(n, total);


        if (yes) {
          System.out.println("YES");
        } else {
          System.out.println("NO");
        }
      }


    }

}
