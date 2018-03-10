import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] n = new int[6];
        String[] temp = in.readLine().split("");
        for (int i = 0; i < 6; i++) {
            n[i] = Integer.parseInt(temp[i]);
        }

        int a = n[0] + n[1] + n[2];
        int b = n[3] + n[4] + n[5];
        if (a == b) {
            System.out.println(0);
            return;
        } else {
            int diff = Math.abs(a - b);
            int count = 0;
            PriorityQueue<Integer> diffs = new PriorityQueue<>(Collections.reverseOrder());
            for (int i = 0; i < 3; i++) {
                if (a > b) diffs.add(n[i]);
                else diffs.add(9 - n[i]);
            }
            for (int i = 3; i < 6; i++) {
                if (b > a) diffs.add(n[i]);
                else diffs.add(9 - n[i]);
            }

            while (diff > 0) {
                diff -= diffs.poll();
                count++;
            }
            System.out.println(count);
        }



    }

}