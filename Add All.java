import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            String[] s = in.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                pq.add(Integer.parseInt(s[i]));
            }
            long total = 0;
            while (pq.size() != 1) {
                int a = pq.poll();
                int b = pq.poll();
                total += (a + b);
                pq.add(a + b);
            }

            System.out.println(total);

        }

    }

}