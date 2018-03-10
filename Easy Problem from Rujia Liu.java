import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String temp;
        while ((temp = in.readLine()) != null) {

            String[] s = temp.split(" ");

            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            TreeMap<Integer, ArrayList<Integer>> t = new TreeMap<>();

            s = in.readLine().split(" ");

            for (int i = 0; i < n; i++) {
                int x = Integer.parseInt(s[i]);
                if (t.containsKey(x)) {
                    t.get(x).add(i + 1);
                } else {
                    ArrayList<Integer> a = new ArrayList<>();
                    a.add(i + 1);
                    t.put(x, a);
                }
            }

            for (int q = 0; q < m; q++) {
                s = in.readLine().split(" ");
                int search = Integer.parseInt(s[1]);
                int occ = Integer.parseInt(s[0]);
                if (t.containsKey(search)) {
                    if (t.get(search).size() < occ) {
                        System.out.println(0);
                    } else {
                        System.out.println(t.get(search).get(occ - 1));
                    }
                } else {
                    System.out.println(0);
                }
            }
        }
    }

}
