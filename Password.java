import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {

            int order = Integer.parseInt(in.readLine());

            TreeSet<Character>[] c1 = new TreeSet[5];
            TreeSet<Character>[] can = new TreeSet[5];

            for (int i = 0; i < c1.length; i++) c1[i] = new TreeSet<>();

            for (int i = 0; i < 6; i++) {
                String[] s = in.readLine().split("");

                for (int j = 0; j < s.length; j++) {
                    if (!c1[j].contains(s[j].charAt(0))) c1[j].add(s[j].charAt(0));
                }

            }


            for (int i = 0; i < can.length; i++) can[i] = new TreeSet<>();

            int totalsize = 1;
            for (int i = 0; i < 6; i++) {
                String[] s = in.readLine().split("");

                for (int j = 0; j < s.length; j++) {
                    if (c1[j].contains(s[j].charAt(0))) can[j].add(s[j].charAt(0));
                }
            }

            int[] sizes = new int[5];
            for (int i = 0; i < can.length; i++) {
                totalsize *= can[i].size();
                sizes[i] = can[i].size();
            }

//            for (int i = 0; i < can.length;i++) System.out.println(Arrays.toString(can[i].toArray()));

            if (totalsize < order) {
                System.out.println("NO");
            } else {

                String s = "";

                order--;

                for (int i = 0; i < 5; i++) {

                    int remain = 1;
                    for (int j = i + 1; j < 5; j++) remain *= sizes[j];

                    s += can[i].toArray()[(order / remain)];
                    order %= remain;
                }
                System.out.println(s);

            }

        }

    }
}
