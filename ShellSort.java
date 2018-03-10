import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());

        for (int c = 0; c < cases; c++) {

            int turtles = Integer.parseInt(in.readLine());

            String[] unsortedS = new String[turtles];

            for (int i = 0 ; i < turtles; i++) {
                unsortedS[i] = in.readLine();
            }

            int i = 0;
            HashMap<String, Integer> h = new HashMap<>();
            HashMap<Integer, String> h2 = new HashMap<>();


            for (int t = 0; t < turtles; t++) {
                String name = in.readLine();
                h2.put(i, name);
                h.put(name, i++);
            }

            int[] unsorted = new int[turtles];

            for (int t = 0; t < turtles; t++) {
                unsorted[t] = h.get(unsortedS[t]);
            }

            TreeSet<Integer> t = new TreeSet<>();
//            System.out.println(Arrays.toString(unsorted));

            int curr = unsorted.length - 1;
            for (int start = unsorted.length - 1; start >= 0; start--) {
                if (unsorted[start] == curr) {
                    curr--;
                } else {
                    t.add(unsorted[start]);
                }
            }

            for (int e: t.descendingSet()) {
                System.out.println(h2.get(e));
            }
            System.out.println();

        }


    }

}