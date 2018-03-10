import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// run KMP and get the max state
public class Main {

    public static int[] buildKMPTable(String pattern) {
        int[] table = new int[pattern.length() + 1];
        for (int i = 2; i < table.length; ++i) {
            int j = table[i - 1];
            while (true) {
                if (pattern.charAt(j) == pattern.charAt(i - 1)) {
                    table[i] = j + 1;
                    break;
                } else if (j == 0) break;
                else j = table[j];
            }
        }
        return table;
    }

    /**
     * Returns the final state when simulating the DFA built using pattern on the string text
     */
    public static int simulate(int[] table, String text, String pattern) {
        int maxState = -1;
        int state = 0;
        for (int i = 0; i < text.length(); ++i) {
            while (true) {
                if (text.charAt(i) == pattern.charAt(state)) {
                    state++;
                    break;
                } else if (state == 0) break;
                state = table[state];
            }
            maxState = Math.max(state, maxState);
            if (state == table.length - 1) break;
        }
        return maxState;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());

        for (int i = 0; i < cases; i++) {
            String s = in.readLine();
            int[] table = buildKMPTable(s);

            String newS = new StringBuilder(s).reverse().toString();

            int max = simulate(table, newS, s);

            StringBuilder ret = new StringBuilder();
            for (int j = 0; j < max; j++)
                ret.append(s.charAt(j));

            System.out.println(ret.reverse().toString());

        }

    }

}