import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(in.readLine());

            HashMap<Integer, Integer> m = new HashMap<>();
            int maxLength = 0;
            int currStart = 0;
            for (int i = 0; i < n; i++) {
                int s = Integer.parseInt(in.readLine());
                if (!m.containsKey(s)) {
                    m.put(s, i);
                    maxLength = Math.max(maxLength, i - currStart + 1);
                } else {
                    maxLength = Math.max(maxLength, i - currStart);
                    currStart = Math.max(m.get(s) + 1, currStart);
                    m.put(s, i);
                }
            }
            System.out.println(maxLength);
        }

    }

}