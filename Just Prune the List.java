import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(b.readLine());
        for (int c = 0; c < cases; c++) {
            String[] s = b.readLine().split(" ");
            int l1 = Integer.parseInt(s[0]);
            int l2 = Integer.parseInt(s[1]);

            int[] a1 = new int[l1];
            int[] a2 = new int[l2];
            String[] sa1 = b.readLine().split(" ");
            String[] sa2 = b.readLine().split(" ");

            for (int i = 0; i < l1; i++) {
                a1[i] = Integer.parseInt(sa1[i]);
            }
            for (int i = 0; i < l2; i++) {
                a2[i] = Integer.parseInt(sa2[i]);
            }

            Arrays.sort(a1);
            Arrays.sort(a2);

            int p1 = 0;
            int p2 = 0;
            int r = 0;
            while (p1 < l1 && p2 < l2) {
                if (a1[p1] == a2[p2]) {
                    p1++;
                    p2++;
                } else if (a1[p1] < a2[p2]) {
                    p1++;
                    r++;
                } else {
                    p2++;
                    r++;
                }
            }
            if (p1 < l1) {
                r += l1 - p1;
            } else {
                r += l2 - p2;
            }
            System.out.println(r);

        }

    }

}
