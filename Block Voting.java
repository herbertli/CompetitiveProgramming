import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());
        for (int c = 0; c < cases; c++) {

            String[] s = in.readLine().split(" ");
            int parties = Integer.parseInt(s[0]);
            int[] members = new int[parties];
            int total = 0;
            for (int i = 0; i < parties; i++) {
                members[i] = Integer.parseInt(s[i + 1]);
                total += members[i];
            }

            for (int currP = 0; currP < parties; currP++) {

                int[] temp = new int[members.length - 1];
                int p = 0;
                for (int j = 0; j < members.length; j++) {
                    if (j != currP) {
                        temp[p] = members[j];
                        p++;
                    }
                }

                int needed = total / 2;
                int power = 0;

                // taken from the textbook on pg 73
                for (int i = 0; i < (0x1 << temp.length); i++) {
                    int sum = 0;
                    for (int j = 0; j < temp.length; j++) {
                        if ((i & (0x1 << j)) > 0) sum += temp[j];
                    }
                    if (sum <= needed && sum > needed - members[currP]) power++;
                }

                System.out.printf("party %d has power index %d\n", currP + 1, power);
            }
            System.out.println();
        }
        in.close();
    }
}
