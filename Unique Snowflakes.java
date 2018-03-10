import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            TreeSet<Integer> t = new TreeSet<>();

            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(in.readLine());
            }

            int max = 0;
            int start = 0;
            int end = 0;
            while (end < a.length) {
                if (!t.contains(a[end])) {
                    t.add(a[end]);
                    end++;
                } else {
                    t.remove(a[start]);
                    start++;
                }
                if (end - start > max) max = end - start;
            }

            System.out.println(max);
        }
        in.close();
    }

}
